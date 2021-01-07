package Core;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestngBase {

	public static String run_env;
	public DesiredCapabilities caps;
	public static WebDriver driver;
	
	
	
@Parameters({"remotebrowser","platform"})
@BeforeClass
public void beforeClass(String remotebrowser,String platform)  {
	TestcaseRunnerConfig config=new TestcaseRunnerConfig();	
			run_env=remotebrowser;
			try {
				
				caps=config.setupCapability(platform);
				String URL=config.setupRemoteUrl();
				driver = config.returnDriver(remotebrowser, "ch");
			    System.out.println("Starting Browser");
			} catch (Exception e) {
				System.out.println("Somethings not correct : "+e.getMessage());
			}
		    
		       
		        	  
	  
  }


  public static WebDriver get_instance() {
	  
	  return driver;
  }
  
  public String return_runenv() {
	  
	  
	return run_env;
	    
  }
 
   
 @AfterClass
 public void tear_down() {
	if (driver!=null) { 
	 driver.quit();
	System.out.println("Closing browser session!!"); 
	
	}
 }
  
}
