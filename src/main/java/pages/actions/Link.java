package pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.commom.CommonPage;

public class Link extends CommonPage {
	
	public Link(WebDriver driver, String testCaseId) {
		super(driver,testCaseId);
	}

	public void validateLinkIsPresent(String pageName, String linkName) throws Exception {
		By buttonLink 			= By.xpath("//a[text()='"+linkName+"']");
		waitForPresenceOfElement(pageName, linkName+" Link",buttonLink);
	}
	
	public void validateLinkIsNotPresent(String pageName, String linkName) throws Exception {
		By buttonLink 			= By.xpath("//a[text()='"+linkName+"']");
		validateElementIsNotPresent(pageName, linkName+" Link",buttonLink);
	}
	
	public void clickonLinkwithText(String pageName, String linkName) throws Exception {
		if(getTempData(linkName)!=null) {linkName=getTempData(linkName);}
		By linkObj		= By.xpath("//a[text()='"+linkName+"']");
		clickOnElement(pageName, linkName+" Link", linkObj);
	}
	
	public void clickonLinkwithContainsText(String pageName, String linkName) throws Exception {
		if(getTempData(linkName)!=null) {linkName=getTempData(linkName);}
		By linkObj		= By.xpath("//a[contains(text(),'"+linkName+"')]");
		clickOnElement(pageName, linkName+" Link", linkObj);
	}
	
	public void clickonLinkwithTextSpanContains(String pageName, String linkName) throws Exception {
		if(getTempData(linkName)!=null) {linkName=getTempData(linkName);}
		By linkObj		= By.xpath("//a/span[contains(text(),'"+linkName+"')]");
		clickOnElement(pageName, linkName+" Link", linkObj);
	}
	
	
} 