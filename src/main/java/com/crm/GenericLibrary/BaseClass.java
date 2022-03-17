package com.crm.GenericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	//create the object of all the utilites
	
		public DataBaseUtility dbLib=new DataBaseUtility();
		public ExcelFileUtility eLib=new ExcelFileUtility();
		public JavaUtility jLib=new JavaUtility();
		public PropertyFileUtility pLib=new PropertyFileUtility();
		public WebDriverUtility wLib=new WebDriverUtility();
		
		public WebDriver driver;
		
		public static WebDriver sDriver;
		
		@BeforeSuite(groups = {"regressionSuite","smokeSuite"})
		public void connectDataBase() throws Throwable
		{
			//dbLib.connectToDb();
			Reporter.log("====db conncetion sucessful====", true);			
		}
		
		@BeforeClass(groups = {"regressionSuite","smokeSuite"})
		//BeforeTest
		public void launchTheBrowser() throws Throwable
		{
			// read the data from property file
			
			String BROWSER = pLib.readDataFromPropertyFile("browser");
			String URL = pLib.readDataFromPropertyFile("url");
			
			// Create runtime polymorpism
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else
			{
				System.out.println("Invalid browser");
			}
			
			sDriver=driver;
			
			wLib.maximizeWindow(driver);
			wLib.waitforPageLoad(driver);
			driver.get(URL);
			Reporter.log("====Browser launch sucessful=====", true);
		}
		
		@BeforeMethod(groups = {"regressionSuite","smokeSuite"})
		public void login() throws Throwable
		{
			String USERNAME = pLib.readDataFromPropertyFile("username");
			String PASSWORD = pLib.readDataFromPropertyFile("password");
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(USERNAME, PASSWORD);
			Reporter.log("====Login sucessful=====", true);
		}
		
		@AfterMethod(groups = {"regressionSuite","smokeSuite"})
		public void logout()
		{
			HomePage hp=new HomePage(driver);
			hp.signOutOfApp(driver);
			Reporter.log("====Logout sucessful=====", true);
		}
		
		@AfterClass(groups = {"regressionSuite","smokeSuite"})
		//@AfterTest
		public void closeBrowser()
		{
			driver.quit();
			Reporter.log("====Browser close sucessful=====", true);
		}
		
		@AfterSuite(groups = {"regressionSuite","smokeSuite"})
		public void closeDb() throws Throwable
		{
			//dbLib.closeDB();
			Reporter.log("====Database close sucessful=====", true);
		}

}
