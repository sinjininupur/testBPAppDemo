package com.ibm.bp.testapp.testcase;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ibm.bp.testapp.pageobject.*;
import com.ibm.bp.testapp.utilities.*;
//import com.qmetry.TestBPApp.ExcellReading;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.ibm.bp.testapp.utilities.ExcellReading;


public class AppTest extends basics
{
	
	SoftAssert softAssert;
	
	AppiumDriver<WebElement> driver;
	Checkout_SignIn_page checkout_signin_page;
	CheckoutAddress_page checkoutaddress_page;
	CheckoutPayment_page checkoutpayment_page;
	CheckoutShipping_page checkoutshipping_page;
	ShoppingCart_page shoppingcart_page;
	ShoppingCart_Summarypage shoppingcart_summarypage;
	Home_page home_page;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	ExcellReading excellReading;
	long starts;
	
	

	
	  
	@BeforeTest
	  @Parameters({"browsername","sheetname","deviceName"}) 
	  public void setUp(String browsername, String sheetname, String deviceName  ) throws Exception 
	{
	   // driver = new FirefoxDriver();
		excellReading = new ExcellReading();
	    baseUrl = "http://automationpractice.com/";
	    
		 driver=setcapabilities(deviceName,browsername);
	     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	     System.out.println("wait for 10 sec");
	     Thread.sleep(5000L);
	     checkout_signin_page = new Checkout_SignIn_page(driver, excellReading,softAssert);
	     checkoutaddress_page = new CheckoutAddress_page(driver, excellReading,softAssert);
	     checkoutpayment_page = new CheckoutPayment_page(driver, excellReading,softAssert);
	     checkoutshipping_page = new CheckoutShipping_page(driver, excellReading,softAssert);
	     shoppingcart_page = new ShoppingCart_page(driver, excellReading,softAssert);
	     shoppingcart_summarypage = new ShoppingCart_Summarypage(driver, excellReading,softAssert);
	     home_page = new Home_page(driver, excellReading,softAssert);
	    
	    
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), checkoutaddress_page);
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), checkout_signin_page);
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), checkoutpayment_page);
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), checkoutshipping_page);
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), shoppingcart_page);
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), home_page);
	     PageFactory.initElements(new AppiumFieldDecorator(driver, 20, TimeUnit.SECONDS), shoppingcart_summarypage);
	 	
	}

	
	@Parameters({"rowno","sheetname"}) 
	@Test
	public  void checkappnew(String rowno,String sheetname ) throws InterruptedException, IOException {
		
		  System.out.printf("%n[START] Thread Id : %s is started!",Thread.currentThread().getId());

		  long start = System.currentTimeMillis();
		   driver.get(baseUrl + "/index.php");
		   long finish = System.currentTimeMillis();
		   long totalTime = finish - start; 
		   System.out.println("Total Time for page load - "+totalTime); 
		   
			 //driver.get("http://automationpractice.com/index.php");
			 
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    
			 
			 
			   // driver.get(baseUrl + "/index.php");
			 home_page.signOut();
			 int rowno1=Integer.parseInt(rowno);
			home_page.navigateItemCategory(excellReading, rowno1, sheetname);
			String unitprice=home_page.selectItemAddToCart(excellReading, rowno1, sheetname);
			
			shoppingcart_page.CheckConfirmationMessage();
			shoppingcart_page.navigateToSummary();
			shoppingcart_summarypage.ProceedToCheckout();
			checkout_signin_page.login(excellReading, rowno1, sheetname);
			checkoutaddress_page.navigateToShipping();
			checkoutshipping_page.processCarrier();
			checkoutpayment_page.ValidateTotalPrice(unitprice, excellReading, rowno1, sheetname);
			checkoutpayment_page.selectBankwire();
		
			checkoutpayment_page.ValidatePaymentConfirmation();
			
			
	}
			
		/*
			if  (driver.findElement(By.xpath("//*[@id='block_top_menu']/div")).isDisplayed())
			{
				driver.findElement(By.xpath("//*[@id='block_top_menu']/div")).click();
			 // driver.findElement(By.cssSelector("div.cat-title.active")).click();
				
				 starts = new Date().getTime();
				 while(new Date().getTime() - starts < 2000L){}
				
				//starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			 // WebElement element = driver.findElement(By.xpath("(//*[text()='"+excellReading.getData(sheetname, 1, "Category")+"'])[1]"));
			  driver.findElement(By.linkText(excellReading.getData(sheetname, rowno1, "Category"))).click();
			   starts = new Date().getTime();
				 while(new Date().getTime() - starts < 2000L){}
			    //driver.findElement(By.xpath("(//*[text()='"+excellReading.getData(sheetname, rowno1, "Category")+"'])")).click();
			    driver.findElement(By.xpath("//*[@id='categories_block_left']/h2")).click();
			  // starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			  // driver.findElement(By.xpath("//a[contains(text(),'Dresses')][2]")).click();
			   
			   
			   //*[@id='categories_block_left']/div/ul//a[contains(text(),'Dresses')]
			    driver.findElement(By.xpath("//*[@id='categories_block_left']/div/ul//a[contains(text(),'" + excellReading.getData(sheetname, rowno1, "Subcat")+"')]")).click();
			    starts = new Date().getTime();
				 while(new Date().getTime() - starts < 2000L){}
				 //starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("//*[@id='categories_block_left']/h2")).click();
			    starts = new Date().getTime();
				 while(new Date().getTime() - starts < 2000L){}// starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			   
			   driver.findElement(By.xpath("//*[@id='categories_block_left']/div/ul//a[contains(text(),'" + excellReading.getData(sheetname, rowno1, "Cattype")+"')]")).click();
			   //*[@id='categories_block_left']/div/ul//a[contains(text(),'Evening Dresses')]
			 //   driver.findElement(By.xpath("(//a[contains(text(),"+ excellReading.getData(sheetname, rowno1, "Cattype")+")])[3]")).click();
			   starts = new Date().getTime();
				 while(new Date().getTime() - starts < 3000L){}// Thread.sleep(3000L);
			  
			//  (//*[@id='center_column']/ul/li/div/div[2]//a[contains(text(),'Printed Summer Dresses')])[1]
			    driver.findElement(By.xpath("(//*[@id='center_column']/ul/li/div/div[2]//a[contains(text(),'" +excellReading.getData(sheetname, rowno1, "Item")+ "')])[1]")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
				 //starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    
			    
			    
			    driver.findElement(By.xpath("//*[@id='add_to_cart']/button")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[@title='Proceed to checkout']")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.id("email")).click();
			    driver.findElement(By.id("email")).clear();
			    driver.findElement(By.id("email")).sendKeys("sinjininupur1@gmail.com");
			    driver.findElement(By.id("passwd")).clear();
			    driver.findElement(By.id("passwd")).sendKeys("jjjjsssoou");
			    driver.findElement(By.id("SubmitLogin")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.name("processAddress")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.id("cgv")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.name("processCarrier")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.cssSelector("a.bankwire > span")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.cssSelector("p.cheque-indent > strong.dark")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.linkText("Sign out")).click();
			}
			    
			    else
			    {
			    	
			   // driver.findElement(By.linkText("Women")).click();
			    driver.findElement(By.linkText(excellReading.getData(sheetname, rowno1, "Category"))).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("//*[@id='categories_block_left']/div/ul//a[contains(text(),'" + excellReading.getData(sheetname, rowno1, "Subcat")+"')]")).click();
			    Thread.sleep(5000L);
			    driver.findElement(By.xpath("//*[@id='categories_block_left']/div/ul//a[contains(text(),'" + excellReading.getData(sheetname, rowno1, "Cattype")+"')]")).click();
			    Thread.sleep(3000L);
			    driver.findElement(By.xpath("(//*[@id='center_column']/ul/li/div/div[2]//a[contains(text(),'" +excellReading.getData(sheetname, rowno1, "Item")+ "')])[1]")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			  
			    
			    
			    
			    driver.findElement(By.xpath("//*[@id='add_to_cart']/button")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[@title='Proceed to checkout']")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
		    
			    driver.findElement(By.id("email")).click();
			    driver.findElement(By.id("email")).clear();
			    driver.findElement(By.id("email")).sendKeys("sinjininupur1@gmail.com");
			    driver.findElement(By.id("passwd")).clear();
			    driver.findElement(By.id("passwd")).sendKeys("jjjjsssoou");
			    driver.findElement(By.id("SubmitLogin")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    
			    driver.findElement(By.name("processAddress")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			   // driver.findElement(By.name("processCarrier")).click();
			    //driver.findElement(By.cssSelector("a.fancybox-item.fancybox-close")).click();
			    driver.findElement(By.id("cgv")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.name("processCarrier")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.cssSelector("a.bankwire > span")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.id("center_column")).click();
			    starts = new Date().getTime();while(new Date().getTime() - starts < 2000L){}
			    driver.findElement(By.linkText("Sign out")).click();
			    }
	}*/

	 
	@AfterMethod
	  public void tearDown(ITestResult result) throws Exception {
		 driver.executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
	       
	  }
	
	
	protected void annotate(String text) {
      driver.executeScript("sauce:context=" + text);
    }
	@AfterClass
	  public void endOfTest() throws Exception {
			   
		    driver.quit();
		    
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      Assert.fail(verificationErrorString);
		    }
	  }
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

	}