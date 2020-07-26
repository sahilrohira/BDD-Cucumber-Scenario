package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EnvironmentFileReader {

	private EnvironmentData environmentData;

	public EnvironmentFileReader() {
		String envName = (new GlobalFileReader()).getEnvironmentName();
		String envPropertyFilePath = (new StringBuilder("Test_Data/")).append(envName).append(".properties").toString();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(envPropertyFilePath));
			environmentData = new EnvironmentData();
			try {
				environmentData.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException((new StringBuilder(String.valueOf(envName))).append(".properties not found at ")
					.append(envPropertyFilePath).toString());
		}
	}

	public EnvironmentData getEnvironmentData() {
		return environmentData;
	}

	public String getUrl() {
		String url = environmentData.getProperty("url").trim();
		return url;
	}

}
