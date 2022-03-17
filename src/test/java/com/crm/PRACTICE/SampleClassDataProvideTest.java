package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleClassDataProvideTest {
	
	@Test(dataProvider = "getData")
	
	public void sampleClassDataProvider(String Name, String Branch)
	{
		System.out.println(Name+"----"+Branch);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] Obj = new Object[6][2];
		
		Obj[0][0] = "Manjunath";
		Obj[0][1] = "Mech";
		
		Obj[1][0] = "Partha";
		Obj[1][1] = "Computer Science";
		
		Obj[2][0] = "Venkatesh";
		Obj[2][1] = "Bio Technology";
		
		Obj[3][0] = "Sarswathi";
		Obj[3][1] = "Civil";
		
		Obj[4][0] = "Soumya";
		Obj[4][1] = "Instrumentation";
		
		Obj[5][0] = "Srinivas";
		Obj[5][1] = "Electronics and Communication";
		
		return Obj;
	}

}
