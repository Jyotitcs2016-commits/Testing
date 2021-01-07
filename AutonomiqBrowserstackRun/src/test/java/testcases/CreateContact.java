package testcases;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.beanutils.MethodUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;


import Core.TestngBase;
import Data_provider.DataproviderTestng;
import Page_objects.CreateContactMobilePage;
import utilities.ExtentTestmanager;
import utilities.AutonomiqMethods;

import utilities.CustomMethods;


public class CreateContact extends TestngBase {
 	
	@Test(dataProviderClass=DataproviderTestng.class,dataProvider="login_data")
    
    public void User_login(String username,String password) {
		
		/*Inheriting base class = Testng_base_class
		 *Calling autonomiq method class and custom methods class
		 *autonomiq method  contains click and wait methods
		 *Copy Autonomiq scripts under Test anotation starting steps written in selenium
		 *Using Dataprovider class for feeding data to testcases
		 *instantiating Actions and Javascript executor
		 */
	
		AutonomiqMethods im=new AutonomiqMethods();
		CustomMethods nw=new CustomMethods();
		JavascriptExecutor executor=(JavascriptExecutor) driver;
		Actions action=new Actions(driver);
		CreateContactMobilePage objmob=new CreateContactMobilePage(driver);
		
	
		
		
	//login apps
		try {
			// open website
			ExtentTestmanager.generatereport("open website");
			Thread.sleep(2000);
			driver.get("https://login.salesforce.com");
			Thread.sleep(1500);
			// enter 'username'
			ExtentTestmanager.generatereport("");
			im.waitTillElementGetsEnabled("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')] | //label[normalize-space(.) = concat('Username', '')]/ancestor::div[@class=concat('inputgroup', '')]//input[@type=concat('email', '')]"))
				.sendKeys(username);
			Thread.sleep(1500);
			// enter 'Password'
			ExtentTestmanager.generatereport("enter 'Password'");
			im.waitTillElementGetsEnabled("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]", driver, executor);
			im.CustomClick("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]", driver, executor, "click", action);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]"))
				.clear();
			driver.findElement(By.xpath("//label[@for=concat('password', '')]/following-sibling::input[@type=concat('password', '')] | //label[normalize-space(.) = concat('Password', '')]/following-sibling::input[@type=concat('password', '')]"))
				.sendKeys(password);
			Thread.sleep(1500);
			// Click {xpath: '//*[@type='submit' and @name='Login']'}
			ExtentTestmanager.generatereport("Click {xpath: '//*[@type='submit' and @name='Login']'}");
			im.waitTillElementGetsEnabled("//input[@type=concat('submit', '')] | //input[@type=concat('submit', '')]", driver, executor);
			im.CustomClick("//input[@type=concat('submit', '')] | //input[@type=concat('submit', '')]", driver, executor, "click", action);
			Thread.sleep(1500);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
			
		}
		
		
	

    }



