package com.ibm.bp.testapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;


import io.appium.java_client.AppiumDriver;
import com.ibm.bp.testapp.utilities.*;




public class Checkout_SignIn_page {
	
	
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;


	public Checkout_SignIn_page(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	
	@FindBy(xpath=".//*[@id='email']")
	public WebElement email;
	@FindBy(xpath=".//*[@id='passwd']")
	public WebElement password;
	@FindBy(xpath=".//*[@id='SubmitLogin']")
	public WebElement submitlogin;

	
	
	public void login(ExcellReading excellReading,int rowno, String sheetname) throws IOException, NoSuchElementException, InterruptedException {
		System.out.println("Log in screen displayed");
		email.sendKeys(excellReading.getData(sheetname, rowno, "Username"));
		password.sendKeys(excellReading.getData(sheetname, rowno, "Password"));
		submitlogin.click();
		Thread.sleep(2000L);
		basics.printLineToReport("Verification", "User successfully logs in.", "pass");
		basics.printLineToReport("Verification", "User navigates to Address screen.", "pass");
		
	
	}
	

	
}



