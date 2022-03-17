package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class OpportunitiesInfoPage  extends WebDriverUtility {
	
		//Declaration
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement OpportunityInfoLnk;

		//Initialization
		public OpportunitiesInfoPage(WebDriver driver)
		{
		PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getOpportunityInfoLnk() {
			return OpportunityInfoLnk;
		}
		
		//Business library
		
		/**
		 * This method will get Opportunity Info text
		 * @return
		 */
		public String OpportunityInfo()
		{
			String OpportunityInfoTxt = OpportunityInfoLnk.getText();
			return OpportunityInfoTxt;
		}
}
