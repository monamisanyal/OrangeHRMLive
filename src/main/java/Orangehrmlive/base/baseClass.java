package Orangehrmlive.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import Orangehrmlive.actiondriver.Action;

import Orangehrmlive.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {
	 // WebDriver instance
    public static WebDriver driver;

    // Properties instance to load the configuration
    public static Properties properties;
    public ExtentReports extent;
	 public ExtentSparkReporter sparkReporter;

    // Constructor to initialize WebDriver and read config file
    public void launchApp()  {
        initializeDriver();
    }

    @BeforeSuite
    // Method to load configuration properties from the config file
    public void loadConfigProperties() throws Exception {
    	ExtentManager.setExtent(); 
        properties = new Properties();
        try {
            // Load the properties from the config file
            FileInputStream configFile = new FileInputStream("C:\\Users\\monamis\\eclipse-workspace\\Orangehrmlive\\Configuration\\Config.properties");
            properties.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to initialize WebDriver based on the browser type from config
    private void initializeDriver() {
        String browser = properties.getProperty("browser").toLowerCase();
       
       

        switch (browser) {
            case "chrome":
                // Using WebDriverManager to automatically set up ChromeDriver
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); // Example option
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                // Using WebDriverManager to automatically set up GeckoDriver
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                // Using WebDriverManager to automatically set up EdgeDriver
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        Action.initializeDriver(driver);
    }
    @AfterSuite
    public void AfterSuite() throws Exception {
    	ExtentManager.endReport();;
    	
    	}

}
