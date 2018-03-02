package com.ibm.bp.testapp.pageobject;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.ibm.bp.testapp.utilities.*;

import io.appium.java_client.AppiumDriver;


public class CheckoutAddress_page {
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;


	public CheckoutAddress_page(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	@FindBy(xpath="//*[@name='processAddress']")
	WebElement processAddress;

	public void navigateToShipping() throws  IOException, NoSuchElementException, InterruptedException{
		processAddress.click();
		Thread.sleep(2000L);
		basics.printLineToReport("Verification", "User navigates to Shipping screen.", "pass");
		
	}
	
}