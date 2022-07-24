package com.vtiger.test;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public static Properties prop;
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest log;
	
	@BeforeSuite
	public void SetupPreConditions()
	{
		readproperties();
		createReport();
	}
	
	public void StartApp()
	{

		//data=readTestData("Login");
		//System.out.println(data.get(1).get("Userid"));
		//System.out.println(data.get(1).get("Password"));	
		//System.exit(0);
		if(prop.getProperty("browser").equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(prop.getProperty("browser").equals("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		}
		if(prop.getProperty("browser").equals("edge"))
		{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		//driver.get("http://localhost:100");
		driver.get(prop.getProperty("AppURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("GlobalTimeout"))));
	}
	
	
	public void TearDown()
	{
		driver.quit();
	}
	
	public void readproperties() 
	{
		try {
		prop= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/setting.properties");
		prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Map<String,String>> readTestData(String Sheet) 
	{
		Connection connection=null;
		Recordset recordset=null;
		List<Map<String,String>> Allmap=null;
		try 
		{
		Fillo fillo=new Fillo();
		connection=fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx");
		String strQuery="Select * from " +Sheet;
		recordset=connection.executeQuery(strQuery);
		List<String> lst=recordset.getFieldNames();
		Allmap=new ArrayList<Map<String,String>>();
		
		while(recordset.next())
		{
			Map<String,String> map=new HashedMap<String, String>();
			//System.out.println(recordset.getField("Details"));
			for(int i=0;i<=lst.size()-1;i++)
			{
				//System.out.println(lst.get(i));
				map.put(lst.get(i), recordset.getField(lst.get(i)));
			}
			Allmap.add(map);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			recordset.close();
			connection.close();
		}
		return Allmap;
		
	}
	
	public void createReport()
	{
		DateFormat f= new SimpleDateFormat("yyyyMMddhhmmss");
		Date d=new Date();
		String str=f.format(d);
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vtiger/report/vTigerCrm"+str+".html");
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		htmlReporter.config().setDocumentTitle("Title of Report");
		htmlReporter.config().setReportName("Report Name");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
	}

}
