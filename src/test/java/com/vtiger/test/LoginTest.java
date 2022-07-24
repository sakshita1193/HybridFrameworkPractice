package com.vtiger.test;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends BaseTest{
	
	public List<Map<String,String>> LoginData;

	
	
	@BeforeClass
	public void LaunchApp()
	{
		LoginData=readTestData("Login");
		StartApp();
	}
	
	@AfterClass
	public void CloseApp()
	{
		TearDown();
	}
	
	
	@Test
	public void ValidLogin()
	{
		log=extent.createTest("ValidLogin");
		LoginPage lp=new LoginPage(driver,log);
		lp.LogoDisplay();
		//lp.login("admin", "admin");
		lp.login(LoginData.get(0).get("Userid"), LoginData.get(0).get("Password"));
		extent.flush();
	}
	
	@Test
	public void ValidLogin2()
	{
		log=extent.createTest("ValidLogin2");
		LoginPage lp=new LoginPage(driver,log);
		lp.LogoDisplay();
		//lp.login("admin", "admin");
		lp.login(LoginData.get(0).get("Userid"), LoginData.get(0).get("Password"));
		extent.flush();
	}

}
