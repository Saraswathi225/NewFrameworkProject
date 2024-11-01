package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onstart(ITestContext testContext) {
		
	/*	SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
		
	*/	
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName= "Test-Report-"+timestamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" +repName);
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User name", System.getProperty("user.name"));	
		
		String os =testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating Systems", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
		
				
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Hellooooooooooo");
	
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+"got successfully executed");
		
		}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		//test.log(Status.FAIL, "Test case FAILED cause is: " +result.getThrowable());
		
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		try {
			String impPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
		} 
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is:" +result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	
		
	}
	
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	}
	

	
	


