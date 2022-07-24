package com.vtiger.test;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class LeadTest extends BaseTest{
	
	public List<Map<String,String>> LeadData;
	
	
	@BeforeClass
	public void LaunchApp()
	{
		LeadData= readTestData("Leads");
		StartApp();
	}
	
	@AfterClass
	public void CloseApp()
	{
		TearDown();
	}
	
	
	@Test
	public void CreateLead()
	{
		log=extent.createTest("Create_Lead");
		LoginPage lp=new LoginPage(driver,log);
		lp.login(LeadData.get(0).get("Userid"), LeadData.get(0).get("Password"));
		HomePage hp=new HomePage(driver,log);
		hp.clickNewLead();
		LeadPage ldp= new LeadPage(driver,log);
		ldp.createLeadWithMandatoryFields(LeadData.get(0).get("LastName"), LeadData.get(0).get("Company"));
		ldp.clickLogout();
		extent.flush();
	}

}
