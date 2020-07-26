package pages.actions;

import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class Frame extends CommonPage {
	
	public Frame(WebDriver driver, String testCaseId) {
		super(driver,testCaseId);
	}

	public void apexSwitchToFrame(int frameIndex) throws Exception {
		switchToFrame(frameIndex);
	}
	
	public void apexSwitchToFrame(String frameTitle) throws Exception {
		switchToFrame(frameTitle);
	}
	
	public void apexSwitchToFrameById(String frameId) throws Exception {
		switchToFrameById(frameId);
	}
	
	public void apexSwitchToDefaultContent() throws Exception {
		switchToDefaultContent();
	}
	
}
