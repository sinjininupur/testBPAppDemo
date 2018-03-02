package com.ibm.bp.testapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.ibm.bp.testapp.utilities.*;

import io.appium.java_client.AppiumDriver;

//import core.Core;

public class CheckoutShipping_page {
	
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;

	
	public CheckoutShipping_page(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	@FindBy(xpath="//*[@id='cgv']")
	public WebElement checkbox;
	@FindBy(xpath="//*[@name='processCarrier']")
	public WebElement processCarrier;

	
	public void processCarrier() throws IOException, NoSuchElementException, InterruptedException{
		checkbox.click();
		processCarrier.click();
		Thread.sleep(2000L);
		basics.printLineToReport("Verification", "User navigates to Payment screen.", "pass");
		
		
	}

}
