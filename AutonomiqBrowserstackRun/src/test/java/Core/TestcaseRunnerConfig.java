package Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;





public class TestcaseRunnerConfig {
	private String USERNAME;
	private String AUTOMATE_KEY;
	private  String URL; 
	
	
	DesiredCapabilities caps;
	String propfile=System.getProperty("user.dir")+"\\resources\\caps.properties";
	FileInputStream fis;
	Properties prop = new Properties();
	WebDriver driver;
	
public String setupRemoteUrl() {
		
		USERNAME=prop.getProperty("USERNAME");
	    AUTOMATE_KEY=prop.getProperty("AUTOMATE_KEY");
		URL="https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";	
		System.out.println(URL);
		return URL;	
		
	}
	
	
	public WebDriver returnDriver(String platform, String browser) throws Exception {
		
		
		switch (platform) {
		case "local":
			switch (browser) {
			case "ch":
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\ExeFiles\\chromedriver.exe");
				
				ChromeOptions option=new ChromeOptions();
				option.addArguments("--disable-notifications");
				driver=new ChromeDriver(option);
				driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				break;
			case "ff":
				
				break;	
			default:
				break;
			}
			
		case "windows":
        	driver=new RemoteWebDriver(new URL(URL), caps);
        	driver=new RemoteWebDriver(new URL(URL), caps);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        	break;	
			
			
			
        case "remote":
        	driver=new RemoteWebDriver(new URL(URL), caps);
			break;
		default:
			break;
		}
			
		
		
		return driver;	
	}
	
	
	public DesiredCapabilities setupCapability(String env) throws Exception {
		fis=new FileInputStream(propfile);
		prop.load(fis);
		caps=new DesiredCapabilities();
		if(env.equalsIgnoreCase("ios")) {
			caps.setCapability("browserstack.console", "warnings");
			    caps.setCapability("browserName", "iphone");
			    caps.setCapability("device", "iPhone 8 Plus");
			    caps.setCapability("realMobile", "true");
			    caps.setCapability("os_version", "11");
			    
			
			
		}
		else if (env.equalsIgnoreCase("IE")) {
			
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "IE");
			caps.setCapability("browser_version", "11.0");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.selenium_version", "3.5.2");
			
			
			
		}
		
        else if (env.equalsIgnoreCase("Edge")) {
			
        	caps.setCapability("os", "Windows");
        	caps.setCapability("os_version", "10");
        	caps.setCapability("browser", "Edge");
        	caps.setCapability("browser_version", "81.0 beta");
        	caps.setCapability("browserstack.local", "false");
        	caps.setCapability("browserstack.selenium_version", "3.5.2");
			
			
			
		} 
		else if(env.equalsIgnoreCase("safari")) {
			caps.setCapability("browserName", "Safari");
			caps.setCapability("browserVersion", "13.0");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "OS X");
			browserstackOptions.put("osVersion", "Catalina");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.141.0");
			caps.setCapability("bstack:options", browserstackOptions);

		}
		else if(env.equalsIgnoreCase("android")) {
			caps.setCapability("os_version", "10.0");
			caps.setCapability("device", "Samsung Galaxy S20 Ultra");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("browserstack.local", "false");
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--disable-notifications");
            caps.setCapability(ChromeOptions.CAPABILITY, option);
		}
		
		
		
		
		
		return caps;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
