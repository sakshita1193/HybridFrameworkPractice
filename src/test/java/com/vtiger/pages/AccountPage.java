package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

public class AccountPage extends HeaderPage {

	public AccountPage(WebDriver driver, ExtentTest log) {
		super(driver, log);
	}

	@FindBy(name="accountname")
	WebElement tb_accountnme;
	
	@FindBy(name="button")
	WebElement btn_save;
	
	@FindBy(name="Edit")
	WebElement btn_edit;
	
	@FindBy(name="Duplicate")
	WebElement btn_duplicate;
	
	@FindBy(xpath="//td[text()='Account Name:']/following-sibling::td[1]")
	WebElement dt_AccountNameValue;
	
	public void createAccount(String value)
	{
		gm.enterValue(tb_accountnme, value, "Account Field");
		gm.clickElement(btn_save, "Save Button");
	}
	
	public void ClickEditButton()
	{
		gm.clickElement(btn_edit, "Edit Button");
	}
	
	public void verifyAccountName(String value)
	{
		gm.elementDisplay(dt_AccountNameValue, "Account Name Data");
	}
	
	public void createDuplicateAccount()
	{
		gm.clickElement(btn_duplicate, "Duplicate Button");
		gm.clickElement(btn_save, "Save Button");
	}
}
