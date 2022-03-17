package com.crm.GenericLibrary;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This method consists of all generic methods related to webDriver actions
 * @author Ashwini
 *
 */

public class WebDriverUtility {
	
	/**
	 * This method will maximize window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will wait for 20 seconds for the page to load
	 * @param driver
	 */
	public void waitforPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	/**
	 * This method will wait for 10 seconds for the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait for 20 seconds for the element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will select the data from dropdown using index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method will select the data from dropdown using visible text
	 * @param element
	 * @param text
	 */
	public void select(WebElement element,String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	/**
	 * This method will select the data from the dropdown using value
	 * @param value
	 * @param element
	 */
	public void select(String value,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * This method will perform mouse Hover action
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform drag and drop action 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void dragAndDrop(WebDriver driver,WebElement src,WebElement target)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(src, target).perform();
	}
	
	/**
	 * This method will double click on the element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element);
	}
	
	/**
	 * This method will double click on Webpage
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will right click on webpage
	 * @param driver
	 */
	public void rightClick(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will right click on webelement
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will press enter Key
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	
	
	/**
	 * This method will press enter Key
	 * @throws Throwable
	 */
	public void enterKeyPress() throws Throwable
	{
		Robot rb= new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);	
	}
	
		
	/**
	 * This method will release enter Key
	 * @throws Throwable
	 */
	public void enterRelease() throws Throwable
	   {
		   Robot rb= new Robot();
		   rb.keyRelease(KeyEvent.VK_ENTER);
	   }
	
	/**
	 * This method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will switch the frame based on name or ID
	 * @param driver
	 * @param nameorId
	 */
	public void switchToFrame(WebDriver driver,String nameorId)
	{
		driver.switchTo().frame(nameorId);
	}
	
	/**
	 * This method will switch the frame based on address of the element
	 * @param driver
	 * @param address
	 */
	public void switchToFrame(WebDriver driver,WebElement address)
	{
		driver.switchTo().frame(address);
	}
	
	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel alert popup
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will switch to window depending on partial window title
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchTOWindow(WebDriver driver, String partialWinTitle)
	{
		//Step 1 : use getWindowHandles to capture all window id's
		Set<String> windows = driver.getWindowHandles();
		
		//Step 2 : iterate through the windows
		Iterator<String> it = windows.iterator();
		
		//Step 3 : Check whether there is next window
		while(it.hasNext())
		{
			//Step 4 : capture current window id
			String winId = it.next();
			
			//Step 5 : switch to current window and capture title
			String currentWinTitle = driver.switchTo().window(winId).getTitle();
			
			//Step 6 : check whether the current window is expected
			if(currentWinTitle.contains(partialWinTitle))
			{
				break;
			}
			}
	}
	
	/**
	 * This method will take screenShot and store it in folder called as ScreenShot
	 * @param driver
	 * @param screenShotName
	 * @return 
	 * @throws IOException
	 */
	public String getScreenShot(WebDriver driver,String screenShotName) throws Throwable
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = "./Screenshot"+screenShotName+".png";
		File dst = new File(path);
		Files.copy(src,dst);
		return path;
	}
	
	/**
	 * This method will perform random scroll
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)","");
	}
	
	/**
	 * This method will scroll until the specified element is found`
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
	js.executeScript("window.scrollBy(0, "+y+")", element);
	//js.executeScript("argument[0].scrollIntoView()",element);
	}

}
