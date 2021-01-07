package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import Core.TestngBase;

public class ExtentTestmanager {
	
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = Extentmanager.getInstance();
	
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public static void ontestfail(ITestResult result) throws Exception {
		
		WebDriver driver=TestngBase.get_instance();
		
		//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
                    //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 			
                    String screenshotPath = ScreenshotsOnFailure.getScreenshot(driver, result.getName());
		//To add it in the extent report 
		getTest().log(Status.FAIL, result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	
	}
	
	public static void generatereport(String message) {
		getTest().log(Status.INFO,message);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
