package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;

public class DataProviderReadDataFromExcel {
	
	@Test(dataProvider="OrgTestData")
	public void readMultipleData(String orgName, String indType)

{
System.out.println(orgName+" "+indType);		
}
	@DataProvider(name="OrgTestData")
	public Object[][] getData() throws Throwable
	{
		ExcelFileUtility eLib = new ExcelFileUtility();
		Object[][] data = eLib.readMultipleDataFromExcel("OrgTestData");
		return data;
	}

}
