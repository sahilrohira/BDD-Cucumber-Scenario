package stepdefs.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.runtime.ScenarioImpl;
import dataProviders.GlobalFileReader;
import gherkin.formatter.model.Result;
import pages.commom.CommonPage;
import utils.Screenshot;
import utils.StaticConfiguration;
import utils.BrowserFactory;

public class Hook {

	private String testCaseId;
	private String testCaseDesc;
	private WebDriver driver;
	ExtentTest logger;

	@Before
	public void beforeScenario(Scenario scenario) {
		String scenarioName = scenario.getName();
		String temp[] = scenarioName.split("#");
		testCaseId = temp[0];
		testCaseDesc = temp[0];
		if (temp.length > 1) {
			testCaseDesc = temp[1];
		}

		String[] arrayScenarioId = scenario.getId().split(";");
		if (arrayScenarioId.length == 4) {
			// this is scenario Outline -- Ass this as part of test case Id
			int scenario_example_row_no = Integer.parseInt(arrayScenarioId[3]) - 1;
			this.testCaseId = this.testCaseId + "_" + scenario_example_row_no;
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Test_Data/TempData/" + testCaseId + ".properties"));
			bw.close(); // now this will create a test.txt in side Test folder
		} catch (IOException e) {
			e.printStackTrace();
		}

		final String downloadFolderPath = "Test_Reports/" + this.testCaseId + "/Download/";
		createDirectory(downloadFolderPath);
		final String screenshotFolderPath = "Test_Reports/" + this.testCaseId + "/Screenshot/";
		createDirectory(screenshotFolderPath);
		final String serverFolderPath = "Test_Reports/" + this.testCaseId + "/Server_File/";
		createDirectory(serverFolderPath);

		logger = StaticConfiguration.extent.createTest(testCaseId);
		logger.info("Started Running Test Case :- [<b>" + testCaseId + "  :-  " + testCaseDesc + "<b/>]");
		System.out.println("Started Running Test Case :- [" + testCaseId + "  :-  " + testCaseDesc + "]");

	}

	@Given("^Open Browser$")
	public void openBrowser() throws Throwable {
		String browserToUse = new GlobalFileReader().getBrowserToUse();
		driver = new BrowserFactory().getDriver(browserToUse, testCaseId);
	}

	@Given("^Go to URL \"(.*)\"$")
	public void goToUrl(String url) throws Throwable {
		CommonPage commonPage = new CommonPage(driver, testCaseId);
		commonPage.goTo(url);
		logger.pass("Go to URL [ <b> " + url + "</b> ]");
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException, IllegalArgumentException, IllegalAccessException {

		if (scenario.isFailed()) {
			String errorMessage = getErrorMessage(scenario);
			errorMessage = "<div style=\"font-family:Source Sans Pro;color:red;font-style:normal;\">" + errorMessage
					+ "</div>";
			String screenshotPath = Screenshot.getScreenshot(driver, testCaseId);
			logger.fail(errorMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

		logger.info("Completed Test Case :- [<b>" + testCaseId + "  :-  " + testCaseDesc + "<b/>]");
		StaticConfiguration.extent.flush();
		
		 if ( driver!=null) 
		 { 
			 driver.quit(); 
			 }
		 

		System.out.println("Completed Test Case :- [" + testCaseId + "  :-  " + testCaseDesc + "]");
	}

	private String getErrorMessage(Scenario scenario) throws IllegalArgumentException, IllegalAccessException {
		String errorMessage = "";
		Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
		for (Result result : results) {
			if (result.getError() != null)
				errorMessage = result.getError().getMessage();
		}
		return errorMessage;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public ExtentTest getLogger() {
		return logger;
	}


	private void createDirectory(final String downloadFolderPath) {
		final File dir = new File(downloadFolderPath);
		final String[] entries = dir.list();
		if (entries != null) {
			String[] array;
			for (int length = (array = entries).length, i = 0; i < length; ++i) {
				final String s = array[i];
				final File currentFile = new File(dir.getPath(), s);
				currentFile.delete();
			}
		}
		dir.mkdirs();
	}

}