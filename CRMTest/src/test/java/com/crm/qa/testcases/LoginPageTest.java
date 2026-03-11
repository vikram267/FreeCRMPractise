package com.crm.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	
public LoginPageTest() {
	super();
}
		
@BeforeMethod
public void setup() {
	initialization();
	loginpage = new LoginPage();
}

@Test(priority=1)
public void ValidateLoginTitleTest() {
	
	String title=loginpage.validateLoginPageTitle();
	Assert.assertEquals(title, "#1 Free CRM Business Software - Free Forever");
	
	
}
@Test(priority=2)
public void crmLogoTest() {
	Boolean logo=loginpage.validateCRMImage();
	
	Assert.assertTrue(logo);
}

@Test(priority=3)
public void LoginTest() {
	homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
}

@Test(priority=4)
public void supportTest() {
	loginpage.ValidateSupportPage();
	
}

@AfterMethod
public void tearDown(ITestResult result) {

    String testName = result.getName();

    if (result.getStatus() == ITestResult.SUCCESS) {
        TestUtil.takeScreenShot(driver, testName + "_PASS");
    }
    else if (result.getStatus() == ITestResult.FAILURE) {
        TestUtil.takeScreenShot(driver, testName + "_FAIL");
    }
    else if (result.getStatus() == ITestResult.SKIP) {
        TestUtil.takeScreenShot(driver, testName + "_SKIP");
    }

    driver.quit();
}

}
