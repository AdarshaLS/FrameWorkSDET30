package com.crm.DynamicPracticeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.HomePage;

public class ContactCheckBoxLastSelectTest extends BaseClass {
	
	@Test
	public void contactCheckBoxLastSelect() throws Throwable
	{
		HomePage hp = new HomePage(driver);
		hp.clickOnContactLnk();
		
		WebElement ele = driver.findElement(By.xpath("(//input[@name='selected_id'])[last()]"));
		wLib.scrollAction(driver, ele);
		Thread.sleep(5000);
		ele.click();
		Thread.sleep(5000);
		
	}

}
