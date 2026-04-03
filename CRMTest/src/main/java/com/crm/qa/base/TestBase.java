package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.util.MyListener;
import com.crm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	protected static WebDriver driver;
	protected static Properties prop;
	protected static Actions actions;
    protected static WebDriverWait wait;
    protected static WebDriverListener listener;
    
	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
				    System.getProperty("user.dir") +
				    "/src/main/java/com/crm/qa/config/config.properties"
				);

			prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
		e.printStackTrace();
	}
}
	
public static void initialization() {
	
String browsername = prop.getProperty("browser");

if (browsername.equalsIgnoreCase("chrome")) {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();

} else if (browsername.equalsIgnoreCase("firefox")) {

    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();

} else {
    throw new RuntimeException("Unsupported browser: " + browsername);
}

listener = new MyListener();
driver = new EventFiringDecorator<>(listener).decorate(driver);


driver.manage().window().maximize();
driver.manage().deleteAllCookies();
wait = new WebDriverWait(driver, TestUtil.WEBDRIVER_WAIT);
actions = new Actions(driver);
driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT);
driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT);
driver.get(prop.getProperty("url"));
}
	
}