package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'vikram  s')]")
	WebElement userName;
	
	@FindBy(xpath="//span[text()='Contacts']/parent::a")
	WebElement contactmenu;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public ContactPage contactmenu() throws InterruptedException {
		wait.until(
		        ExpectedConditions.visibilityOf(contactmenu));
		actions.moveToElement(contactmenu).perform();
		contactmenu.click();
		Thread.sleep(5000);
		return new ContactPage();
		
		
	}
	
	public boolean verifyCorrectUserName() {
		return userName.isDisplayed();
	}
	
	public String homePageTitle() {
		return driver.getTitle();
		
	}
}
