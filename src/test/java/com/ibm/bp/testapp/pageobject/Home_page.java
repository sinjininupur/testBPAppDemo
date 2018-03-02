package com.ibm.bp.testapp.pageobject;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.ibm.bp.testapp.utilities.*;


import io.appium.java_client.AppiumDriver;

//import core.Core;

public class Home_page {
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;


	public Home_page(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	
	   
	 
	@FindBy(xpath="//*[@id='block_top_menu']/div")
	WebElement blockTopMenu;
	@FindBy(xpath="//*[@id='categories_block_left']/h2")
	WebElement leftDropdown;
	
	
	@FindBy(xpath="//*[@id='add_to_cart']/button")
	WebElement addToCartButton;
	@FindBy(xpath="//*[text()='Sign out']")
	WebElement signOutButton;
	@FindBy(xpath="//*[@id='quantity_wanted']")
	WebElement quantity;
	@FindBy(xpath="//*[@id='our_price_display']")
	public WebElement unitPrice;
	
	
	public void navigateItemCategory(ExcellReading excellReading, int rowno, String sheetname) {
		String reportstr = null;
		try{
	
			if (blockTopMenu.isDisplayed())
			{
				basics.printLineToReport("Verification", "Responsive menu - mobile displayed.", "pass");
				
				blockTopMenu.click();
				Thread.sleep(2000); 
			}
		
		}
		 catch(Exception NoSuchElementException)
	    {
			 basics.printLineToReport("Verification", "Responsive menu-Tab or browser  displayed", "pass");
				
	    }
	    try {
			driver.findElement(By.linkText(excellReading.getData(sheetname, rowno, "Category"))).click();
			Thread.sleep(2000); 
			 basics.printLineToReport("Verification", "User selects category:"+ excellReading.getData(sheetname, rowno, "Category"), "pass");
				
	    } catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    try{
	    if (leftDropdown.isDisplayed())
		{
	    	 basics.printLineToReport("Verification", "Responsive sub menu - mobile displayed", "pass");
				
	    	
			leftDropdown.click();
			 Thread.sleep(2000); 
		}
	    }
	    catch(Exception NoSuchElementException)
	    {
	    	 basics.printLineToReport("Verification", "Responsive sub menu-Tab or browser  displayed", "pass");
				
	    }
	    try {
			driver.findElement(By.xpath("//*[@id='categories_block_left']/div/ul//a[contains(text(),'" + excellReading.getData(sheetname, rowno, "Subcat")+"')]")).click();
			 Thread.sleep(2000); 
			 basics.printLineToReport("Verification", "User selects sub category:"+ excellReading.getData(sheetname, rowno, "Subcat"), "pass");
				
	    } catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   try{
	    if (leftDropdown.isDisplayed())
	  		{
	    	 basics.printLineToReport("Verification", "Responsive sub menu-Mobile  displayed", "pass");
				
	  			leftDropdown.click();
	  			Thread.sleep(2000); 
	  		}
	   }
	   catch(Exception NoSuchElementException)
	    {
		   basics.printLineToReport("Verification", "Responsive menu-Tab or browser  displayed", "pass");
			
	    }
	   try{
	    driver.findElement(By.xpath("//*[@id='categories_block_left']/div/ul//a[contains(text(),'" + excellReading.getData(sheetname, rowno, "Cattype")+"')]")).click();
	    Thread.sleep(2000); 
	    basics.printLineToReport("Verification", "User selects another subcategory:"+ excellReading.getData(sheetname, rowno, "Cattype"), "pass");
		
	   } catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	public String selectItemAddToCart(ExcellReading excellReading, int rowno, String sheetname) throws IOException, NoSuchElementException, InterruptedException{
		 
		driver.findElement(By.xpath("(//*[@id='center_column']/ul/li/div/div[2]//a[contains(text(),'" +excellReading.getData(sheetname, rowno, "Item")+ "')])[1]")).click();
		Thread.sleep(5000);   
		 basics.printLineToReport("Verification", "Item found:"+ excellReading.getData(sheetname, rowno, "Item"), "pass");
		 Thread.sleep(2000); 
		try{
			quantity.clear();
			quantity.sendKeys(excellReading.getData(sheetname, rowno, "Quantity"));
			basics.printLineToReport("Verification", "User sets quantity:"+ excellReading.getData(sheetname, rowno, "Quantity"), "pass");
			
		}
		catch(Exception e)
		{
			
		}
			
		addToCartButton.click();
		 basics.printLineToReport("Verification", "User clicks on Add to Cart button.", "pass");
		 basics.printLineToReport("Verification", "Shopping cart page displayed.", "pass");
				
		Thread.sleep(2000); 
		//driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[@title='Proceed to checkout']")).click();
		//Thread.sleep(2000);  
		String unitprice=unitPrice.getText();
		unitprice=unitprice.substring(1);
		basics.printLineToReport("Verification", "Unitprice for the item is:"+unitprice, "pass");
		
		return unitprice;
		
	}
	
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }
	
	  public void signOut() {
		    try{
		    	if(signOutButton.isDisplayed())
		    	{
		    		signOutButton.click();
		    		basics.printLineToReport("Verification", "User logs off.", "pass");
		    		
		    	}
					
		    }
		    catch(Exception NoSuchElementException)
		    {
		    
		    }
	  
	  }
	  
	  
}
