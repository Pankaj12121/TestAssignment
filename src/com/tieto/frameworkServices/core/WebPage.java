package com.tieto.frameworkServices.core;



import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tieto.frameworkExceptions.ScriptExecutionException;

public abstract class WebPage {
	protected WebDriver driver;
	protected WebDriverWrapper webDriverWrapper;
	protected FrameworkServices frameworkServices;

	public WebPage(WebDriver driver) {
		this.driver = driver;
		webDriverWrapper = new WebDriverWrapper(driver);
		frameworkServices = new FrameworkServices();

	}
	public WebPage(){

	}
	private WebElement waitForElementAndReturnElement(PageElement pageElement) {
		switch (pageElement.getWaitType()) {
		case WAITFORELEMENTTOBECLICKABLE:
			try {
				return webDriverWrapper.waitForElementToBeClickable(pageElement.getBy(), pageElement.getTimeOut());
			} catch (TimeoutException e) {
				throw new ScriptExecutionException (pageElement.getName() + " not loaded within specified wait time of "+ pageElement.getTimeOut(), e);
			}

		case WAITFORELEMENTTOBEEENABLED:
			try {
				return webDriverWrapper.waitForElementToBeEnabled(pageElement.getBy(), pageElement.getTimeOut());
			} catch (TimeoutException e) {
				throw new ScriptExecutionException (pageElement.getName() + " was disabled beyond specified wait time "+ pageElement.getTimeOut(), e);
			}

		case WAITFORELEMENTTOBEDISPLAYED:
			try {
				return webDriverWrapper.waitForElementToBeDisplayed(pageElement.getBy(), pageElement.getTimeOut());
			} catch (TimeoutException e) {
				throw new ScriptExecutionException (pageElement.getName() + " was not displayed in specified wait time "+ pageElement.getTimeOut(), e);
			}

		default:
			return driver.findElement(pageElement.getBy());
		}
	}

	protected void waitForPageElement(PageElement pageElement) {
		boolean isWebElementAvailableInPageElement = isWebElementAvailableInPageElement(pageElement);
		switch (pageElement.getWaitType()) {
		case WAITFORELEMENTTOBECLICKABLE:
			try {
				if (!isWebElementAvailableInPageElement)
					webDriverWrapper.waitForElementToBeClickable(pageElement.getBy(), pageElement.getTimeOut());
				else
					webDriverWrapper.waitForElementToBeClickable(pageElement.getWebElement(), pageElement.getTimeOut());

			} catch (TimeoutException e) {
				throw new ScriptExecutionException (pageElement.getName() + " was not clickable within specified wait time "+ pageElement.getTimeOut(), e);
			}
			break;

		case WAITFORELEMENTTOBEEENABLED:
			try {
				if (!isWebElementAvailableInPageElement)
					webDriverWrapper.waitForElementToBeEnabled(pageElement.getBy(), pageElement.getTimeOut());
				else
					webDriverWrapper.waitForElementToBeEnabled(pageElement.getWebElement(), pageElement.getTimeOut());
			} catch (TimeoutException e) {
				throw new ScriptExecutionException (pageElement.getName() + " was not loaded within specified wait time "+ pageElement.getTimeOut(), e);
			}
			break;

		case WAITFORELEMENTTOBEDISPLAYED:
			try {
				if (!isWebElementAvailableInPageElement)
					webDriverWrapper.waitForElementToBeDisplayed(pageElement.getBy(), pageElement.getTimeOut());
				else
					webDriverWrapper.waitForElementToBeDisplayed(pageElement.getWebElement(), pageElement.getTimeOut());

			} catch (TimeoutException e) {
				throw new ScriptExecutionException (pageElement.getName() + " was not displayed within specified wait time "+ pageElement.getTimeOut(), e);
			}
			break;
		default:
			break;
		}
	}

	private boolean isWebElementAvailableInPageElement(PageElement pageElement) {
		return !(pageElement.getWebElement() == null);
	}

	protected WebElement getWebElement(PageElement pageElement) {
		if (pageElement.isSlowLoadableComponent()) {
			return waitForElementAndReturnElement(pageElement);
		} else
			return driver.findElement(pageElement.getBy());
	}

	protected void sendKeys (PageElement pageElement, String value) {
		try {
			value = (value == null) ? "" : value;

			if (!isWebElementAvailableInPageElement(pageElement))
				getWebElement(pageElement).sendKeys(value);
			else
				pageElement.getWebElement().sendKeys(value);

			frameworkServices.logMessage("Typed Value: " + value + "' in " + pageElement.getName());

		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			pageElement = null;
		}
	}

	protected void sendKeys (PageElement pageElement, Keys key) {
		try {
			if (!isWebElementAvailableInPageElement(pageElement))
				getWebElement(pageElement).sendKeys(key);
			else
				pageElement.getWebElement().sendKeys(key);

			frameworkServices.logMessage("Typed Value: " + key + "' in " + pageElement.getName());

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			pageElement = null;
		}
	}

	protected void clearAndSendKeys (PageElement pageElement, String value) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();

			element.clear();
			element.sendKeys(value);

			frameworkServices.logMessage("Cleared and Typed Value: " + value + "' in " + pageElement.getName());

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			pageElement = null;
		}
	}


	protected void click(PageElement pageElement) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();


			((JavascriptExecutor)driver).executeScript("window.confirm = function(msg) { return true; }");
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			frameworkServices.logMessage("Clicked on: " + pageElement.getName());
			Thread.sleep(3000);

		} catch (Exception exception) {

		} finally {
			pageElement = null;
		}
	}

	protected void check(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			if (!webElement.isSelected())
				webElement.click();

			frameworkServices.logMessage("Checked  " + pageElement.getName());

		} catch (Exception exception) {

		} finally {
			pageElement = null;
		}
	}


	protected String getText(PageElement pageElement) {
		String text = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			text = webElement.getText().trim();

			frameworkServices.logMessage("Fetched text: " + text + " of " + pageElement.getName());

		} catch (Exception exception) {

		} finally {
			pageElement = null;
		}
		return text;
	}


	protected void selectValueFromList(PageElement pageElement, String value) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			Select select = new Select(webElement);
			select.selectByVisibleText(value);

			frameworkServices.logMessage("Selected Value: " + value + "' in " + pageElement.getName());

		} catch (Exception exception) {

		} finally {
			pageElement = null;
		}
	}

	protected void mouseOver(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(webElement).build().perform();

			frameworkServices.logMessage("Hoverd mouse on: " + pageElement.getName());

		} catch (Exception exception) {

		} finally {
			pageElement = null;
		}
	}

	protected void switchToTab() throws InterruptedException {

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.switchTo().window(tabs2.get(0));
		driver.close();
		driver.switchTo().window(tabs2.get(1));

	}



}

