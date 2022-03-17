package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateCampaignsPage extends WebDriverUtility {
	
		//Declaration
		@FindBy(name="campaignname")
		private WebElement campaignNameEdt;
		
		@FindBy(xpath="//img[@alt='Select']")
		private WebElement productLookUpImg;
		
		@FindBy(name="search_text")
		private WebElement searchEdt;
		
		@FindBy(name="search")
		private WebElement searchBtn;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		//Initialization
		public CreateCampaignsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getCampaignNameEdt() {
			return campaignNameEdt;
		}

		public WebElement getProductLookUpImg() {
			return productLookUpImg;
		}

		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getSearchBtn() {
			return searchBtn;
		}

		public WebElement getSaveBtn() {
			return saveBtn;
		}
		
		//Business Library
		
		/**
		 * This method will create campaign name and save
		 * @param campaignName
		 */
		public void createCampaign(String campaignName)
		{
			campaignNameEdt.sendKeys(campaignName);
			saveBtn.click();
		}

		/**
		 * This method will create campaign with product and click on save
		 * @param campaignName
		 * @param driver
		 * @param productName
		 */
		public void createCampaign(String campaignName, WebDriver driver,String productName)
		{
			campaignNameEdt.sendKeys(campaignName);
			productLookUpImg.click();
			switchTOWindow(driver,"Products");
			searchEdt.sendKeys(productName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
			switchTOWindow(driver,"Campaigns");
			saveBtn.click();
		}

}
