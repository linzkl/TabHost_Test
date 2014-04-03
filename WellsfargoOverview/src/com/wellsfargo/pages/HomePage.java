package com.wellsfargo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		System.out.println("Home Page started");
	}
	
	public HomeLendingPage navigateToHomeLending(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try{
			js.executeScript("return $(\".navLevel1[data-navitem='loans']\").mouseover();");
			driver.findElement(By.linkText("Home Lending")).click();
			System.out.println("home lending clicked");
		}catch(NoSuchElementException e){
			System.out.println("home lending navigate button not found");
		}
		return new HomeLendingPage(driver);
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
	
}
