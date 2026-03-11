package com.crm.qa.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase {

	HomePage homepage;
	ContactPage contactpage;
	LoginPage loginpage;
	TestUtil testutil;
	String sheetName = "Contact";
	
	public ContactPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		contactpage = new ContactPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		homepage.contactmenu();
	}
	
	@Test(priority=2)
	public void contactCheckBoxValidation() throws InterruptedException {
		contactpage.contactPageCheckBox();
	}
	
	@DataProvider
	public Object[][] getCRMtestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=1, dataProvider="getCRMtestData")
	public void createContactTest(String firstname, String lastname, String company, String category, String image) {
		contactpage.createContacts(firstname, lastname, company, category, image);
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		String testName = result.getName();
		
		if(ITestResult.FAILURE==result.getStatus()) {
			TestUtil.takeScreenShot(driver, testName + "FAIL");
		}
		else if(ITestResult.SUCCESS==result.getStatus()) {
			TestUtil.takeScreenShot(driver, testName + "PASS");
		}
		else if (result.getStatus() == ITestResult.SKIP) {
	        TestUtil.takeScreenShot(driver, testName + "_SKIP");
	    }	
		
		driver.quit();
	}
}
