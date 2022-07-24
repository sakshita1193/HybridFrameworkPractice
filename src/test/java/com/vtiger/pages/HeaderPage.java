package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.GenericMethods;

public class HeaderPage {
	
	private WebDriver driver;
	public GenericMethods gm;
	public ExtentTest log;

	public HeaderPage(WebDriver driver,ExtentTest log)
	{
		this.driver=driver;
		this.log=log;
		gm=new GenericMethods(driver,log);
		PageFactory.initElements(driver, this);
	}


	@FindBy(linkText="Logout")
	WebElement lnk_Logout;
	
	@FindBy(linkText="New Lead")
	WebElement lnk_NewLead;
	
	@FindBy(linkText="Leads")
	WebElement lnk_Leads;
	
	@FindBy(linkText="New Account")
	WebElement lnk_NewAccount;
	
	@FindBy(linkText="My Account")
	WebElement lnk_MyAccount;
	
	@FindBy(linkText="Home")
	WebElement lnk_Home;
	
	public void clickLogout()
	{
		gm.clickElement(lnk_Logout,"Link Logout");
	}
	
	public void clickNewLead()
	{
		gm.clickElement(lnk_NewLead, "Link New Lead");
	}
	
	public void clickMyAccount()
	{
		gm.clickElement(lnk_MyAccount,"Link My Account");
	}
	
	public void clickLeads()
	{
		gm.clickElement(lnk_Leads,"Link Leads");
	}
	
	public void clickHome()
	{
		gm.clickElement(lnk_Home,"Link Home");
	}
	
	public void clickNewAccount()
	{
		gm.clickElement(lnk_NewAccount,"Link New Account");
	}
}
