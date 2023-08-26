package basePackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseClass implements ITestListener {
	ExtentReports extent = BaseClass.getExtentReport();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult tr) {
		test = extent.createTest(tr.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		test.log(Status.PASS, "Test case passed");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		String screenshotPath = null;
		test.log(Status.FAIL, "Test case failed");
		test.fail(tr.getThrowable());
		try {
			driver = (WebDriver) tr.getTestClass().getRealClass().getField("driver").get(tr.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			screenshotPath = getScreenshot(tr.getMethod().getMethodName(), driver);
		} catch (IOException e) {

			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, tr.getMethod().getMethodName());

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
