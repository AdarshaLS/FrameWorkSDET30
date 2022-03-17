package com.crm.PRACTICE;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeTestNGTest {
	
	@Test
	public void createOrg()
	{
		System.out.println("Org created");
		Assert.fail();
	}
	
	@Test(dependsOnMethods="createOrg")
	public void modifyOrg()
	{
		System.out.println("Org is modified");
	}
	
	@Test
	public void deleteOrg()
	{
		System.out.println("Org is deleted");
	}

}
