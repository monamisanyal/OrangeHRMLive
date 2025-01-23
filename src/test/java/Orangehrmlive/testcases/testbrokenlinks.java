package Orangehrmlive.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Orangehrmlive.base.baseClass;
import Orangehrmlive.pageobject.LoginPage;

public class testbrokenlinks extends baseClass {
	LoginPage lp;
	@BeforeMethod
	public void setup()  {
		launchApp();
		}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void brokenlinktest() {
		lp=new LoginPage();
		lp.testhttpbrokenlinks();
		
	}

}
