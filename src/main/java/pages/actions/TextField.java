package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class TextField extends CommonPage {

	public TextField(WebDriver driver, String testCaseId) {
		super(driver, testCaseId);
	}

	public void enterText(String pageName, String fieldName, String text) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden')) ]");
		if (getTempData(text) != null)
			text = getTempData(text);
		clearAndType(pageName, fieldName, inputWithId, text);
	}

	public void enterTextWithTableId(String pageName, String fieldName, String text, String tableName)
			throws Exception {
		By inputWithId = By.xpath("//div[@id='" + tableName + "']//input[contains(@id,'" + fieldName
				+ "') and not(contains(@type,'hidden')) ]");
		if (getTempData(text) != null)
			text = getTempData(text);
		clearAndType(pageName, fieldName, inputWithId, text);
	}

	public void enterTextArea(String pageName, String fieldName, String text) throws Exception {
		By inputWithId = By.xpath("//textarea[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden')) ]");
		if (getTempData(text) != null)
			text = getTempData(text);
		clearAndType(pageName, fieldName, inputWithId, text);
	}

	public void validateTextFieldText(String pageName, String fieldName, String expText) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		if (getTempData(expText) != null)
			expText = getTempData(expText);
		validateTextEquals(pageName, fieldName, inputWithId, expText);
	}

	public void validateTextFieldValue(String pageName, String fieldName, String expText) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		if (getTempData(expText) != null)
			expText = getTempData(expText);
		validateElementAttribute(pageName, fieldName, inputWithId, "value", expText);
	}

	public void validateTextAreaValue(String pageName, String fieldName, String expText) throws Exception {
		By inputWithId = By.xpath("//textarea[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		if (getTempData(expText) != null)
			expText = getTempData(expText);
		validateElementAttribute(pageName, fieldName, inputWithId, "value", expText);
	}
	
	public String getTextFieldValue(String pageName, String fieldName) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		return getElementAttribute(pageName, fieldName, inputWithId, "value");
	}
	
	public String getTextAreaFieldValue(String pageName, String fieldName) throws Exception {
		By inputWithId = By.xpath("//textarea[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		return getElementAttribute(pageName, fieldName, inputWithId, "value");
	}

	public void validateTextFieldIsReadOnly(String pageName, String fieldName) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		validateElementAttribute(pageName, fieldName, inputWithId, "readonly", "true");
	}

	public void validateTextFieldIsDisabled(String pageName, String fieldName) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		validateElementAttribute(pageName, fieldName, inputWithId, "disabled", "true");
	}

	public void validateTextFieldTextwithIDEquals(String pageName, String fieldName, String expText) throws Exception {
		By inputWithId = By.xpath("//input[@id='" + fieldName + "' and not(contains(@type,'hidden'))]");
		if (getTempData(expText) != null)
			expText = getTempData(expText);
		validateTextEquals(pageName, fieldName, inputWithId, expText);
	}

	public void enterTexwithIDEquals(String pageName, String fieldName, String text) throws Exception {
		By inputWithId = By.xpath("//input[@id='" + fieldName + "' and not(contains(@type,'hidden'))]");
		if (getTempData(text) != null)
			text = getTempData(text);
		clearAndType(pageName, fieldName, inputWithId, text);
	}

	public void validateTextFieldValuewithIDEquals(String pageName, String fieldName, String expText) throws Exception {
		By inputWithId = By.xpath("//input[@id='" + fieldName + "' and not(contains(@type,'hidden'))]");
		if (getTempData(expText) != null)
			expText = getTempData(expText);
		validateElementAttribute(pageName, fieldName, inputWithId, "value", expText);
	}
	
	public void validateTextFieldValueContainswithIDEquals(String pageName, String fieldName, String expText) throws Exception {
		By inputWithId = By.xpath("//input[@id='" + fieldName + "' and not(contains(@type,'hidden'))]");
		if (getTempData(expText) != null)
			expText = getTempData(expText);
		validateElementAttributeContains(pageName, fieldName, inputWithId, "value", expText);
	}

	public void clickTextField(String pageName, String fieldName) throws Exception {
		By inputWithId = By.xpath("//input[contains(@id,'" + fieldName + "') and not(contains(@type,'hidden'))]");
		clickOnElement(pageName, fieldName, inputWithId);
	}
	
}
