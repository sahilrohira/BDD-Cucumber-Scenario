package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class TopMenu extends CommonPage{
	
	public TopMenu(WebDriver driver, String testCaseId) {
		super(driver,testCaseId);
	}

	public void clickOnLink(String pageName, String linkName) throws Exception {
		By topMenuLink					= By.xpath("//li/a[text()='"+linkName+"']");
		clickOnElement(pageName, linkName, topMenuLink);
	}
	
}
