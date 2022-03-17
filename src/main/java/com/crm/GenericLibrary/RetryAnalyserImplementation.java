package com.crm.GenericLibrary;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer{
	
	int count = 0;
	int retcount = 3;
	public boolean retry(ITestResult result) {
		while(count<retcount)
		{
			count++;
			return true;
		}		
		return false;
	}
}
