package com.wellsfargo.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class HomePage {

	WebDriver driver;
	private Logger logger = Logger.getLogger(HomePage.class);
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		logger.info("Home Page started");
	}
	
	public HomeLendingPage navigateToHomeLending(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try{
			js.executeScript("return $(\".navLevel1[data-navitem='loans']\").mouseover();");
			driver.findElement(By.linkText("Home Lending")).click();
			logger.info("home lending clicked");
		}catch(NoSuchElementException e){
			logger.info("home lending navigate button not found");
		}
		return new HomeLendingPage(driver);
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
	
}
