package com.vtiger.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class GenericMethods {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest log;
	
	public GenericMethods(WebDriver driver,ExtentTest log)
	{
		this.driver=driver;
	    this.log=log;
	    wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	} 
	
	public void enterValue(WebElement elm, String val, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isEnabled();
		log.info("Checked that element id enabled");
		elm.clear();
		log.info("Cleared existing data from element");
		elm.sendKeys(val);
		if(elm.getAttribute("value").equals(val))
		{
			//System.out.println("Passed");
			log.pass(val +" has been entered successfully in Textbox " + msg);
		}
		else
		{
			//System.out.println("Failed");
			log.fail(val +" has not been entered successfully in Textbox "+msg+"<span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
		}
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			log.fail(val +" has not been entered successfully in Textbox "+msg+"  due to error" +e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
		}
		
	}
	
	public void clickElement(WebElement elm, String msg)
	{
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(elm));
			elm.click();
			//System.out.println("Passed");
			log.pass(msg+" element clicked successfully");
			
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			log.fail(msg+" element does not got clicked due to error " +e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
		}
		
	}
	
	public boolean elementDisplay(WebElement elm, String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			log.pass(msg+ " element is displayed successfully");
			return elm.isDisplayed();
			
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			log.fail(msg+ " element is not displayed successfully due to error "+e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
			return false;
		}
		
	}
	
	public boolean elementEnable(WebElement elm, String msg)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			log.pass(msg+ " element is enabled");
			return elm.isEnabled();
			
		}
		catch(Exception e)
		{
			//System.out.println(e.getMessage());
			log.fail(msg+ " element is not enabled");
			return false;
		}
		
	}
	
	public  String getScreenshot()
	{
		String destination=null;
		try 
		{
			String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts=(TakesScreenshot) driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			destination=System.getProperty("user.dir")+"/src/test/java/com/vtiger/report/screenshot/"+dateName+".png";
			File finalDestination=new File(destination);
			FileUtils.copyFile(source,finalDestination);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return destination;
	}

}
