package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class ContactsPage extends WebDriverUtility {
	
		//declaration
		@FindBy(xpath = "//img[@alt='Create Contact...']")
		private WebElement createContactLookupImg;		

		//Initialization
		public ContactsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//Utilization
		public WebElement getCreateContactLookupImg() {
			return createContactLookupImg;
		}

		//Business Library		
		/**
		 * This method will click on create contact image to create contact
		 */
		public void clickOnCreateContactImg()
		{
			createContactLookupImg.click();
		}	

}
