package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Core.TestngBase;
import Data_provider.DataproviderTestng;
import Page_objects.CreateContactMobilePage;
import utilities.ExtentTestmanager;
import utilities.AutonomiqMethods;

import utilities.CustomMethods;

public class TcDemoAutonomiq extends TestngBase {
	AutonomiqMethods im;
	JavascriptExecutor executor;
	Actions action;
	ExtentTestmanager log;
	
	
	@Test(dataProviderClass=DataproviderTestng.class,dataProvider="login_data")
	public void demo_testcase(String username, String password) {
        
		
		im=new AutonomiqMethods();
		
		action=new Actions(driver);
		executor=(JavascriptExecutor) driver;
		
		
		
		
		//will be using Data provider to feed data !! so removed the hash code 
		//now we are done with setup, lets run through testng xml
		
		
        try {
			// open website
			////generateReport("open website");
			Thread.sleep(2000);
			driver.get("https://login.salesforce.com");
			Thread.sleep(1500);
			// enter 'username'
			//generateReport("enter 'username'");
      im.waitTillElementGetsEnabled("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]"))
				.sendKeys(username);
			Thread.sleep(1500);
			// enter 'Password'
			//generateReport("enter 'Password'");
      im.waitTillElementGetsEnabled("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]"))
				.sendKeys(password);
			Thread.sleep(1500);
			// Click {xpath: '//*[@type='submit' and @name='Login']'}
			//generateReport("Click {xpath: '//*[@type='submit' and @name='Login']'}");
      im.waitTillElementGetsEnabled("//input[@type=concat('submit', '')] | //input[@type=concat('submit', '')]", driver, executor);
			im.CustomClick("//input[@type=concat('submit', '')] | //input[@type=concat('submit', '')]", driver, executor, "click", action);
			Thread.sleep(1500);
     
			// click 'uiTooltip'
			//generateReport("click 'uiTooltip'");
      im.waitTillElementGetsEnabled("//button[contains(@class,concat('slds-icon-waffle_container', ''))]//div[contains(@class,concat('tooltipTrigger', ''))] | //div[normalize-space(.) = concat('App Launcher', '')]", driver, executor);
			im.CustomClick("//button[contains(@class,concat('slds-icon-waffle_container', ''))]//div[contains(@class,concat('tooltipTrigger', ''))] | //div[normalize-space(.) = concat('App Launcher', '')]", driver, executor, "click", action);
			Thread.sleep(1500);
			// wait 5 secs
			//generateReport("wait 5 secs");
			Thread.sleep(5000);
			Thread.sleep(1500);
			// click on 'Community'
			//generateReport("click on 'Community'");
      im.waitTillElementGetsEnabled("//ul[contains(@class,concat('slds-grid_pull-padded', ''))]/li[1]/div/div/div/div[2]/a | //a[normalize-space(.) = concat('Community', '')]", driver, executor);
			im.CustomClick("//ul[contains(@class,concat('slds-grid_pull-padded', ''))]/li[1]/div/div/div/div[2]/a | //a[normalize-space(.) = concat('Community', '')]", driver, executor, "click", action);
			Thread.sleep(1500);
			// click on 'Contacts'
			//generateReport("click on 'Contacts'");
      im.waitTillElementGetsEnabled("//one-app-nav-bar-item-root[3]//a[contains(@class,concat('slds-context-bar__label-action', ''))] | //a[normalize-space(.) = concat('Contacts', '')]", driver, executor);
			im.CustomClick("//one-app-nav-bar-item-root[3]//a[contains(@class,concat('slds-context-bar__label-action', ''))] | //a[normalize-space(.) = concat('Contacts', '')]", driver, executor, "click", action);
			Thread.sleep(1500);
			// verify 'Contacts' is on screen
			//generateReport("verify 'Contacts' is on screen");
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//one-app-nav-bar-item-root[contains(@class,concat('slds-is-active', ''))]//a[contains(@class,concat('slds-context-bar__label-action', ''))] | //a[normalize-space(.) = concat('Contacts', '')]")));
			Thread.sleep(1500);
			// Click {xpath: '//*[@class='forceActionLink' and @title='New']'}
			//generateReport("Click {xpath: '//*[@class='forceActionLink' and @title='New']'}");
      im.waitTillElementGetsEnabled("//*[@class='forceActionLink' and @title='New']", driver, executor);
			im.CustomClick("//*[@class='forceActionLink' and @title='New']", driver, executor, "click", action);
			Thread.sleep(1500);
			// verify 'New Contact' is on screen
			//generateReport("verify 'New Contact' is on screen");
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(@class,concat('inlineTitle', ''))] | //h2[normalize-space(.) = concat('New Contact', '')]")));
			Thread.sleep(1500);
			// click  {xpath: '//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']'}
			//generateReport("click  {xpath: '//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']'}");
      im.waitTillElementGetsEnabled("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']", driver, executor);
			im.CustomClick("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']", driver, executor, "click", action);
			Thread.sleep(1500);
			// wait 2 secs
			//generateReport("wait 2 secs");
			Thread.sleep(2000);
			Thread.sleep(1500);
			// click on variable ${Salutation}
			//generateReport("click on variable ${Salutation}");
      im.waitTillElementGetsEnabled("//div[contains(@class,concat('visible', ''))]/div/ul/li[2]/a | //a[normalize-space(.) = concat('Mr.', '')]", driver, executor);
			im.CustomClick("//div[contains(@class,concat('visible', ''))]/div/ul/li[2]/a | //a[normalize-space(.) = concat('Mr.', '')]", driver, executor, "click", action);
			Thread.sleep(1500);
			// enter text in 'First Name'
			//generateReport("enter text in 'First Name'");
      im.waitTillElementGetsEnabled("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]"))
				.sendKeys("Test");
			Thread.sleep(1500);
			// enter text in 'Phone'
			//generateReport("enter text in 'Phone'");
      im.waitTillElementGetsEnabled("//label[@for=concat('46:1575;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')] | //label[@for=concat('46:1575;a', '')]//span[normalize-space(.) = concat('Phone', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('46:1575;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')] | //label[@for=concat('46:1575;a', '')]//span[normalize-space(.) = concat('Phone', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('46:1575;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')] | //label[@for=concat('46:1575;a', '')]//span[normalize-space(.) = concat('Phone', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('46:1575;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')] | //label[@for=concat('46:1575;a', '')]//span[normalize-space(.) = concat('Phone', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('tel', '')]"))
				.sendKeys("12345555");
			Thread.sleep(1500);
			// enter text in {xpath:'//*[@type='email']'}
			//generateReport("enter text in {xpath:'//*[@type='email']'}");
      im.waitTillElementGetsEnabled("//*[@type='email']", driver, executor);
			im.CustomClick("//*[@type='email']", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@type='email']"))
				.clear();
			driver.findElement(By.xpath("//*[@type='email']"))
				.sendKeys("jp@tcs.com");
			Thread.sleep(1500);
			// enter text in 'Title'
			//generateReport("enter text in 'Title'");
      im.waitTillElementGetsEnabled("//label[@for=concat('321:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('Title', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('321:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('Title', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('321:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('Title', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('321:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('Title', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]"))
				.sendKeys("Doctor");
			
			ExtentTestmanager.getTest().log(Status.INFO, "Test case complete!!");
			Thread.sleep(1500);
			//generateReport("Complete");
			Thread.sleep(5000);
		} catch (Exception e) {
			
		e.getMessage();
		
			
		}
   
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
