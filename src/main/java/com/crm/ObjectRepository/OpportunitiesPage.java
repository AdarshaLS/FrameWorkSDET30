package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class OpportunitiesPage  extends WebDriverUtility {
	
		//Declaration
		@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
				private WebElement createOppurtunitesLnk;
		
		//Initialization
		public OpportunitiesPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getCreateOppurtunitesLnk() {
			return createOppurtunitesLnk;
		}
		
		//Business Library
		
		/**
		 * This method will click on create opportunities link
		 */
		public void clickOnCreateOpportunitiesLink()
		{
			createOppurtunitesLnk.click();
		}
}
