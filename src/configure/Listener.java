/**
 *Copyright (c) 2014 Qburst Technologies, Inc. All Rights Reserved.
 */
package configure;

import java.util.HashMap;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import utility.Email;
import utility.PropertyFileRead;

public class Listener extends configure.Driver implements ISuiteListener,
		ITestListener {
	private static final String htmlFile = null;
	private int testRun = 0;
	private int testPassed = 0;
	private int testFailed = 0;
	private int testSkipped = 0;
	private String mailText;
	public static Map<Long, Boolean> passStatusMap = new HashMap<Long, Boolean>();
	public String filename;
	public String buildTime;
	public String buildDate;
	PropertyFileRead prop = new PropertyFileRead();

	@Override
	public void onStart(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		String text1 = "Started " + iTestResult.getTestContext().getName()
				+ "->" + iTestResult.getName();
		System.out.println("Started " + iTestResult.getTestContext().getName()
				+ "->" + iTestResult.getName());
		testRun++;
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		String text2 = iTestResult.getTestContext().getName() + "->"
				+ iTestResult.getName() + "--" + " passed";
		System.out.println(iTestResult.getTestContext().getName() + "->"
				+ iTestResult.getName() + "--" + " passed");
		testPassed++;

	}

	/**
	 * Method Name: onTestFailure() Description: This function will be called
	 * when a test case fails.
	 */
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String text3 = iTestResult.getTestContext().getName() + "->"
				+ iTestResult.getName() + "--" + " failed";
		System.out.println(iTestResult.getTestContext().getName() + "->"
				+ iTestResult.getName() + "--" + " failed");
		testFailed++;
		passStatusMap.put(Thread.currentThread().getId(), false);
		//Email e = new Email();
		String message = iTestResult.getTestContext().getName() + "-"
				+ iTestResult.getName();
	//	e.failureNotification(message, prop.getProperty("Sendermailid"));
	}

	/**
	 * Method Name: onTestSkipped() Description: This function will be called
	 * when a test case execution is skipped.
	 */
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		testSkipped++;
		passStatusMap.put(Thread.currentThread().getId(), false);
	}

	/**
	 * Method Name: onFinish() Description: This function will be called after
	 * all the test cases are executed. This method will also send a mail
	 * regarding the execution status.
	 */
	@Override
	public void onFinish(ISuite isuite) {

	}

	/**
	 * Method Name: onFinish() Description: This function will be called after
	 * all the test cases are executed. This method will also send a mail
	 * regarding the execution status.
	 */
	@Override
	public void onFinish(ITestContext iTestContext) {
		String testsRun = "Total Tests run: " + testRun;
		String testsPassed = " Tests passed: " + testPassed;
		String testsFailed = " Tests failed: " + testFailed;
		String testsSkipped = " Tests skipped: " + testSkipped;
		mailText = testsRun + "\n" + testsPassed + "\n" + testsFailed + "\n"
				+ testsSkipped + "\n\n";
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		// buildTime = getCurrentDate();
		// buildDate = getCurrentTimeStamp();
	}

}
