package visor.utility;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {

	ExtentHtmlReporter reporter = new ExtentHtmlReporter("./test-output/Rpt_BDD-OpenCart.html");
	ExtentReports extent = new ExtentReports();

	public void stepTestPass(String stepTestName) {
	
		ExtentTest logger;
		
		extent.attachReporter(reporter);
		logger = extent.createTest(stepTestName);
		logger.log(Status.INFO, stepTestName);
		logger.log(Status.PASS, stepTestName);
		extent.flush();
	}
	

	public void stepTestFail(String stepTestName) {
		ExtentTest logger;
		extent.attachReporter(reporter);
		logger = extent.createTest(stepTestName);
		logger.log(Status.INFO, stepTestName);
		logger.log(Status.FAIL, stepTestName);
		extent.flush();
	}
	
	
	
}
