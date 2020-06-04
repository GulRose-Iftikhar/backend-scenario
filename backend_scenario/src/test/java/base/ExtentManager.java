package base;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir") + "/reports/";
		String path = directory + fileName;
		System.out.println("path " + path);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Rest Assured Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Tajawal");
		extent.attachReporter(htmlReporter);

		return extent;
	}

	private static String getReportName() {
		Date today = new Date();
		String fileName = "AutomationReport_" + today.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}

}
