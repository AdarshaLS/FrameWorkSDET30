package com.crm.PRACTICE;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeRetryAnalyseTest {
	
	@Test(retryAnalyzer=com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	public void practiceRetryAnalyse()
	{
		System.out.println("this is test 1");
		Assert.fail();
		System.out.println("this is passed");
	}

}
