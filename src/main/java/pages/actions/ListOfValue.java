package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class ListOfValue extends CommonPage {

	public ListOfValue(WebDriver driver, String testCaseId) {
		super(driver, testCaseId);
	}

	public void selectFromLOV_UsingSpanText(String pageName, String fieldName, String fieldValue, int frameNo)
			throws Exception {

		By lovBtn = By.xpath("//span[text()='Popup List of Values: " + fieldName + "']//ancestor::button");
		clickOnElement(pageName, fieldName + "_lovBtn", lovBtn);

		switchToDefaultContent();
		switchToFrame(frameNo - 1);
		By searchFieldTxt = By.id("SEARCH");
		enterText(pageName, "Search_Txt", searchFieldTxt, fieldValue);

		By searchButton = By.xpath("//input[@type='button' and @value='Search']");
		clickOnElement(pageName, "SearchButton", searchButton);

		By dropDownValue = By.xpath("//a[contains(.,\"" + fieldValue + "\")]");
		clickOnElement(pageName, fieldName + "_LOV", dropDownValue);

		if (frameNo == 1) {
			switchToDefaultContent();
		} else {
			switchToFrame(frameNo - 2);
		}

	}
	

	public void validateValueIsAbsentFromLOV(String pageName, String fieldName, String fieldValue, int frameNo)
			throws Exception {

		By lovBtn = By.xpath("//button[contains(@id,'" + fieldName + "_lov_btn')]");
		clickOnElement(pageName, fieldName + "_lovBtn", lovBtn);

		switchToDefaultContent();
		switchToFrame(frameNo - 1);
		By searchFieldTxt = By.id("SEARCH");
		enterText(pageName, "Search_Txt", searchFieldTxt, fieldValue);

		By searchButton = By.xpath("//input[@type='button' and @value='Search']");
		clickOnElementThenWait(pageName, "SearchButton", searchButton, 2);

		// By dropDownValue = By.xpath("//a[contains(.,\""+fieldValue+ "\")]");
		By dropDownValue = By.xpath("//a[text()=\"" + fieldValue + "\"]");
		validateElementIsNotPresent(pageName, fieldName + "_LOV", dropDownValue);
	}

	public void selectFromLOV_InTable(String pageName, String fieldName, String fieldValue, String frameTitle)
			throws Exception {

		By lovBtn = By.xpath("//td[@headers='" + fieldName + "]//a");
		clickOnElement(pageName, fieldName, lovBtn);

		switchToDefaultContent();
		switchToFrame(frameTitle);
		By searchFieldTxt = By.id("SEARCH");
		enterText(pageName, "Search_Txt", searchFieldTxt, fieldValue);

		By searchButton = By.xpath("//input[@type='button' and @value='Search']");
		clickOnElement(pageName, "SearchButton", searchButton);

		By dropDownValue = By.xpath("//a[contains(.,\"" + fieldValue + "\")]");
		clickOnElement(pageName, fieldName + "_LOV", dropDownValue);

		

	}

}
