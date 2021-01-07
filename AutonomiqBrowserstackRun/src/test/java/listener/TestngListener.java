package listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestngListener extends TestListenerAdapter {
	
	
	@Override
	public void onFinish(ITestContext Result) {
		
		System.out.println("Test finished");

	}

	@Override
	public void onStart(ITestContext Result) {

		System.out.println("Test started");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

	// When Test case get failed, this method is called.
	//WriteExcel we = new WriteExcel();

	@Override
	public void onTestFailure(ITestResult Result) {
		
		System.out.println("Test case failed");
	}

	// When Test case get Skipped, this method is called.
	@Override
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Test case skipped");
	}

	// When Test case get Started, this method is called.
	@Override
	public void onTestStart(ITestResult Result) {
		System.out.println("Test case started");
		
	}

	// When Test case get passed, this method is called.
	@Override
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Test case passed");
	}

	
	
	
	
	
	
	
	

}
