package pages.commom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;



public class CommonPage {

	protected Logger logger;
	protected WebDriver driver;
	protected String testCaseId;
	private static final int DEFAULT_EXPLICIT_WAIT_TIMEOUT = 120;

	public CommonPage(WebDriver driver, String testCaseId) {
		this.driver = driver;
		this.testCaseId = testCaseId;
		logger = Logger.getLogger(testCaseId);
	}

	/**
	 * Go to Given URL
	 * 
	 * @author Sahil.Rohira
	 */
	public void goTo(String url) throws Exception {
		String messageToDisplay = "Go To URL [" + url + "]";
		String errorMessageToDisplay = "Could not go To URL [" + url + "]";

		try {
			logger.info(messageToDisplay);
			driver.get(url);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Switch To Window With Title Containing word
	 * 
	 * @author Sahil.Rohira
	 */
	protected void switchToWindowWithTitleContaining(String windowTitle) throws Exception {
		String messageToDisplay = "Switch To Window With Title As [" + windowTitle + "]";
		String errorMessageToDisplay = "Could not Switch To Window With Title As [" + windowTitle + "]";
		Boolean found = false;
		try {

			logger.info(messageToDisplay);
			Set<String> allWindows = driver.getWindowHandles();
			for (String curWindow : allWindows) {
				driver.switchTo().window(curWindow);
				if (driver.getTitle().contains(windowTitle)) {
					found = true;
					return;
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		if (found == false) {
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Switch To Frame With Index
	 * 
	 * @author Sahil.Rohira
	 */
	protected void switchToFrame(int index) throws Exception {
		String messageToDisplay = "Switch To Frame With Index As [" + index + "]";
		String errorMessageToDisplay = "Could not Switch To Frame With Index As [" + index + "]";
		try {

			logger.info(messageToDisplay);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(index);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	protected void waitThenSwitchToFrame(String title) throws Exception {
		String messageToDisplay = "Switch To Frame With Title As [" + title + "]";
		String errorMessageToDisplay = "Could not Switch To Frame With Title As [" + title + "]";
		try {

			logger.info(messageToDisplay);
			driver.switchTo().defaultContent();
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			for (int i = 0; i <= frames.size(); i++) {
				new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frames.get(i)));
				if (frames.get(i).getAttribute("title").equals(title)) {
					driver.switchTo().frame(i);
					break;
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}

	}

	protected void switchToFrame(String title) throws Exception {
		String messageToDisplay = "Switch To Frame With Title As [" + title + "]";
		String errorMessageToDisplay = "Could not Switch To Frame With Title As [" + title + "]";
		try {

			logger.info(messageToDisplay);
			driver.switchTo().defaultContent();
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			for (int i = 0; i <= frames.size(); i++) {
				if (frames.get(i).getAttribute("title").equals(title)) {
					driver.switchTo().frame(i);
					break;
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}
	
	protected void switchToFrameById(String Id) throws Exception {
		String messageToDisplay = "Switch To Frame With Id As [" + Id + "]";
		String errorMessageToDisplay = "Could not Switch To Frame With Id As [" + Id + "]";
		try {

			logger.info(messageToDisplay);
			driver.switchTo().defaultContent();
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			for (int i = 0; i <= frames.size(); i++) {
				if (frames.get(i).getAttribute("id").contains(Id)) {
					driver.switchTo().frame(i);
					break;
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Switch To Default Content
	 * 
	 * @author Sahil.Rohira
	 */
	protected void switchToDefaultContent() throws Exception {
		String messageToDisplay = "Switch To Default Content";
		String errorMessageToDisplay = "Could not Switch To Default Content";
		try {

			logger.info(messageToDisplay);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Refresh Current Window
	 * 
	 * @author Sahil.Rohira
	 */
	protected void refreshCurrentWindow() throws Exception {
		String messageToDisplay = "Refresh Current Window";
		String errorMessageToDisplay = "Could not Refresh Current Window";
		try {

			logger.info(messageToDisplay);
			driver.navigate().refresh();
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Click On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void pressKeywords(String pageName, String fieldName, By by, Keys key) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [pressKey]    ###   Key [" + key + "]";
		String errorMessageToDisplay = "Could Not Press Key [" + key + "] At [" + fieldName + "]   On Page [ "
				+ pageName + "]";
		try {
			logger.info(messageToDisplay);
			driver.findElement(by).sendKeys(key);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Click On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void clickOnElement(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [Click]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Click On [" + fieldName + "]   On Page [ " + pageName + "]";
		try {
			logger.info(messageToDisplay);
			findElement(by).click();
		} catch (Exception e1) {
			try {
				clickOnELementUsingJS(findElement(by));
			} catch (Exception e) {
				logger.error(errorMessageToDisplay, e);
				throw new Exception(errorMessageToDisplay);
			}
		}
	}

	/**
	 * Click On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void doubleClickOnElement(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [DoubleClick]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Double Click On [" + fieldName + "]   On Page [ " + pageName + "]";
		try {
			logger.info(messageToDisplay);
			Actions actions = new Actions(driver);
			WebElement elementLocator = findElement(by);
			actions.doubleClick(elementLocator).perform();
		} catch (Exception e1) {
			try {
				clickOnELementUsingJS(findElement(by));
			} catch (Exception e) {
				logger.error(errorMessageToDisplay, e);
				throw new Exception(errorMessageToDisplay);
			}
		}
	}

	/**
	 * Click On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void clickOnElementWithText(String pageName, String fieldName, By by, String text) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ClickWithText]    Text [ " + text + "]###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Click On [" + fieldName + "]  With Text [" + text + "] On Page [ "
				+ pageName + "]";
		Boolean found = false;
		try {
			logger.info(messageToDisplay);
			List<WebElement> elements = findElements(by);
			for (int currentElementNo = 0; currentElementNo < elements.size(); currentElementNo++) {
				WebElement currentElement = elements.get(currentElementNo);
				scrollToElement(currentElement);
				String actualText = currentElement.getText().trim();
				if (actualText.contains("Sort")) {
					String lines[] = actualText.split("\n");
					actualText = lines[0];
				}
				if (actualText.equalsIgnoreCase(text)) {
					currentElement.click();
					found = true;
					return;
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}

		if (found == false) {
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Click On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void clickOnElementThenWait(String pageName, String fieldName, By by, int waitTimeInSec)
			throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [Click]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Click On [" + fieldName + "]   On Page [ " + pageName + "]";
		int iterationCount = 1, noOfIterations = 80;
		try {
			logger.info(messageToDisplay);
			findElement(by).click();
			Thread.sleep(waitTimeInSec * 1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			while (iterationCount < noOfIterations) {
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					System.out.println("Page Is loaded.");
					System.out.println("iteration count is -" + iterationCount);
					Thread.sleep(waitTimeInSec * 1000);
					break;
				} else {
					Thread.sleep(1000);
					iterationCount++;
				}
			}
		} catch (Exception e1) {
			try {
				clickOnELementUsingJS(findElement(by));
				Thread.sleep(waitTimeInSec * 1000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				while (iterationCount < noOfIterations) {
					if (js.executeScript("return document.readyState").toString().equals("complete")) {
						System.out.println("Page Is loaded.");
						System.out.println("iteration count is -" + iterationCount);
						Thread.sleep(waitTimeInSec * 1000);
						break;
					} else {
						Thread.sleep(1000);
						iterationCount++;
					}
				}
			} catch (Exception e) {
				logger.error(errorMessageToDisplay, e);
				throw new Exception(errorMessageToDisplay);
			}

		}
	}

	/**
	 * EnterText On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void enterText(String pageName, String fieldName, By by, String text) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [EnterText]    ###   Text [" + text + "]   ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Enter Text [" + text + "] On Page Name [ " + pageName
				+ "]  And  Field Name [" + fieldName + "]";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			element.sendKeys(text);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	

	protected void clickOnWinBrowsePopup(String pageName) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [Open_Btn]   ###   Action [Click]";
		String errorMessageToDisplay = "Could Not Click On [ Open_Btn]  On Page [ " + pageName + "]";
		try {
			logger.info(messageToDisplay);
			Pattern p = new Pattern(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "Open_Btn.PNG");
			Screen s = new Screen();
			s.click(p);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Clear And Type On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void clearAndType(String pageName, String fieldName, By by, String text) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ClearAndType]    ###   Text [" + text + "]   ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Clear and Type Text [" + text + "] On Page Name [ " + pageName
				+ "]  And  Field Name [" + fieldName + "]";
		if (getTempData(text) != null) {
			text = getTempData(text);
		}
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Clear Text On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void clearText(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ClearText]  ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Clear Text On Page Name [ " + pageName + "]  And Field Name ["
				+ fieldName + "]";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			element.clear();
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Select Date In Date Field by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void selectDateInDateField(String pageName, String fieldName, By by, String dateVal) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [Click]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Select Date On [" + fieldName + "]   On Page [ " + pageName + "]";
		try {
			logger.info(messageToDisplay);
			findElement(by).click();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date parse = sdf.parse(dateVal);
			Calendar c = Calendar.getInstance();
			c.setTime(parse);

			WebElement month = findElement(By.xpath("//select[@data-handler='selectMonth']"));
			// Select Month
			String[] shortMonths = new DateFormatSymbols().getShortMonths();
			if (Calendar.MONTH >= 0 && Calendar.MONTH <= 11) {
				String shortMonth = shortMonths[c.get(Calendar.MONTH)];
				Select mmDD = new Select(month);
				mmDD.selectByVisibleText(shortMonth);
			}

			// Select Year
			WebElement year = findElement(By.xpath("//select[@data-handler='selectYear']"));
			Select yyDD = new Select(year);
			yyDD.selectByVisibleText(Integer.toString(c.get(Calendar.YEAR)));

			// Select Date
			findElement(By.xpath("//a[text()=" + c.get(Calendar.DATE) + "]")).click();
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Get Selected DropDown Value by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateDropDownValueSelected(String pageName, String fieldName, By by, String expDDText)
			throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateDDTxt]    ###   Text [" + expDDText + "]   ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Validate Selected Text  [" + expDDText + "] On Page Name [ "
				+ pageName + "]  And Field Name [" + fieldName + "]";
		String actualDDText = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			Select select = new Select(element);
			actualDDText = select.getFirstSelectedOption().getText();

			if (!actualDDText.equals(expDDText)) {
				throw new Exception(errorMessageToDisplay + " Actual Text Found [" + actualDDText + "]");
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "Actual Text Found [" + actualDDText + "]");
		}
	}

	/**
	 * Select Element By Visible Text On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void selectElementByVisibleText(String pageName, String fieldName, By by, String text) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [SelectByVisibleTxt]    ###   Text [" + text + "]   ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Select Text  [" + text + "] On Page Name [ " + pageName
				+ "]  And Field Name [" + fieldName + "]";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			Select select = new Select(element);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}
	
	protected void selectElementByIndex(String pageName, String fieldName, By by, int index) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [SelectByIndex]    ###   Index [" + index + "]   ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Select for index  [" + index + "] On Page Name [ " + pageName
				+ "]  And Field Name [" + fieldName + "]";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			Select select = new Select(element);
			select.selectByIndex(index);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 

	protected void deselectAllOptionsOfDropDown(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [DeselectvAllDD_Option]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Deselect All Options On Page Name [ " + pageName
				+ "]  And Field Name [" + fieldName + "]";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			Select select = new Select(element);
			select.deselectAll();
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Get Element Count by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected int getElementCount(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ElementCount]    ###   Locator [" + by + "]";

		int count = 0;
		try {
			logger.info(messageToDisplay);
			List<WebElement> elements = findElements(by);
			count = elements.size();
		} catch (Exception e) {
		}
		return count;
	}

	/**
	 * Get Element No by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected int getElementNo(String pageName, String fieldName, By by, String searchText) throws Exception {
		// String messageToDisplay = "Page Name [ " +pageName+"] ### Field Name [" +
		// fieldName + "] ### Action [ElementNo] ### Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Get Element No For [" + fieldName + "] On Page Name [ " + pageName
				+ "]";

		int currentElementNo = 0;
		Boolean found = false;
		try {
			// logger.info(messageToDisplay);
			List<WebElement> elements = findElements(by);
			for (currentElementNo = 0; currentElementNo < elements.size(); currentElementNo++) {
				scrollToElement(elements.get(currentElementNo));
				String actualText = elements.get(currentElementNo).getText().trim();
				if (actualText.toLowerCase().contains(searchText.toLowerCase())) {
					found = true;
					return (currentElementNo + 1);
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}

		if (found == false) {
			throw new Exception(errorMessageToDisplay);
		}

		return (currentElementNo + 1);
	}

	protected int getElementNowithequalColumnName(String pageName, String fieldName, By by, String searchText)
			throws Exception {
		// String messageToDisplay = "Page Name [ " +pageName+"] ### Field Name [" +
		// fieldName + "] ### Action [ElementNo] ### Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Get Element No For [" + fieldName + "] On Page Name [ " + pageName
				+ "]";

		int currentElementNo = 0;
		Boolean found = false;
		try {
			// logger.info(messageToDisplay);
			List<WebElement> elements = findElements(by);
			for (currentElementNo = 0; currentElementNo < elements.size(); currentElementNo++) {
				scrollToElement(elements.get(currentElementNo));
				String actualText = elements.get(currentElementNo).getText().trim();
				if (actualText.toLowerCase().equalsIgnoreCase(searchText.toLowerCase())) {
					found = true;
					return (currentElementNo + 1);
				}
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}

		if (found == false) {
			throw new Exception(errorMessageToDisplay);
		}

		return (currentElementNo + 1);
	}

	/**
	 * Validate Element Count by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateElementCount(String pageName, String fieldName, By by, int expCount) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateElementCount]    ###   Expected Count [" + expCount + "]  ###   Locator ["
				+ by + "]";
		String errorMessageToDisplay = "Expected Count [" + expCount + "]  On Page Name [ " + pageName
				+ "]  And  Field Name [" + fieldName + "]";
		int actCount = 0;
		try {
			logger.info(messageToDisplay);
			List<WebElement> elements = findElements(by);
			actCount = elements.size();
		} catch (Exception e) {
		}

		if (actCount != expCount) {
			throw new Exception(errorMessageToDisplay + " Actual Count [" + actCount + "]");
		}
	}

	/**
	 * Get Validate Element Present
	 * 
	 * @author Sahil.Rohira
	 */
	protected Boolean isElementPresent(By by) throws Exception {
		Boolean found = false;
		try {
			int elementCount = driver.findElements(by).size();
			if (elementCount > 0) {
				found = true;
			}
		} catch (Exception e) {
		}

		return found;
	}

	/**
	 * Get Validate Element Present
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateElementPresent(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateElementPresent]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not Find Element [" + fieldName + "]On Page Name [ " + pageName + "]";

		Boolean found = false;
		try {
			logger.info(messageToDisplay);
			int elementCount = driver.findElements(by).size();
			if (elementCount > 0) {
				found = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		if (found != true) {
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Get Validate Element Present
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateElementIsNotPresent(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateElementNotPresent]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Element [" + fieldName + "] Was found On Page Name [ " + pageName + " ]";

		Boolean found = false;
		try {
			logger.info(messageToDisplay);
			int elementCount = driver.findElements(by).size();
			if (elementCount > 0) {
				found = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		if (found == true) {
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Wait For Presence Of Element by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected void waitForPresenceOfElement(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [WaitForPresence]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not be find [" + fieldName + "] in [" + DEFAULT_EXPLICIT_WAIT_TIMEOUT
				+ "] Seconds On Page [" + pageName + "]";

		Boolean found = false;
		try {
			logger.info(messageToDisplay);
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			found = true;
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		if (found == false) {
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Wait For Element To be Clickable by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected void waitForElementToBeClickable(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [WaitForElementTobeClickable]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not be Click [" + fieldName + "] in [" + DEFAULT_EXPLICIT_WAIT_TIMEOUT
				+ "] Seconds On Page [" + pageName + "]";
		try {
			logger.info(messageToDisplay);
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_TIMEOUT);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Wait For Element To be Invisible by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected void waitForElementToBeInvisible(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [WaitForElementTobeInvisible]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not be Click [" + fieldName + "] in [" + DEFAULT_EXPLICIT_WAIT_TIMEOUT
				+ "] Seconds On Page [" + pageName + "]";
		try {
			logger.info(messageToDisplay);
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
	}

	/**
	 * Get Element Attribute by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected String getElementAttribute(String pageName, String fieldName, By by, String attributeName)
			throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [GetAttributeValue]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not be Get Attribute Value For [" + fieldName + "] On Page [" + pageName
				+ "]";

		String attrValue = null;
		try {
			logger.info(messageToDisplay);
			attrValue = findElement(by).getAttribute(attributeName);
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		return attrValue;
	}

	/**
	 * Get Element Text by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected String getElementText(String pageName, String fieldName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [GetElementText]    ###   Locator [" + by + "]";
		String errorMessageToDisplay = "Could Not be Get Text For [" + fieldName + "] On Page [" + pageName + "]";

		String text = null;
		try {
			logger.info(messageToDisplay);
			text = findElement(by).getText().trim();
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		return text;
	}

	/**
	 * Get Element Text by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected String getElementText(String pageName, String fieldName, By by, int locatorNo, String innerTagNameIfAny)
			throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [GetElementText]    ###   Locator [" + by + "]  ### Locator No [" + locatorNo
				+ "]   ###  Inner Tag Name If Any [" + innerTagNameIfAny + "]";
		String errorMessageToDisplay = "Could Not be Get Text For [" + fieldName + "] On Page [" + pageName
				+ "] Using Locator No [" + locatorNo + "]";

		String text = null;
		try {
			logger.info(messageToDisplay);
			WebElement element = findElements(by).get(locatorNo - 1);
			if (!innerTagNameIfAny.equals("")) {
				element = findElement(element, By.tagName(innerTagNameIfAny));
			}
			scrollToElement(element);
			text = element.getText().trim();

		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		return text;
	}

	/**
	 * Get Element Text by locator
	 * 
	 * @author Sahil.Rohira
	 */
	protected String getElementText(By by, int locatorNo, String innerTagNameIfAny) throws Exception {
		String errorMessageToDisplay = "Could Not be Get Text Using Locator No [" + locatorNo + "]";

		String text = null;
		try {

			WebElement element = findElements(by).get(locatorNo - 1);
			if (!innerTagNameIfAny.equals("")) {
				element = findElement(element, By.tagName(innerTagNameIfAny));
			}
			scrollToElement(element);
			text = element.getText().trim();

		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}
		return text;
	}

	/**
	 * Get Table Cell Text
	 * 
	 * @author Sahil.Rohira
	 */
	protected String getTableCellText(String tableLocator, int rowNo, int columnNo) throws Exception {

		String text = null;
		try {
			By cellLabel = By.xpath(tableLocator + "//tr[" + rowNo + "]/td[" + columnNo + "]");
			text = findElement(cellLabel).getText().trim();
		} catch (Exception e) {
			throw new Exception("Could Not Get Text From Row [" + rowNo + "] ColumnNo [" + columnNo + "]");
		}
		return text;
	}

	/**
	 * Validate Text Equal On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateElementAttribute(String pageName, String fieldName, By by, String attributeName,
			String expAttrValue) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateAttribute_" + attributeName + "]    ###   Locator [" + by
				+ "] Expected Attribute Value [" + expAttrValue + "]";
		String errorMessageToDisplay = "Expected Attribute Value [" + expAttrValue + "]  Was not found On Page Name [ "
				+ pageName + "]  And  Field [" + fieldName + "]";

		Boolean matchFound = false;
		String actualAttrValue = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			actualAttrValue = element.getAttribute(attributeName).trim();
			if (actualAttrValue.equals(expAttrValue)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Attribute Value [" + actualAttrValue + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Attribute Value [" + actualAttrValue + "]");
		}

	}
	
	

	protected void loadingWait(String pageName, By by) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName
				+ "]  ###  Field Name [Loader]   ###   Action [waitforloader]";
		String errorMessageToDisplay = "Could Not find [ loader]  On Page [ " + pageName + "]";
		try {
			logger.info(messageToDisplay);
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_TIMEOUT);
			WebElement element = findElement(by);
			wait.until(ExpectedConditions.visibilityOf(element)); // wait for loader to appear
			wait.until(ExpectedConditions.invisibilityOf(element)); // wait for loader to disappear

		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay);
		}

	}

	/**
	 * Validate Text Equal On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateTextEquals(String pageName, String fieldName, By by, String expText) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateTextEquals]    ###   Locator [" + by + "] Expected Text [" + expText + "]";
		String errorMessageToDisplay = "Expected Text [" + expText + "]  Was not found On Page Name [ " + pageName
				+ "]  And  Field [" + fieldName + "]";

		Boolean matchFound = false;
		String actualText = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			actualText = element.getText().trim();
			if (actualText.equals(expText)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + actualText + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + actualText + "]");
		}

	}

	/**
	 * Validate Text Equal On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateTextEquals(String pageName, String fieldName, By by, int locatorNo, String innerTagNameIfAny,
			String expText) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateTextEquals]    ###   Locator [" + by + "] ### Locator No [" + locatorNo
				+ "]   ###  Inner Tag Name If Any [" + innerTagNameIfAny + "]    ### Expected Text [" + expText + "]";
		String errorMessageToDisplay = "Expected Text [" + expText + "]  Was not found On Page Name [ " + pageName
				+ "]  And  Field [" + fieldName + "] Using Locator No [" + locatorNo + "]";

		Boolean matchFound = false;
		String actualText = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElements(by).get(locatorNo - 1);
			if (!innerTagNameIfAny.equals("")) {
				element = findElement(element, By.tagName(innerTagNameIfAny));
			}
			scrollToElement(element);
			actualText = element.getText().trim();

			if (actualText.equals(expText)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + actualText + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + actualText + "]");
		}

	}

	/**
	 * Validate Text Contains On Element by locators
	 * 
	 * @author Sahil.Rohira
	 */
	protected void validateTextContains(String pageName, String fieldName, By by, String expText) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateTextEquals]    ###   Locator [" + by + "] Expected Text [" + expText + "]";
		String errorMessageToDisplay = "Expected Text [" + expText + "]  Was not found On Page Name [ " + pageName
				+ "]  And  Field [" + fieldName + "]";

		Boolean matchFound = false;
		String actualText = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			actualText = element.getText().trim();
			if (actualText.contains(expText)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + actualText + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + actualText + "]");
		}

	}

	protected WebElement findElement(By by) {
		scrollToElement(by);
		return driver.findElement(by);
	}

	private WebElement findElement(WebElement element, By by) {
		scrollToElement(element, by);
		return element.findElement(by);
	}

	private List<WebElement> findElements(By by) {
		scrollToElement(by);
		return driver.findElements(by);
	}

	private void clickOnELementUsingJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void scrollToTopOfThePage() {
		((JavascriptExecutor) driver).executeScript("scroll(250, 0)");
	}

	public void scrollToBottomOfThePage() {
		((JavascriptExecutor) driver).executeScript("scroll(0, 250)");
	}

	protected void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void scrollToElement(By by) {
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void validateElementAttributeContains(String pageName, String fieldName, By by, String attributeName,
			String expAttrValue) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateAttribute_" + attributeName + "]    ###   Locator [" + by
				+ "] Expected Attribute Value [" + expAttrValue + "]";
		String errorMessageToDisplay = "Expected Attribute Value [" + expAttrValue + "]  Was not found On Page Name [ "
				+ pageName + "]  And  Field [" + fieldName + "]";
		Boolean matchFound = false;
		String actualAttrValue = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			actualAttrValue = element.getAttribute(attributeName).trim();
			if (actualAttrValue.contains(expAttrValue)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Attribute Value [" + actualAttrValue + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Attribute Value [" + actualAttrValue + "]");
		}

	}

	protected void scrollToElement(WebElement element, By innerLocator) {
		element = element.findElement(innerLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	public void storeTempData(String key, String value) throws IOException {

		Properties prop = new Properties();
		InputStream in = new FileInputStream("Test_Data/TempData/" + testCaseId + ".properties");
		prop.load(in);
		in.close();
		OutputStream out = new FileOutputStream("Test_Data/TempData/" + testCaseId + ".properties");
		prop.setProperty(key, value);
		prop.store(out, null);
	}

	public String getTempData(String key) throws Exception {

		Properties prop = new Properties();
		InputStream in = new FileInputStream("Test_Data/TempData/" + testCaseId + ".properties");
		prop.load(in);
		String val = prop.getProperty(key);
		in.close();

		return val;
	}

	/*
	 * Convert Number 1,500.00 to 1500 then Validate
	 * 
	 * @author Sahil.Rohira
	 */
	protected void convertNumberAndValidateSpanTextContains(String pageName, String fieldName, By by, String expText)
			throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateTextEquals]    ###   Locator [" + by + "] Expected Text [" + expText + "]";
		String errorMessageToDisplay = "Expected Text [" + expText + "]  Was not found On Page Name [ " + pageName
				+ "]  And  Field [" + fieldName + "]";

		Boolean matchFound = false;
		String actualText = "";
		String truncatedValue = "";
		try {
			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			actualText = element.getText().trim();
			truncatedValue = actualText.split("\\.")[0].replace(",", "");
			if (truncatedValue.contains(expText)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + truncatedValue + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + truncatedValue + "]");
		}

	}

	/**
	 * Convert Number 1,500.00 to 1500 then Validate
	 * 
	 * @author Sahil.Rohira
	 */
	protected void convertNumberAndValidateTextContains(String pageName, String fieldName, By by, String attributeName,
			String expText) throws Exception {
		String messageToDisplay = "Page Name [ " + pageName + "]  ###  Field Name [" + fieldName
				+ "]   ###   Action [ValidateTextEquals]    ###   Locator [" + by + "] Expected Text [" + expText + "]";
		String errorMessageToDisplay = "Expected Text [" + expText + "]  Was not found On Page Name [ " + pageName
				+ "]  And  Field [" + fieldName + "]";

		Boolean matchFound = false;
		String actualAttrValue = "";
		String truncatedValue = "";
		try {
			logger.info(messageToDisplay);

			logger.info(messageToDisplay);
			WebElement element = findElement(by);
			actualAttrValue = element.getAttribute(attributeName).trim();
			truncatedValue = actualAttrValue.split("\\.")[0].replace(",", "");
			if (truncatedValue.contains(expText)) {
				matchFound = true;
			}
		} catch (Exception e) {
			logger.error(errorMessageToDisplay, e);
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + truncatedValue + "]");
		}

		if (matchFound == false) {
			throw new Exception(errorMessageToDisplay + "  Actual Text [" + truncatedValue + "]");
		}

	}

}