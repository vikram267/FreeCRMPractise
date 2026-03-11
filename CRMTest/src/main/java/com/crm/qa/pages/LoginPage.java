package com.crm.qa.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase {
	
	//page factory -object repository
	@FindBy(xpath = "//a[@title='free crm home']")  
	WebElement logo;
	
	@FindBy(xpath="//a[contains(text(),'About')]")
	WebElement about;
	
	@FindBy(xpath = "//a[.//span[text()='Log In']]")
	WebElement loginbtn;
	
	@FindBy(xpath = "//a[text()='Support']")
	WebElement supportLink;
	
	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(css = "div.ui.fluid.large.blue.submit.button")
	 WebElement loginsave;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signup;
	
	@FindBy(xpath = "//a[contains(.,'Free CRM')]")
	WebElement docandvideo;
	
	public LoginPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver,this);
	}
public String validateLoginPageTitle() {
	return driver.getTitle();
}

public Boolean validateCRMImage() {
	try {
	wait.until(ExpectedConditions.visibilityOf(logo));
    return logo.isDisplayed();
	}catch(Exception e) {
		e.printStackTrace();
		return false;
	}

	
}
public void ValidateSupportPage() {
	
    supportLink.click();
    String supportTitle = driver.getTitle();
    System.out.println("Support page title: " + supportTitle);
    docandvideo.click();
    driver.navigate().back();
    
    }


public HomePage login(String un,String pwd) {
	
    wait.until(ExpectedConditions.elementToBeClickable(loginbtn));
    loginbtn.click();
	username.sendKeys(un);
	password.sendKeys(pwd);
	loginsave.click();
	return new HomePage();
}

}
