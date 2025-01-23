package Orangehrmlive.pageobject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Orangehrmlive.actiondriver.Action;
import Orangehrmlive.pageobject.DashboardPage;

import Orangehrmlive.base.baseClass;

public class LoginPage extends baseClass {
	
	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement username;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement pasword;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement LoginButton;
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
			}
	
	
	public DashboardPage Testlogin(String Test_username,String Test_password) {
		Action.enterText(username, Test_username);
		Action.enterText(pasword, Test_password);
		Action.click(LoginButton);
		return new DashboardPage();
	}
	
	public void testhttpbrokenlinks() {
		driver.findElement(By.linkText("OrangeHRM, Inc")).click();
		 Set<String> windowHandles = driver.getWindowHandles();
	        List<String> windowHandlesList = new ArrayList<>(windowHandles);
		 for (String handle : windowHandlesList) {
			 driver.switchTo().window(handle);
	            if (driver.getCurrentUrl().equals("https://www.orangehrm.com/")) {
	                System.out.println("Switched to the correct window: " + driver.getCurrentUrl());
	                break;
	        }}
		  // Step 4: Use Actions class to click on another link (link2) in the opened window
	        Actions act = new Actions(driver);
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement link2 = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[2]/a/button"));
	        
	        // Wait for link2 to be visible
	        wait.until(ExpectedConditions.visibilityOf(link2));
	        
	        // Click on link2
	        act.click(link2).perform();
	        
	        // Step 5: Update window handles list after clicking link2 (new window will be opened)
	        windowHandles = driver.getWindowHandles(); // Refresh window handles
	        windowHandlesList = new ArrayList<>(windowHandles);

	        // Step 6: Iterate over all open windows and print the current URL of each
	        for (String handle : windowHandlesList) {
	            driver.switchTo().window(handle);
	            System.out.println("Current Window URL: " + driver.getCurrentUrl());
	            String urls=driver.getCurrentUrl();
	            if (urls != null && !urls.isEmpty()) {
	            	try {
	                HttpURLConnection connection = (HttpURLConnection) new URL(urls).openConnection();
	                connection.setRequestMethod("GET");
	                connection.connect();

	                // Get the response code from the connection
	                int responseCode = connection.getResponseCode();

	                // Print the result (broken link if response code is not 200)
	                if (responseCode == HttpURLConnection.HTTP_OK) {
	                    System.out.println(urls + " - Link is working");
	                } else {
	                    System.out.println(urls + " - Broken link with status code: " + responseCode);
	                }

	            } catch (IOException e) {
	                // Handle exceptions (e.g., invalid URL or other IO issues)
	                System.out.println(urls + " - Error: " + e.getMessage());
	            }	
	            	
	            }}

	      
	}

	
	

}
