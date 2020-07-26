package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import dataProviders.GlobalFileReader;

public class BrowserFactory {

	private WebDriver driver;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	@SuppressWarnings("deprecation")
	public WebDriver getDriver(String browserToUSe, String testCaseId) {

		switch (browserToUSe) {

		case "chrome":

			System.setProperty(CHROME_DRIVER_PROPERTY, "lib/chromedriver.exe");

			String userDir = System.getProperty("user.dir");
			String downloadFolderPath = userDir + File.separator + "Test_Reports" + File.separator + testCaseId
					+ File.separator + "Download";

			Map<String, Object> preferences = new Hashtable<String, Object>();
			preferences.put("profile.default_content_settings.popups", 0);
			preferences.put("download.default_directory", downloadFolderPath);
			preferences.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			preferences.put("download.prompt_for_download", false);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", preferences);
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			break;

		}

		switch (browserToUSe) {

		case "safari":
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		int implicitWait = new GlobalFileReader().getImplicitlyWait();
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

		return driver;
	}

}
