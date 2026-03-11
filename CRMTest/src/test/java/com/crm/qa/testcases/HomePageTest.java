package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestResult;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	HomePage homepage;
	ContactPage contactpage;
	LoginPage loginpage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		contactpage = new ContactPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=2)
	public void contactPageValidateTest() throws InterruptedException {
		contactpage=homepage.contactmenu();
	}
	
	@Test(priority=1)
	public void VerifyHomePageTitleTest() {
		String homepagetitle = homepage.homePageTitle();
		System.out.println("Actual title is:"+homepagetitle);
		Assert.assertEquals(homepagetitle,"Free CRM","Home page title not matched");
	}
	
	@Test(priority=3)
	public void verifyUserNameTest() {
		Assert.assertTrue(homepage.verifyCorrectUserName());
	}
	
	public void helpIconTest() throws Exception {
		homepage.helpIcon();
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
