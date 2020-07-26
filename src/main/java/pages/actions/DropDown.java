package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class DropDown extends CommonPage {

	public DropDown(WebDriver driver, String testCaseId) {
		super(driver, testCaseId);
	}

	public void validateDropDownValueSelected(String pageName, String fieldName, String expFieldValue)
			throws Exception {

		By dropDown = By.xpath("//select[contains(@id,'" + fieldName + "')]");
		validateDropDownValueSelected(pageName, fieldName + "_DD", dropDown, expFieldValue);
	}

	public void selectDropDownValue(String pageName, String fieldName, String fieldValue) throws Exception {
		if (getTempData(fieldValue) != null) {
			fieldValue = getTempData(fieldValue);
		}
		By dropDown = By.xpath("//select[contains(@id,'" + fieldName + "')]");
		selectElementByVisibleText(pageName, fieldName + "_DD", dropDown, fieldValue);
	}

	public void selectDropDownValueByIndex(String pageName, String fieldName, int indexValue) throws Exception {
		By dropDown = By.xpath("//select[contains(@id,'" + fieldName + "')]");
		selectElementByIndex(pageName, fieldName + "_DD", dropDown, indexValue);
	}

}
