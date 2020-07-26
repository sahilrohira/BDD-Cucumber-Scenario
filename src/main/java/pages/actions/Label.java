package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class Label extends CommonPage {
	
	public Label(WebDriver driver, String testCaseId) {
		super(driver,testCaseId);
	}
	
	public void validateLabelTextcontains(String pageName,String fieldName, String labelId,String expText) throws Exception {
		By labelElmnt 			= By.xpath("//span[contains(@id,'"+labelId+"']");
		validateTextContains(pageName, fieldName, labelElmnt, expText);
	}
	
	public void validateLabelText(String pageName,String fieldName, String labelId,String expText) throws Exception {
		By labelElmnt 			= By.xpath("//span[contains(@id,'"+labelId+"']");
		validateTextEquals(pageName, fieldName, labelElmnt, expText);
	}
	
	public void clickOnLabelText(String pageName,String labelName, String labelObj) throws Exception {
		By labelElmnt 			= By.xpath("//span[contains(@class,'tab-label')]//ancestor::button[@id='"+labelObj+"']");
		clickOnElement(pageName, labelName+" Link", labelElmnt);
	}
	
	public void clickOnLabelChkBox(String pageName,String labelName, String labelObj) throws Exception {
		By labelElmnt 			= By.xpath("//span[@id='"+labelObj+"']//ancestor::label");
		clickOnElement(pageName, labelName+" checkBox", labelElmnt);
	}
	
	
	
}