@Test(dataProviderClass=DataproviderTestng.class,dataProvider="contacts_data",dependsOnMethods="User_login")
public void Create_contact(String Last_Name,String first,String salut,String phone,String email,String title) throws Exception {
	
	AutonomiqMethods im=new AutonomiqMethods();
	CustomMethods nw=new CustomMethods();
	JavascriptExecutor executor=(JavascriptExecutor) driver;
	Actions action=new Actions(driver);
	CreateContactMobilePage objmob=new CreateContactMobilePage(driver);

	
	

	if(run_env.equalsIgnoreCase("mobile")) {
		System.out.println("Entering mobile device");
		im.waitTillElementGetsEnabled("//*[@title=\"Toggle SideBar\"]", driver, executor);
		im.CustomClick("//*[@title=\"Toggle SideBar\"]", driver, executor, "click", action);
	
	im.waitTillElementGetsEnabled("//*[@class=\"title truncate\" and  contains(text(),\"Contacts\")]", driver, executor);
	im.CustomClick("//*[@class=\"title truncate\" and  contains(text(),\"Contacts\")]", driver, executor, "click", action);
	
	im.waitTillElementGetsEnabled("//*[@dir=\"ltr\" and text()=\"New\" ]", driver, executor);
	im.CustomClick("//*[@dir=\"ltr\" and text()=\"New\" ]", driver, executor, "click", action);
	
		//click  {xpath: '//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']'}
    ExtentTestmanager.generatereport("click  {xpath: '//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']'}");
 
    
    // enter text in 'First Name'
    ExtentTestmanager.generatereport("enter text in 'First Name'");
    im.waitTillElementGetsEnabled("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor);
    im.CustomClick("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor, "click", action);
    Thread.sleep(1000);
    objmob.set_firstname(first);
 // enter text in 'last Name'
    ExtentTestmanager.generatereport("enter text in 'last Name'");
    im.waitTillElementGetsEnabled("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input", driver, executor);
    im.CustomClick("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input", driver, executor, "click", action);
    Thread.sleep(1000);
    objmob.set_lastname(Last_Name);
    // enter text in 'Phone'
    ExtentTestmanager.generatereport("enter text in 'Phone'");
    im.waitTillElementGetsEnabled("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor);
    im.CustomClick("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor, "click", action);
    Thread.sleep(1000);
   objmob.set_phone(phone);
    // enter text in {xpath:'//*[@type='email']'}
    ExtentTestmanager.generatereport("enter text in {xpath:'//*[@type='email']'}");
    im.waitTillElementGetsEnabled("//*[@type='email']", driver, executor);
    im.CustomClick("//*[@type='email']", driver, executor, "click", action);
    Thread.sleep(1000);
    objmob.set_email(email);
    // enter text in 'Title'
    ExtentTestmanager.generatereport("enter text in 'Title'");
    im.waitTillElementGetsEnabled("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor);
    im.CustomClick("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor, "click", action);
   objmob.set_title(title);
    // click  {xpath:'//*[@dir='ltr' and text()='Save & New']'}
    ExtentTestmanager.generatereport("click  {xpath:'//*[@dir='ltr' and text()='Save']'}");
    im.waitTillElementGetsEnabled("//*[@class=\" label bBody\" ]//ancestor::button[@title=\"Save\"]", driver, executor);
    im.CustomClick("//*[@class=\" label bBody\" ]//ancestor::button[@title=\"Save\"]", driver, executor, "click", action);
    Thread.sleep(1500);
    ExtentTestmanager.generatereport("Complete");
    Thread.sleep(5000);
	
	}
	   
	else {	
        try {
        
        
        // click 'uiTooltip'
        ExtentTestmanager.generatereport("click 'uiTooltip'");
        im.waitTillElementGetsEnabled("//button[contains(@class,concat('slds-icon-waffle_container', ''))]//div[contains(@class,concat('tooltipTrigger', ''))] | //div[normalize-space(.) = concat('App Launcher', '')]", driver, executor);
        im.CustomClick("//button[contains(@class,concat('slds-icon-waffle_container', ''))]//div[contains(@class,concat('tooltipTrigger', ''))] | //div[normalize-space(.) = concat('App Launcher', '')]", driver, executor, "click", action);
        Thread.sleep(1500);
        // wait 5 secs
        ExtentTestmanager.generatereport("wait 5 secs");
        Thread.sleep(5000);
        Thread.sleep(1500);
        // click on 'Community'
        ExtentTestmanager.generatereport("click on 'Community'");
        im.waitTillElementGetsEnabled("//ul[contains(@class,concat('slds-grid_pull-padded', ''))]/li[1]/div/div/div/div[2]/a | //a[normalize-space(.) = concat('Community', '')]", driver, executor);
        im.CustomClick("//ul[contains(@class,concat('slds-grid_pull-padded', ''))]/li[1]/div/div/div/div[2]/a | //a[normalize-space(.) = concat('Community', '')]", driver, executor, "click", action);
        Thread.sleep(1500);
        // click on 'Contacts'
        ExtentTestmanager.generatereport("click on 'Contacts'");
        im.waitTillElementGetsEnabled("//one-app-nav-bar-item-root[3]//a[contains(@class,concat('slds-context-bar__label-action', ''))] | //a[normalize-space(.) = concat('Contacts', '')]", driver, executor);
        im.CustomClick("//one-app-nav-bar-item-root[3]//a[contains(@class,concat('slds-context-bar__label-action', ''))] | //a[normalize-space(.) = concat('Contacts', '')]", driver, executor, "click", action);
        Thread.sleep(1500);
        // verify 'Contacts' is on screen
        ExtentTestmanager.generatereport("verify 'Contacts' is on screen");
        im.waitTillElementGetsEnabled("//one-app-nav-bar-item-root[contains(@class,concat('slds-is-active', ''))]//a[contains(@class,concat('slds-context-bar__label-action', ''))] | //a[normalize-space(.) = concat('Contacts', '')]",driver,executor);
        Thread.sleep(1500);
        
        // Click {xpath: '//*[@class='forceActionLink' and @title='New']'}
        ExtentTestmanager.generatereport("Click {xpath: '//*[@class='forceActionLink' and @title='New']'}");
        im.waitTillElementGetsEnabled("//*[@class='forceActionLink' and @title='New']", driver, executor);
        im.CustomClick("//*[@class='forceActionLink' and @title='New']", driver, executor, "click", action);
        Thread.sleep(1500);
        // verify 'New Contact' is on screen
        ExtentTestmanager.generatereport("verify 'New Contact' is on screen");
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(@class,concat('inlineTitle', ''))] | //h2[normalize-space(.) = concat('New Contact', '')]")));
        Thread.sleep(1500);
        
        // click  {xpath: '//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']'}
        ExtentTestmanager.generatereport("click  {xpath: '//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']'}");
       // im.waitTillElementGetsEnabled("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu and (text(),"+salut+")]", driver, executor);
        //im.CustomClick("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu' and contains(text(),"+salut+")]", driver, executor, "action chain", action);
       // Thread.sleep(1500);
        // wait 2 secs
        ExtentTestmanager.generatereport("wait 2 secs");
       
        im.waitTillElementGetsEnabled("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']", driver, executor);
        im.CustomClick("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']", driver, executor, "click", action);
        
       // new Select(driver.findElement(By.xpath("//*[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']//option"))).selectByVisibleText(salut);
     //  WebElement element=newly_added_methods.return_element(driver, "//*[@class=\"select-options\"]/ul/li/a", salut);
    //	element.click();
    	
        
        
        // enter text in 'First Name'
        ExtentTestmanager.generatereport("enter text in 'First Name'");
        im.waitTillElementGetsEnabled("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor);
        im.CustomClick("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]", driver, executor, "click", action);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]"))
			.clear();
        driver.findElement(By.xpath("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]"))
			.sendKeys(first);
        Thread.sleep(1500);
     // enter text in 'last Name'
        ExtentTestmanager.generatereport("enter text in 'last Name'");
        im.waitTillElementGetsEnabled("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input", driver, executor);
        im.CustomClick("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input", driver, executor, "click", action);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input"))
			.clear();
        driver.findElement(By.xpath("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input"))
			.sendKeys(Last_Name);
        Thread.sleep(1500);
        // enter text in 'Phone'
        ExtentTestmanager.generatereport("enter text in 'Phone'");
        im.waitTillElementGetsEnabled("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor);
        im.CustomClick("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor, "click", action);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input"))
			.clear();
        driver.findElement(By.xpath("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input"))
		.sendKeys(phone);
        Thread.sleep(1500);
        // enter text in {xpath:'//*[@type='email']'}
        ExtentTestmanager.generatereport("enter text in {xpath:'//*[@type='email']'}");
        im.waitTillElementGetsEnabled("//*[@type='email']", driver, executor);
        im.CustomClick("//*[@type='email']", driver, executor, "click", action);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@type='email']"))
			.clear();
        driver.findElement(By.xpath("//*[@type='email']"))
			.sendKeys(email);
        Thread.sleep(1500);
        // enter text in 'Title'
        ExtentTestmanager.generatereport("enter text in 'Title'");
        im.waitTillElementGetsEnabled("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor);
        im.CustomClick("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input", driver, executor, "click", action);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input"))
			.clear();
        driver.findElement(By.xpath("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input"))
			.sendKeys(title);
        Thread.sleep(1500);
        // click  {xpath:'//*[@dir='ltr' and text()='Save']'}
        ExtentTestmanager.generatereport("click  {xpath:'//*[@dir='ltr' and text()='Save & New']'}");
        im.waitTillElementGetsEnabled("//*[@dir='ltr' and text()='Save']", driver, executor);
        im.CustomClick("//*[@dir='ltr' and text()='Save']", driver, executor, "click", action);
        Thread.sleep(1500);
        ExtentTestmanager.generatereport("Complete");
        Thread.sleep(5000);
        
        
        } 
        catch (Exception e){
            System.out.println(e.getMessage());
        }}
       
      
        }
	
	
	
	









	
}
