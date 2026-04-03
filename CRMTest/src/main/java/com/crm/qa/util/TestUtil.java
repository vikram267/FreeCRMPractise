package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(20);
	public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
	public static final Duration WEBDRIVER_WAIT = Duration.ofSeconds(10);
	static Workbook book;
	static Sheet sheet;
	public static  String TESTDATA_SHEET_PATH =System.getProperty("user.dir") +
			"/src/main/java/com/crm/qa/testdata/TestData.xlsx";
	
	
	public void switchToFrame() {
		
		driver.switchTo().frame("mainframe");
	}
	
	 public static void takeScreenShot(
	            WebDriver driver, String testName) {

	        try {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File src = ts.getScreenshotAs(OutputType.FILE);

	            File dest = new File(
	              "C:\\Users\\admin\\eclipse-workspace\\Screenshot\\"
	              + testName + ".png");

	            FileUtils.copyFile(src, dest);

	        } catch (Exception e) {
	            System.out.println("Screenshot failed: " + e.getMessage());
	        }
	    }
	 
	 public static Object[][] getTestData(String sheetName) {

		    Object[][] data = null;

		    try {
		        FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
		        book = WorkbookFactory.create(fis);
		        sheet = book.getSheet(sheetName);
		        int rowCount = sheet.getLastRowNum();
		        Row headerRow = sheet.getRow(0);
		        int colCount = headerRow == null ? 0 : headerRow.getLastCellNum();

		        data = new Object[rowCount][colCount];

		        DataFormatter formatter = new DataFormatter();

		        for (int i = 0; i < rowCount; i++) {
		            Row row = sheet.getRow(i + 1);

		            for (int k = 0; k < colCount; k++) {
		                data[i][k] = formatter.formatCellValue(row.getCell(k));
		            }
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return data;
		}
	 
	}

	
