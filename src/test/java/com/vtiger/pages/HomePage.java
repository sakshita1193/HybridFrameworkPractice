package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends HeaderPage{

	public HomePage(WebDriver driver,ExtentTest log) {
		super(driver,log);
		
	}
	
	@FindBy(xpath="//*[contains(text(),'My Upcoming and Pending Activities')]")
	WebElement lbl_MyUpcomingPendingActivities;
	
	@FindBy(xpath="//map[@name='pipeline']")
	WebElement Map_pipeline;
	
	public boolean checkPendingActivities()
	{
		return gm.elementDisplay(lbl_MyUpcomingPendingActivities, "MyUpcomingPendingActivities block");
	}
	
	public boolean checkMapPipelineDashboard()
	{
		return gm.elementDisplay(Map_pipeline, "My Pipeline Graph");
	}
	
	

}
