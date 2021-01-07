package testcases;

import org.testng.annotations.Test;

import com.google.common.io.ByteProcessor;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import utilities.AllureHelp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.sql.Savepoint;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


@Epic("Allure reports example")
@Feature("Test DemoWebShop- login")
public class AllureDemoTC {
	
	WebDriver driver;
	String url="http://demowebshop.tricentis.com/";
	
  @Test
  @Description("Testing DemoWebShop site , login as admin")
  @Severity(SeverityLevel.CRITICAL)
  @Attachment(value="Landing page",type="png")
  public void webShopLogin() {
	  
	  driver.get(url);
	  String title=driver.getTitle();
	  Assert.assertTrue(driver.getTitle().contains(title));
	  AllureHelp.saveScreenshot(takescreenshot(driver));
	  
  }
  
  public byte[] takescreenshot(WebDriver driver) {
	  
	  TakesScreenshot scrShot =((TakesScreenshot)driver);
	  byte[] srcFile=scrShot.getScreenshotAs(OutputType.BYTES); 
	  
	  //FileUtils.copyFile(srcFile, System.getProperty("user.dir")+"attachment/d"+".png");
	  
	return srcFile;
	  
  }
  
  
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  
	  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\ExeFiles\\chromedriver.exe");
		
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver=new ChromeDriver(option);
		driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
  
  }
  
  

  @AfterClass
  public void afterClass() {
	  
	  driver.close();
	  
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
