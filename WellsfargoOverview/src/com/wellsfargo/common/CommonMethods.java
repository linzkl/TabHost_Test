package com.wellsfargo.common;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class CommonMethods {
	public String chromePath = "D:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	public String iePath = "D:\\Selenium\\IEDriverServer_x64_2.25.2\\IEDriverServer";
	private WebDriver driver;
	static Logger logger = Logger.getLogger(CommonMethods.class);
	
	public WebDriver openBrowser(String sBrowserType){
		if(sBrowserType.equalsIgnoreCase("FireFox")){
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Firefox is opened");
		}
		else if (sBrowserType.equalsIgnoreCase("Chrome")){
			File chromedriver = new File(chromePath);
			System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());//chromePath
			//Capabilities = DesiredCapabilities.chrome();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//System.out.println("chrome browser is opened");
			logger.info("Chrome browser is opened");
		}
		else if (sBrowserType.equalsIgnoreCase("ie")){
			File iedriver = new File(iePath);
			System.setProperty("webdriver.ie.driver", iedriver.getAbsolutePath());
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("IE browser is opened");
		}
		else{
			System.out.println("please give the browser type");
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("Executed before class method successfully");
		return driver;
	}
	
	public void quitBrowser(){
		System.out.println("closing browser");
		driver.quit();
		System.out.println("Browser closed");
	}
	
	public void navigateURL(String baseURL){
		driver.get(baseURL);
		System.out.println("URL opened");
	}
	
	public void login(String username, String password, String sLoginButton, String loginId, String PWD){
		driver.findElement(By.name(username)).sendKeys(loginId);
		driver.findElement(By.name(password)).sendKeys(PWD);
		driver.findElement(By.name(sLoginButton)).click();
	}
	
	public void verifyText(WebDriver driver, String expected){
		this.driver = driver;
		try{
			driver.findElement(By.xpath("//*[contains(text(),'"+expected.trim()+"')]"));
			System.out.println("On page "+driver.getTitle()+". Expected Text \""+expected+"\" verified");
		}
		catch(NoSuchElementException e){
			System.out.println("On page "+driver.getTitle()+". Expected Text \""+expected+"\" not verified");
		
		}
	}
	
	public void verifyTitle(WebDriver driver, String expected) throws InterruptedException{
		this.driver = driver;
		boolean found = false;
		int count = 0;
		while(count<30){
			if(driver.getTitle().equalsIgnoreCase(expected)){
				System.out.println("On page "+driver.getTitle()+". Expect Title\""+expected+"\" verified" );
				found=true;
				break;
			}
			count++;
			Thread.sleep(1000);
			if(!found){
				System.out.println("On page "+driver.getTitle()+". Expect Title\""+expected+"\"verified");
			}
		}
	}
	
	public void selectDropdown(WebElement slocator,String value){
		List<WebElement> getDropDownValues = slocator.findElements(By.tagName("option"));
		boolean match = false;
		logger.info("Total no. of dropdown values:" + getDropDownValues.size());
		//System.out.println("Total no. of dropdown values:" + getDropDownValues.size());
		for(int i = 0; i<getDropDownValues.size(); ++i){
			System.out.println(getDropDownValues.get(i).getAttribute("value"));
			if(getDropDownValues.get(i).getAttribute("value").equalsIgnoreCase(value)){
				getDropDownValues.get(i).click();
				match = true;
				break;
			}
		}	
		if(!match){
				System.out.println("No Selection Found");
				Assert.fail(value + "Not found in the dropdown");
			}
		
		//waitForElement(value); 
		
		
	}
	
	private void waitForElement(String svalue) {
		// TODO Auto-generated method stub
		
	}

	
	public WebDriver goBack(WebDriver driver){
		driver.navigate().back();
		return driver;
	}
	
	public void setBrowser(WebDriver driver){
		this.driver = driver;
	}
	public WebDriver getDriver(){
		return this.driver;
	}
}
