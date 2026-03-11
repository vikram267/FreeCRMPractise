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
	
	@FindBy(xpath="//button[contains(@class,'ui vk basic icon button')]")
	WebElement helpIcon;
	
	@FindBy(xpath="//button[contains(@aria-label,'Close')]")
	WebElement closeHelpIcon;
	
	
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
	
	public void helpIcon() throws Exception {
		
		helpIcon.click();
		Thread.sleep(3000);
		closeHelpIcon.click();
		Thread.sleep(3000);
	}
}
