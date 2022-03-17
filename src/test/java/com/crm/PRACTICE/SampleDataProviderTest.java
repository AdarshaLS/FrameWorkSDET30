package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleDataProviderTest {
	
	@Test(dataProvider = "getData")
	public void sampleDataProvider(String Name, String model, int qty)
	{
		System.out.println(Name+"---"+model+"---"+qty);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj = new Object[4][3];
		
		obj[0][0] = "Mi";
		obj[0][1] = "13 Pro Max";
		obj[0][2] = 25;
		
		obj[1][0] = "iphone";
		obj[1][1] = "11 Max";
		obj[1][2] = 12;
		
		obj[2][0] = "Vivo";
		obj[2][1] = "Pro Max";
		obj[2][2] = 13;
		
		obj[3][0] = "Samsung";
		obj[3][1] = "A80";
		obj[3][2] = 20;
		
		return obj;
		
	}
}
