package stepdefs.flightbookingportal;

import cucumber.api.java.en.Then;
import pages.actions.Label;
import stepdefs.common.Hook;
import stepdefs.common.SuperStepdefs;

public class FlightBooking_StepDef extends SuperStepdefs {
	
	private Label apexLabel;

	public FlightBooking_StepDef(Hook hook) {
		super(hook);
		// TODO Auto-generated constructor stub
		apexLabel = new Label(driver, testCaseId);
	}
	
	@Then("^From \"(.*)\" Page, Click On \"(.*)\" Label with object id as \"(.*)\"$")
	public void clickOnSpanLabel(String pageName, String labelName, String labelObj) throws Exception{
		apexLabel.clickOnLabelText(pageName, labelName, labelObj);
		logger.pass("From "+pageName+", Click On "+bold(labelName)+" Label");
	}

}
