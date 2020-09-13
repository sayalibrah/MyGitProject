package com.aflac.base;

import org.junit.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static Response response;
	public static RequestSpecification httpRequest;
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest test;
	
	@BeforeClass
	public void setExtent()
	{
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter("./test-output/extentReports.html");
		htmlreporter.config().setDocumentTitle("Aflac API Testing");
		htmlreporter.config().setReportName("API Automation Report");
		htmlreporter.config().setTheme(Theme.DARK);
		extent.attachReporter(htmlreporter);
	}
}
