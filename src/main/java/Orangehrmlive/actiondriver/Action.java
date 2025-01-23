package Orangehrmlive.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Orangehrmlive.base.baseClass;

public class Action extends baseClass {

    // Static WebDriver instance
private static WebDriver driver;
private static WebDriverWait wait;



// Static method to initialize the WebDriver
public static void initializeDriver(WebDriver driverInstance) {
    driver = driverInstance;
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
}

// Wait for an element to be visible using By locator
public static WebElement waitForElementVisible(WebDriver driver, By locator) {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return element;
}

// Wait for an element to be click able using By locator
public static WebElement waitForElementClickable(WebDriver driver, By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    return element;
}

// Static method to click an element
public static void click(WebElement locatorname) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorname));
    element.click();
}

// static method for click using JS executioner
public static void clickbyjs(WebElement locatorname) {
	 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorname));	
	// JavaScript click using executeScript
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
}


// Static method to enter text into a text field
public static void enterText(WebElement locatorname, String text) {
    WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
  	            element.clear();
        element.sendKeys(text);
    
	
}

// Static method to get the text from an element
public static String getText(WebElement locatorname) {
    WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
    return element.getText();
}

// Static method to check if an element is visible
public static boolean isElementVisible(WebElement locatorname) {
    try {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
        return element.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
// Static method to check if an element is selected
public static boolean isElementSelected(WebElement locatorname) {
    try {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
        return element.isSelected();
    } catch (Exception e) {
        return false;
    }
}
// Static method to check if an element is enabled
public static boolean isElementEnabled(WebElement locatorname) {
    try {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
        return element.isEnabled();
    } catch (Exception e) {
        return false;
    }
}

// Static method to hover over an element (optional, can be used for dropdowns)
public static void hoverOverElement(WebElement locatorname) {
    WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
    new org.openqa.selenium.interactions.Actions(driver).moveToElement(element).perform();
}

// Static method to select an option from a dropdown (by visible text)
public static void selectDropdownByVisibleText(WebElement locatorname, String visibleText) {
	
	WebElement element = wait.until(ExpectedConditions.visibilityOf(locatorname));
	 if (driver == null) {
         throw new IllegalStateException("WebDriver is not initialized!");
     }
    Select dropdown = new Select(element);
    dropdown.selectByVisibleText(visibleText);
   // element.click();
	 
}

// Static method to select an option from a dropdown (by visible value)
public static void selectDropdownByValue(WebElement locatorname, String value) {
	 if (driver == null) {
         throw new IllegalStateException("WebDriver is not initialized!");
     }
    Select select = new Select(locatorname);
    select.selectByValue(value);
}

// Select option by index using WebElement
public static void selectDropdownByIndex(WebElement locatorname, int index) {
	if (driver == null) {
         throw new IllegalStateException("WebDriver is not initialized!");
     }
    Select select = new Select(locatorname);
    select.selectByIndex(index);
}

// Static method to maximize the browser window
public static void maximizeWindow() {
    driver.manage().window().maximize();
}

// Static method to close the browser
public static void closeBrowser() {
    if (driver != null) {
        driver.quit();
    }
}

public static String screenShot(WebDriver driver,String filename) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	  File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
	  String destination = System.getProperty("user.dir")+"\\Screenshot\\"+filename+"_"+dateName+".png";
	  File finalDestination= new File(destination);
	  try {
	   FileUtils.copyFile(source, finalDestination);
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.getMessage();
	  }
	  return destination;
	 }

// Static method to get the WebDriver instance (if needed)
public static WebDriver getDriver() {
    return driver;
}


}
