package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactPage extends TestBase {
	
	HomePage homepage;
	LoginPage loginpage;
	TestUtil testutil;
	ContactPage contactpage;
	
	public ContactPage() {
		super();
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//div[@class='ui fitted checkbox']")
	List<WebElement> contactCheckBoxes;
	
	@FindBy(css="button[class*='ui linkedin icon']")
	WebElement refreshbtn;
	
	@FindBy(xpath = "//button[normalize-space()='Create']")
	WebElement create;
	
	@FindBy(name = "first_name")
	 WebElement firstNameInput;
	
	@FindBy(name = "last_name")
	 WebElement lastNameInput;
	
	@FindBy(xpath = "//div[@name='company']//input[contains(@class,'search')]")
	WebElement companyInput;
	
	@FindBy(xpath = "//div[@name='category']")
	WebElement categoryInput;
	
	@FindBy(xpath = "//div[@name='category' and @role='option']")
	List<WebElement> categoryvalues;
	
	@FindBy(xpath = "//input[@name='image']")
	WebElement imageInput;
	
	@FindBy(css = "i[class='save icon']")
	WebElement contactSaveBtn;
	
	public ContactPage createContacts(String ftName,String ltName,String companyIpt,String categoryIpt,String imgIpt) {
		
		create.click();
		firstNameInput.sendKeys(ftName);
		lastNameInput.sendKeys(ltName);
		companyInput.sendKeys(companyIpt);
		categoryInput.click();
		for(WebElement categoriesvalue : categoryvalues) {
			if(categoriesvalue.getText().equalsIgnoreCase(categoryIpt)) {
				categoriesvalue.click();
				break;
			}}
		imageInput.sendKeys(imgIpt);
		contactSaveBtn.click();
		return new ContactPage();
		
	}
	
	public void contactPageCheckBox() throws InterruptedException {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // click refresh button
	    js.executeScript("arguments[0].click();", refreshbtn);

	    for (WebElement cb : contactCheckBoxes) {

	        String text = cb.getText().trim();

	        if (text.equalsIgnoreCase("team one")) {
	            js.executeScript("arguments[0].click();", cb);
	            Thread.sleep(50000);
	        }
	    }
	}
}
