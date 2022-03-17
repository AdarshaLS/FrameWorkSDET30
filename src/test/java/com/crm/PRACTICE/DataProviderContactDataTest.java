package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtility;

public class DataProviderContactDataTest 
	{
		@Test(dataProvider = "ContactWithData")
		public void createConatctWithData(String lastName)
		{
			System.out.println(lastName);
		}
		@DataProvider(name="ContactWithData")
		public Object[][] getData() throws Throwable
		{
			ExcelFileUtility eLib=new ExcelFileUtility();
			Object[][] data = eLib.readMultipleDataFromExcel("ContactWithData");
			return data;
		}

	}
