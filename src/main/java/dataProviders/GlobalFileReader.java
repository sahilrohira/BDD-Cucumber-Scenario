package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalFileReader {

	private Properties properties;
	private final String propertyFilePath = "Test_Data/Global.properties";

	public GlobalFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Global.properties not found at " + propertyFilePath);
		}
	}

	public String getBrowserToUse() {
		String browserToUse= properties.getProperty("BrowserToUse");
		return browserToUse;
	}

	public String getEnvironmentName() {
		String environmentName = properties.getProperty("EnvironmentName");
		return environmentName;
	}

	public int getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWaitInSecond");
		if (implicitlyWait != null)
			return Integer.parseInt(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Global.properties file.");
	}

	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("ReportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Global.properties file for the Key:reportConfigPath");
	}

	public String getReportFilePath() {
		String spaceReportFilePath = properties.getProperty("ReportFilePath");
		if (spaceReportFilePath != null)
			return spaceReportFilePath;
		else
			throw new RuntimeException(
					"Report File Path not specified in the Global.properties file for the Key:reportFilePath");
	}

}
