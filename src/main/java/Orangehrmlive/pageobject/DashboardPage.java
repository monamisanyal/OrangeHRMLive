package Orangehrmlive.pageobject;



import Orangehrmlive.base.baseClass;

public class DashboardPage extends baseClass {
	
	public String getcurrentURL()
	{
		String curURL=driver.getCurrentUrl();	
		return curURL;}	
	

}
