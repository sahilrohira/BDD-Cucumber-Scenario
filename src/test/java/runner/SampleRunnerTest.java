package runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dataProviders.GlobalFileReader;
import utils.StaticConfiguration;

@RunWith(Cucumber.class)

@CucumberOptions(features = {"Test_Features/Flight_Booking_Portal.feature"}, glue = { "stepdefs" }, tags = { "~@skip" }, monochrome = true)
public class SampleRunnerTest {

	@BeforeClass
	public static void setup() {

		ExtentHtmlReporter reporter = new ExtentHtmlReporter(new GlobalFileReader().getReportFilePath());
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("Automation Test Result");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		StaticConfiguration.extent = extent;
	}

}