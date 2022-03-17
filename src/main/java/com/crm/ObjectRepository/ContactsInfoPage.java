package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ContactsInfoPage extends WebDriverUtility {
	
	//Declaration
		@FindBy(xpath = "//span[@class='dvHeaderText']")
		private WebElement contactHeaderText;
		
		//Initialization
		public ContactsInfoPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getContactHeaderText() {
			return contactHeaderText;
		}
		
		//Business Library
		/**
		 * This method will get header text of the contact info page
		 * @return
		 */
		public String contactNameInfo()
		{
			return contactHeaderText.getText();
		}
		


}
