package com.crm.PRACTICE;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFilePracticeTest {
	
	public void propertyFile() throws Throwable
	{
		//Step 1: read the file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.Properties");
		
		//Step 2: create obj for properties
		Properties pObj = new Properties();
		pObj.load(fis);
		
		//Step 3: read the data
		String URL=pObj.getProperty("username");
		
		//Verification
		System.out.println(URL);				
	}

}
