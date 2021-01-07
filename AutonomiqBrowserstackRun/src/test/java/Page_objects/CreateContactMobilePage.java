package Page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateContactMobilePage {
	
	WebDriver driver;

    By firstname = By.xpath("//label[@for=concat('178:1525;a', '')]/span/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')] | //span[normalize-space(.) = concat('First Name', '')]/ancestor::div[contains(@class,concat('uiInput', ''))]//input[@type=concat('text', '')]");
    By lastname=By.xpath("//*[text()=\"Last Name\"]//ancestor::label[@class='uiLabel-top form-element__label uiLabel']/following-sibling::input");
    By email= By.xpath("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input");
    By title=By.xpath("//*[text()=\"Title\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input");
    By phone=By.xpath("//*[text()=\"Phone\"]//ancestor::label[@class='label inputLabel uiLabel-left form-element__label uiLabel']/following-sibling::input");
    
   public CreateContactMobilePage(WebDriver driver) {
	   this.driver = driver;
	   
   }
   
   public void sendKeys(By element,String value) {
	   
	   driver.findElement(element).clear();
   	driver.findElement(element).sendKeys(value);
   }
    
   
   
    public void set_firstname(String first) {
    	sendKeys(firstname, first);
    	
    }
    public void set_lastname(String last) {
    	sendKeys(lastname, last);
    	
    }
    public void set_email(String email_no) {
    	sendKeys(email, email_no);
    	
    }
    public void set_phone(String phoneno) {
    	sendKeys(phone, phoneno);
    	
    }
    public void set_title(String titleof) {
    	sendKeys(title, titleof);
    	
    }
    
    
    
    
	

}
