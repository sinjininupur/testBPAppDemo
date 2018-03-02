package com.ibm.bp.testapp.pageobject;

import java.io.IOException;
import java.text.DecimalFormat;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.ibm.bp.testapp.utilities.*;

import io.appium.java_client.AppiumDriver;


public class CheckoutPayment_page {
	
	AppiumDriver<WebElement> driver;
	ExcellReading excellReading;
	SoftAssert softAssert;


	public CheckoutPayment_page(AppiumDriver<WebElement> driver, ExcellReading excellReading,SoftAssert softassert) {
		super();
		this.driver=driver;
		this.excellReading=excellReading;
		this.softAssert=softassert;
	}
	@FindBy(xpath="//*[@id='total_product']")
	public WebElement totalProductPrice;
	@FindBy(xpath="//a[@class='bankwire']/span")
	public WebElement bankwair;
	@FindBy(xpath="//*[@id='cart_summary']/tfoot/tr[3]/td[2]")
	public WebElement totalShippingPrice;
	@FindBy(xpath="//*[@id='cart_summary']/tfoot/tr[5]/td[2]/span")
	public WebElement totalPrice;
	@FindBy(xpath="//*[@id='cart_navigation']/button")
	public WebElement cartNavigate;
	@FindBy(xpath="//*[@id='center_column']/div")
	public WebElement cartresulttext;
	
	public void selectBankwire() throws InterruptedException{
		bankwair.click();
		Thread.sleep(2000L);
		basics.printLineToReport("Verification", "User selects payment mode-Bank Wire.", "pass");
		
		cartNavigate.click();
		Thread.sleep(2000L);
		basics.printLineToReport("Verification", "User confirms the order.", "pass");
		
	}
	
	public void ValidateTotalPrice(String unitprice, ExcellReading excellReading, int rowno1, String sheetname) throws IOException, NoSuchElementException, InterruptedException{
		String reportstr = null;
		String qty= excellReading.getData(sheetname, rowno1, "Quantity");
		 int quantity=Integer.parseInt(qty);
		 
		 Float unitpricedetails=Float.parseFloat(unitprice);
		 
		 
		// set totalcost value as unitprice*quantity
		 
		Float totalcost;
		totalcost = quantity*unitpricedetails;
		
		basics.printLineToReport("Verification", "Total product cost derived from calculation:unitprice*quantity:"+totalcost, "pass");
		
		String prodcost=totalProductPrice.getText();
		prodcost=prodcost.substring(1);
		
		Float productcost=Float.parseFloat(prodcost);
		basics.printLineToReport("Verification", "Total product cost as displayed in Payment Confirmation screen:"+productcost, "pass");
		 
		String shippingcost=totalShippingPrice.getText();
		shippingcost=shippingcost.substring(1);
		
		float shipcost=Float.parseFloat(shippingcost);

	
		 int retval = Float.compare(totalcost, productcost);
		    
	     
		if(retval >0 || retval<0)
		{
			basics.printLineToReport("Verification", "Total product cost wrongly displayed:"+totalcost +":"+productcost , "fail");
			
			
		}
		
		else
		{
			basics.printLineToReport("Verification", "Total product cost correctly displayed:"+totalcost , "pass");
			
			
  			
  			Float total=totalcost+shipcost;
  			basics.printLineToReport("Verification", "Total product and shipping cost derived from calculation:totalproductcost+shippingcost:"+total , "pass");
			
  			String totalp=totalPrice.getText();
  			totalp=totalp.substring(1);
  			 Float totalprice=Float.parseFloat(totalp);
  			basics.printLineToReport("Verification", "Total product and shipping cost as displayed in Payment Confirmation screen:"+totalprice, "pass");
  			
  		//	 int totalprice=Integer.parseInt(totalp);
  			 int retval2 = Float.compare(total, totalprice);
 		    
  		     
  			if(retval2 >0 || retval2<0)
  			{
  				
  				basics.printLineToReport("Verification", "Total product and shipping cost wrongly displayed:"+total +":"+totalprice , "fail");
  				
  				
  				
  	  			
  			}
  			else
  			{
  				basics.printLineToReport("Verification", "Total product and shipping cost correctly displayed:"+total, "pass");
  				
  				
  	  			
  			}
  			
  			
  			
		}
		
		
			
		
	}

public void ValidatePaymentConfirmation(){
		
		String paymenttext=cartresulttext.getText();
		String reportstr=null;
		
		basics.printLineToReport("Verification", "Confirmation message displayed:"+paymenttext, "pass");
			
		
	}
}
