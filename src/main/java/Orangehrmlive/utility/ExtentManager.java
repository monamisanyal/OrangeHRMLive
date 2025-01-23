package Orangehrmlive.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static ExtentSparkReporter sparkReporter;  
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() throws Exception {
        
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\ExtentReport\\" + "MyReport.html");

        // Optionally load configuration file (it might be XML or JSON based on your ExtentReport version)
        sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "\\extent-config.xml");

         // Initialize ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set system information for the report
        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "Orangehrmlive");
        extent.setSystemInfo("Tester", "Monami");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Browser", "Chrome");
    }

    public static void endReport() {
        extent.flush(); // Finalize and write the report to disk
    }

}
