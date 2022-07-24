package com.vtiger.test;

import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.AccountPage;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class AccountTest extends BaseTest {
	
	public List<Map<String,String>> AccountData;

	@BeforeClass
	public void LaunchApp()
	{
		AccountData= readTestData("Accounts");
		StartApp();
	}
	
	@AfterClass
	public void CloseApp()
	{
		TearDown();
	}
	
	@Test
	public void CreateAccount()
	{
		log=extent.createTest("Create_Account");
		LoginPage lp=new LoginPage(driver,log);
		lp.login(AccountData.get(0).get("Userid"), AccountData.get(0).get("Password"));
		HomePage hp=new HomePage(driver,log);
		hp.clickNewAccount();
		AccountPage acp= new AccountPage(driver,log);
		acp.createAccount(AccountData.get(0).get("AccountName"));
		acp.verifyAccountName(AccountData.get(0).get("AccountName"));
		acp.ClickEditButton();
		acp.createAccount(AccountData.get(0).get("NewAccountName"));
		acp.verifyAccountName(AccountData.get(0).get("NewAccountName"));
		acp.createDuplicateAccount();
		acp.verifyAccountName(AccountData.get(0).get("AccountName"));
		acp.clickLogout();
		extent.flush();
	}
}
