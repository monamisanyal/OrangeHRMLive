package Orangehrmlive.dataprovider;

import org.testng.annotations.DataProvider;

import Orangehrmlive.utility.NewExcelLibrary;

public class dataProvider {
	NewExcelLibrary obj = new NewExcelLibrary();
	
	 @DataProvider(name ="Credentials1")
	 public Object[][] getCredentials() {
	  //Totals rows count
	  int rows=obj.getRowCount("Credentials"); //Credentials is worksheet name
	  //Total Columns
	  int column=obj.getColumnCount("Credentials");
	  int actRows=rows-1;
	  
	  Object[][] data= new Object[actRows][column];
	  
	  for(int i=0;i<actRows;i++) {
	   for(int j=0; j<column;j++) {
	    data[i][j]=obj.getCellData("Credentials", j, i+2);
	   }
	  }
	  return data;
	 }
}
