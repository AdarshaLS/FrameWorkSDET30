package com.crm.PRACTICE;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;

public class PracticeForGenericUtilsTest {

	@Test
	public void practice() throws Throwable
	{
		JavaUtility jLib = new JavaUtility();
		int ran = jLib.getRandomNumber();
		String dat = jLib.getSystemDateFormat();
		String date = jLib.getSystemDateFormat();
		System.out.println(ran + date);
		System.out.println(dat);
		
		PropertyFileUtility pLib = new PropertyFileUtility();
		String brows = pLib.readDataFromPropertyFile("browser");
		System.out.println(brows);
		
	}
}
