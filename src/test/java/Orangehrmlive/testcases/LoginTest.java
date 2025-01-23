package Orangehrmlive.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Orangehrmlive.dataprovider.dataProvider;

import Orangehrmlive.base.baseClass;
import Orangehrmlive.pageobject.DashboardPage;
import Orangehrmlive.pageobject.LoginPage;

public class LoginTest extends baseClass{
	LoginPage lp;
	DashboardPage dp; 
	
	@BeforeMethod
	public void setup()  {
		launchApp();
		}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	@Test(dataProvider="Credentials1" , dataProviderClass=dataProvider.class )
	public void TestLoginCredentials(String uname,String pass) {
		lp=new LoginPage();
		dp= lp.Testlogin(uname, pass);
		String curURL=dp.getcurrentURL();
		String expURL="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(curURL, expURL);
		
	}

}
