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

public class ShoppingCart_page {
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;


	public ShoppingCart_page(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement confirmationMessage;
	@FindBy(xpath="//h1[@id='cart_title']")
	public WebElement cartSummary;
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[1]/span")
	public WebElement totalProductCost;
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[2]/span")
	public WebElement TotalShippingCost;
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[3]/span")
	public WebElement TotalCost;
	@FindBy(xpath="//*[@id='layer_cart_product_title']")
	public WebElement ProductTitle;
	@FindBy(xpath="//*[@id='layer_cart_product_price']")
	public WebElement ProductPrice;
	@FindBy(xpath="//a[@title='Proceed to checkout']")
	public WebElement ProceedToCheckout;
	@FindBy(xpath="//*[@id='layer_cart_product_quantity']")
	public WebElement quantity;

	
	  
	public void CheckConfirmationMessage() throws IOException, NoSuchElementException, InterruptedException{
		String s=confirmationMessage.getText();
		if(s.equalsIgnoreCase("Product successfully added to your shopping cart")){
			System.out.println("Product successfully added to cart.");
			basics.printLineToReport("Verification", "Product successfully added to cart.", "pass");
			
		}
	}
	
	
	public void navigateToSummary()throws IOException, NoSuchElementException, InterruptedException{
		ProceedToCheckout.click();
		Thread.sleep(3000L);
		basics.printLineToReport("Verification", "User navigates to Checkout-Shopping cart summary page.", "pass");
		
		
	}
	

}
