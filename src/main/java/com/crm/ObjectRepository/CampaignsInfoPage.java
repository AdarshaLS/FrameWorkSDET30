package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CampaignsInfoPage extends WebDriverUtility {

	//Declaration
	
		@FindBy(xpath = "//span[@class='dvHeaderText']")
		private WebElement campaignHeader;
		
		//Initialization
		public CampaignsInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getCampaignHeader() {
			return campaignHeader;
		}
		
		//Business Library
		/**
		 * This method will return the text campaign page after creating campaigns
		 * @param campaignHeaderName
		 * @return
		 */
		public String campaignHeader(String campaignHeaderName)
		{
			campaignHeaderName = campaignHeader.getText();
			return campaignHeaderName;
		}		
}
