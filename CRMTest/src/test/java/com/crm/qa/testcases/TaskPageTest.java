package com.crm.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.Task;
import com.crm.qa.util.TestUtil;

public class TaskPageTest  extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactPage contactpage;
	Task task;
	String sheetName = "Task";
	public TaskPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		contactpage = new ContactPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		task = new Task();	
		task.clickTaskMenu();
	}
	
	@Test(priority=0)
	public void TaskPageScreen() {
		task.clickTaskMenu();
	}
	
	@Test(priority=1)
	public void TaskPageTitleVerify() {
		task.clickTaskMenu();
		String result = task.getTaskTitleText();
		Assert.assertTrue(result.contains("Tasks"));
		System.out.println("Task title verified successfully");
	}
	
	@DataProvider
	public Object[][] getTasktestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=2,dataProvider="getTasktestData")
	public void TaskCreationMenu(String Title,String AssignedTo,String month_year,String day,String time,String Company,String Completion,String Status,String Type,String Contact,String Description,String Pirority,String Identifier) {
		task.clickCreateMenu(Title, AssignedTo, month_year, day, time, Company, Completion, Status, Type, Contact, Description, Pirority, Identifier);
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
	    driver = null; 
	}

	}


