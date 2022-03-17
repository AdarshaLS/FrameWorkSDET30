package com.crm.DynamicPracticeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SelectAnyMonthFromCalenderTest {
	
	@Test
	public void selectAnyMonthFromCalender()
	{
		String date = "12";
		String monthAndYear = "December 2022";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 10).click().perform();
		
		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
		
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();		
		String arrowXPath = "//span[@aria-label='Next Month']";
		String dateXPath = "//div[text()='"+monthAndYear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']";
				
		
		for(;;)
		{
			try
			{
				driver.findElement(By.xpath(dateXPath)).click();
				break;
			}catch (Exception e)
			{
				driver.findElement(By.xpath(arrowXPath)).click();
				}			
		}
	}

}
