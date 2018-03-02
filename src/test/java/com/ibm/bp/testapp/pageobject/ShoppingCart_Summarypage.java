package com.ibm.bp.testapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.ibm.bp.testapp.utilities.*;

import io.appium.java_client.AppiumDriver;

//import core.Core;

public class ShoppingCart_Summarypage {
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;

	
	
	 // driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[@title='Proceed to checkout']")).click();
	  
	public ShoppingCart_Summarypage(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	//for 1st line item in shopping cart summary page
	@FindBy(xpath="//*[@id='cart_summary']/tbody/tr[1]/td[2]/p/a")
	public WebElement productName;
	@FindBy(xpath="// *[@id='cart_summary']/tbody/tr[1]/td[4]/span/span")
	public WebElement unitPrice;
	@FindBy(xpath="// *[@id='cart_summary']/tbody/tr[1]/td[5]/input[2]")
	public WebElement quantity;
	
	//total cost of all products in cart
	@FindBy(xpath="//*[@id='total_product']")
	public WebElement totalProductCost;
	@FindBy(xpath="// *[@id='total_shipping']")
	public WebElement totalShipping;
	@FindBy(xpath="// *[@id='total_price_without_tax']")
	public WebElement totalPriceWithoutTax;
	@FindBy(xpath="// *[@id='total_tax']")
	public WebElement totalTax;
	@FindBy(xpath="// *[@id='total_price']")
	public WebElement totalPrice;
	@FindBy(xpath="//*[@id='center_column']/p[2]/a[@title='Proceed to checkout']")
	public WebElement checkout;

	  
	
	
	public void ProceedToCheckout() throws  InterruptedException, NoSuchElementException{
		Thread.sleep(3000L);
		checkout.click();
		
		Thread.sleep(3000L);
		basics.printLineToReport("Verification", "User navigates to screen: Checkout-Sign in page", "pass");
		
		
		
	}

}
