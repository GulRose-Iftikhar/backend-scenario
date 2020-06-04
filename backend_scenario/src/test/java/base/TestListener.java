package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestListener implements ITestListener {

	protected static ExtentReports extent = ExtentManager.createInstance();
	protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "::" + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Successful </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = result.getThrowable().getLocalizedMessage();
		extentTest.get().fail("<details><summary><b><font color=red>" + "Exception Occured, click to see details:"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");

		String logText = "<b> Test Method " + methodName + " Failed </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Skipped </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
}
