package stepdefs.common;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.EnvironmentFileReader;
import pages.actions.Button;
import pages.actions.DropDown;
import pages.actions.Frame;
import pages.actions.Label;
import pages.actions.Link;
import pages.actions.ListOfValue;
import pages.actions.TextField;
import pages.commom.CommonPage;

public class CommonPageSteps extends SuperStepdefs {

	private String pageName = "CommonPage";
	private Frame apexFrame;
	private TextField apexTextField;
	private Button apexButton;
	private Link apexLink;
	private DropDown apexDropDown;
	private ListOfValue apexListOfValue;
	private CommonPage commonPage;
	private Label apexLabel;
	

	public CommonPageSteps(Hook hook) {
		super(hook);
		apexFrame = new Frame(driver, testCaseId);
		apexTextField = new TextField(driver, testCaseId);
		apexButton = new Button(driver, testCaseId);
		apexLink = new Link(driver, testCaseId);
		apexDropDown = new DropDown(driver, testCaseId);
		apexListOfValue = new ListOfValue(driver, testCaseId);
		commonPage = new CommonPage(driver, testCaseId);
		apexLabel = new Label(driver, testCaseId);

	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Wait For "1" Second
	 * 
	 */
	@Then("^Wait For \"(.*)\" Second$")
	public void waitForSecond(int secondUnit) throws Exception {
		Thread.sleep(secondUnit * 1000);
		logger.pass("Wait For " + bold(secondUnit) + " Second");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Switch To Frame with Index "0"
	 * 
	 */
	@Then("^Switch To Frame with Index \"(.*)\"$")
	public void switchToFrameWithIndex(int frameIndex) throws Exception {
		apexFrame.apexSwitchToFrame(frameIndex);
		logger.pass("Switch To Frame with Index " + bold(frameIndex));
	}
	
	@When("^Go to URL after browser is open$")
    public void enterCredential() throws Throwable {
    	String url = new EnvironmentFileReader().getUrl();
    	commonPage.goTo(url);
    	logger.pass("URL entered successfully");
    }

	@Then("^Switch To Frame with Title \"(.*)\"$")
	public void switchToFrameWithTitle(String frametitle) throws Exception {
		apexFrame.apexSwitchToFrame(frametitle);
		logger.pass("Switch To Frame with Title " + bold(frametitle));
	}

	@Then("^Switch To Frame with Id \"(.*)\"$")
	public void switchToFrameWithId(String frameId) throws Exception {
		apexFrame.apexSwitchToFrameById(frameId);
		logger.pass("Switch To Frame with Id " + bold(frameId));
	}
	
	/**
	 * @author Sahil.Rohira
	 * 
	 *         Switch To Original Window
	 * 
	 */
	@Then("^Switch To Original Window$")
	public void switchToDefaultContent() throws Exception {
		apexFrame.apexSwitchToDefaultContent();
		logger.pass("Switch To Original Window");
	}

	@Then("^Filter Column \"(.*)\" with value \"(.*)\" by SearchIcon$")
	public void filterbySearchIcon(String filterColumn, String filterValue) throws Exception {
		if (getTempData(filterValue) != null)
			filterValue = getTempData(filterValue);
		apexButton.clickOnButtonwithTitle(pageName, "Select columns to search");
		apexButton.clickOnButtonInSpan(pageName, filterColumn);
		apexTextField.enterText(pageName, "search_field", filterValue);
		apexButton.clickOnButton(pageName, "Go");
		Thread.sleep(2000);
		logger.pass("Filter applied for Column " + bold(filterColumn) + " with value as " + bold(filterValue));
	}

	@Then("^Filter Column \"(.*)\" with value \"(.*)\" by SearchIcon for table \"(.*)\"$")
	public void filterbySearchIconWithTableName(String filterColumn, String filterValue, String tableName)
			throws Exception {
		if (getTempData(filterValue) != null)
			filterValue = getTempData(filterValue);
		apexButton.clickOnButtonwithTitleWithTableName(pageName, tableName, "Select columns to search");
		apexButton.clickOnButtonInSpan(pageName, filterColumn);
		apexTextField.enterTextWithTableId(pageName, "search_field", filterValue, tableName);
		apexButton.clickOnButton(pageName, "Go");
		Thread.sleep(2000);
		logger.pass("Filter applied for Column " + bold(filterColumn) + " with value as " + bold(filterValue));
	}

	@Then("^Clear the download folder$")
	public void clearandrecreateDownloadFolder() throws Exception {
		createDirectory("Test_Reports/" + this.testCaseId + "/Download/");
		logger.pass("Cleared the download folder for testcase " + this.testCaseId);
	}
	
	@Then("^For page \"(.*)\" click On \"(.*)\" button$")
	public void clickOnButtonWithDataTestId(String pageName, String buttonName) throws Exception {
		apexButton.clickOnButtonWithDataTestId(pageName, buttonName);
		logger.pass("Click On " + bold(buttonName) + " button");
	}
	
	
	@Then("^For page \"(.*)\" Click On \"(.*)\" Label CheckBox having object \"(.*)\"$")
	public void clickOnLabelCheckBox(String pageName,String labelName, String labelObj) throws Exception {
		apexLabel.clickOnLabelChkBox(pageName, labelName, labelObj);
		logger.pass("Click On " + bold(labelName) + " checkbox");
	}

	@Then("^Click On Link with text \"(.*)\"$")
	public void clickOnLinkText(String linkName) throws Exception {
		apexLink.clickonLinkwithText(pageName, linkName);
		logger.pass("Click On " + bold(linkName) + " Link");
	}
	
	@Then("^Click On Link with text contains \"(.*)\"$")
	public void clickOnLinkTextContains(String linkName) throws Exception {
		apexLink.clickonLinkwithContainsText(pageName, linkName);
		logger.pass("Click On " + bold(linkName) + " Link");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate "Receive PO" Link Is not Present
	 */
	@Then("^Validate \"(.*)\" Link Is not Present$")
	public void clickOnOrder(String buttonName) throws Exception {
		apexLink.validateLinkIsNotPresent(pageName, buttonName);
		logger.pass("Validate " + bold(buttonName) + " Link Is not Present");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate "Apply" Button Is Present Validate "Prev" Button Is Present
	 *         Validate "Cancel" Button Is Present
	 * 
	 */
	@Then("^Validate \"(.*)\" Button Is Present$")
	public void validateButtonIsPresent(String buttonName) throws Exception {
		apexButton.validateButtonIsPresent(pageName, buttonName);
		logger.pass("Validate " + bold(buttonName) + " Button Is Present");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate "Receive PO" Button Is not Present
	 */
	@Then("^Validate \"(.*)\" Button Is not Present$")
	public void validateButtonIsNotPresent(String buttonName) throws Exception {
		apexButton.validateButtonIsNotPresent(pageName, buttonName);
		logger.pass("Validate " + bold(buttonName) + " Button Is not Present");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Click On "Search" Button Click On "Reset" Button Click On "Cancel"
	 *         Button Click On "Download" Button Click On "Modify Search" Button
	 *         Click On "Apply Sub Type" Button Click On "Save" Button
	 * 
	 * 
	 */
	@Then("^Click On \"(.*)\" Button$")
	public void clickOnButton(String buttonName) throws Exception {
		apexButton.clickOnButton(pageName, buttonName);
		logger.pass("Click On " + bold(buttonName) + " Button");

	}

	@Then("^Click On \"(.*)\" Button with tableName \"(.*)\"$")
	public void clickOnButtononTable(String buttonName, String tableName) throws Exception {
		Thread.sleep(2000);
		apexButton.clickOnButtonwithTable(pageName, tableName, buttonName);
		logger.pass("Click On " + bold(buttonName) + " Button on Table " + bold(tableName));

	}

	

	@Then("^Click on Button with title \"(.*)\"$")
	public void clickOnButtonWithTitle(String titleName) throws Exception {
		apexButton.clickOnButtonwithTitle(pageName, titleName);
		logger.pass("Click On " + bold(titleName) + " Button");

	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Click On Label "Edit" Button
	 */
	@Then("^Click On Label \"(.*)\" Button$")
	public void clickOnEditButton(String buttonName) throws Exception {
		apexButton.clickOnLabelButton(pageName, buttonName);
		logger.pass("Click On Label  " + bold(buttonName) + " Button");
	}

	@Then("^Click On Span Label \"(.*)\" Button$")
	public void clickOnLabelButton(String buttonName) throws Exception {
		apexButton.clickOnSpanLabelButton(pageName, buttonName);
		logger.pass("Click On Span Label  " + bold(buttonName) + " Button");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Remove Existing Filter
	 * 
	 */
	@Then("^Remove Existing Filter$")
	public void removeFilter() throws Exception {
		apexButton.removeFilter(pageName);
		logger.pass("Remove Existing Filter");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate Text Field "COMMENTS" Text As "ABC"
	 * 
	 */
	@Then("^Validate Text Field \"(.*)\" Text As \"(.*)\"$")
	public void validateTextFieldText(String fieldName, String fieldValue) throws Exception {
		apexTextField.validateTextFieldText(pageName, fieldName, fieldValue);
		logger.pass("Validate Text Field " + bold(fieldName) + " Text As " + bold(fieldValue));
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate Text Field "DC" Value As "DC_CODE_1" Validate Text Field
	 *         "ORDER_NO" Value As "ORDER_NO1" Validate Text Field "STORER" Value As
	 *         "STORER_1" Validate Text Field "EXT_ORDER_NO" Value As "ORDER_NO1"
	 *         Validate Text Field "CONSIGNEE" Value As "CONSIGNEE_1" Validate Text
	 *         Field "APPT_NO" Value As "" Validate Text Field "APPT_DATE" Value As
	 *         "" Validate Text Field "COMMENTS" Value As "Test PO"
	 * 
	 *         Validate Text Field "DC" Value As "ABC"
	 * 
	 */
	@Then("^Validate Text Field \"(.*)\" Value As \"(.*)\"$")
	public void validateTextFieldValue(String fieldName, String fieldValue) throws Exception {
		apexTextField.validateTextFieldValue(pageName, fieldName, fieldValue);
		logger.pass("Validate Text Field " + bold(fieldName) + " Value As " + bold(fieldValue));
	}

	@Then("^Validate Text Area \"(.*)\" Value As \"(.*)\"$")
	public void validateTextAreaValue(String fieldName, String fieldValue) throws Exception {
		apexTextField.validateTextAreaValue(pageName, fieldName, fieldValue);
		logger.pass("Validate Text Field " + bold(fieldName) + " Value As " + bold(fieldValue));
	}

	@Then("^Validate Text Field \"(.*)\" Value As \"(.*)\" With Description$")
	public void validateTextFieldValueWithDesc(String fieldName, String fieldValue) throws Exception {
		apexTextField.validateTextFieldValue(pageName, fieldName, fieldValue);
		logger.pass("Validate Text Field " + bold(fieldName) + " Value As " + bold(fieldValue) + " With Description");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate Text Field "CONSIGNEE" Is Read Only
	 * 
	 */
	@Then("^Validate Text Field \"(.*)\" Is Read Only$")
	public void validateTextFieldIsReadOnly(String fieldName) throws Exception {
		apexTextField.validateTextFieldIsReadOnly(pageName, fieldName);
		logger.pass("Validate Text Field " + bold(fieldName) + " Is Read Only");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate Text Field "CONSIGNEE" Is Disabled
	 * 
	 */
	@Then("^Validate Text Field \"(.*)\" Is Disabled$")
	public void validateTextFieldIsDisabled(String fieldName) throws Exception {
		apexTextField.validateTextFieldIsDisabled(pageName, fieldName);
		logger.pass("Validate Text Field " + bold(fieldName) + " Is Disabled");
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Enter Text Field "COMMENTS" Value As "" Enter Text Field "STORER"
	 *         Value As "STORER_1" Enter Text Field "ORDER_NO" Value As "ORDER_NO1"
	 * 
	 */
	@Then("^Enter Text Field \"(.*)\" Value As \"(.*)\"$")
	public void enterTextFieldValue(String fieldName, String fieldValue) throws Exception {
		if (getTempData(fieldValue) != null)
			fieldValue = getTempData(fieldValue);
		apexTextField.enterText(pageName, fieldName, fieldValue);
		logger.pass("Enter Text Field " + bold(fieldName) + " Value As " + bold(fieldValue));
	}

	@Then("^Enter Text Area \"(.*)\" Value As \"(.*)\"$")
	public void enterTextAreaFieldValue(String fieldName, String fieldValue) throws Exception {
		if (getTempData(fieldValue) != null)
			fieldValue = getTempData(fieldValue);
		apexTextField.enterTextArea(pageName, fieldName, fieldValue);
		logger.pass("Enter Text Field " + bold(fieldName) + " Value As " + bold(fieldValue));
	}

	
	@Then("^Validate Drop Down \"(.*)\" Value Selected As \"(.*)\"$")
	public void validateDropDownValue(String fieldName, String fieldValue) throws Exception {
		if (getTempData(fieldValue) != null)
			fieldValue = getTempData(fieldValue);
		apexDropDown.validateDropDownValueSelected(pageName, fieldName, fieldValue);
		logger.pass("Validate Drop Down " + bold(fieldName) + " Value Selected As " + bold(fieldValue));
	}

	/**
	 * @author Sahil.Rohira
	 * 
	 *         Validate Drop Down "DC_CODE" Value Selected As "DC_CODE_1" With
	 *         Description Validate Drop Down "ORDER_TYPE" Value Selected As "NPO"
	 *         With Description // XPO Validate Drop Down "ORDER_SUB_TYPE" Value
	 *         Selected As "T" With Description //N Validate Drop Down
	 *         "ORDER_STATUS" Value Selected As "I" With Description //I,P,F,C
	 * 
	 */
	@Then("^Validate Drop Down \"(.*)\" Value Selected As \"(.*)\" With Description$")
	public void validateDropDownValueWithDescription(String fieldName, String fieldValue) throws Exception {
		if (getTempData(fieldValue) != null)
			fieldValue = getTempData(fieldValue);
		apexDropDown.validateDropDownValueSelected(pageName, fieldName, fieldValue);
		logger.pass("Validate Drop Down " + bold(fieldName) + " Value Selected As " + bold(fieldValue)
				+ " With Description");
	}

	@Then("^Select \"(.*)\" Value As \"(.*)\"$")
	public void selectDropDownValue(String fieldName, String fieldValue) throws Exception {
		if (getTempData(fieldValue) != null)
			fieldValue = getTempData(fieldValue);
		apexDropDown.selectDropDownValue(pageName, fieldName, fieldValue);
		logger.pass("From " + pageName + ", Select " + bold(fieldName) + " Value As " + bold(fieldValue));
	}

	@Then("^Select \"(.*)\" Value with Index as \"(.*)\"$")
	public void selectDropDownValueByIndex(String fieldName, int indexValue) throws Exception {
		apexDropDown.selectDropDownValueByIndex(pageName, fieldName, indexValue);
		logger.pass("From " + pageName + ", Select " + bold(fieldName) + " with Index Value As " + bold(indexValue));
	}

	/***
	 * @author Sahil.Rohira
	 * @param fieldName
	 * @param fieldValue
	 * @param frameNo
	 * @throws Exception
	 */
	@Then("^Validate From LOV \"(.*)\" Value As \"(.*)\" At Frame \"(.*)\" Is Not Present$")
	public void validateValueIsAbsentFromLOV(String fieldName, String fieldValue, int frameNo) throws Exception {

		apexListOfValue.validateValueIsAbsentFromLOV(pageName, fieldName, fieldValue, frameNo);
		logger.pass("Validate From LOV " + bold(fieldName) + " Value As " + bold(fieldValue) + " At Frame "
				+ bold(frameNo) + " Is Not Present");
	}

	

	@When("^Click On Span Text \"(.*)\" Button$")
	public void clickOnSpanTextButton(String buttonName) throws Exception {
		apexButton.clickOnSpanText(pageName, buttonName);
		logger.pass("From " + pageName + ", Click On " + bold(buttonName) + " Button");
	}
}
