package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

		//Step 1 : declaration
		@FindBy(xpath = "//span[@class='dvHeaderText']")
		private WebElement headerText;
		
		//Step 2 : Initialization
		public OrganizationInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Step 3 : Utilization
		public WebElement getHeaderText() {
			return headerText;
		}
		
		
		//business library
		
		/**
		 * This method will get contact header after creating organization
		 * @return
		 */
		public String OrgNameInfo()
		{
			 String orgInfo = headerText.getText();
			 return orgInfo;
		}
		
		
}
