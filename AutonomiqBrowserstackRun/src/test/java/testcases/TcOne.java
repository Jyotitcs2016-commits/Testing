package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Core.TestngBase;

public class TcOne extends TestngBase {
	
	
	@Test
	public void tc1_open_browser() {
		
		driver.get("https://google.com");
		System.out.println("TC1 executed");
		Assert.assertTrue(driver.getTitle()=="Google");
		
	}
	
	
	
	
	
	

}
