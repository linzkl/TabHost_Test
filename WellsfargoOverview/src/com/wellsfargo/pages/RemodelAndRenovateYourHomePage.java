package com.wellsfargo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemodelAndRenovateYourHomePage {
	private WebDriver driver;
	
	@FindBy(linkText="Overview")
	@CacheLookup
	private WebElement overviewTag;
	
	@FindBy(linkText="Loan Options")
	@CacheLookup
	private WebElement loanOptionsTag;
	
	@FindBy(linkText="How to Apply")
	@CacheLookup
	private WebElement howToApplyTag;
	
	@FindBy(linkText="After You Apply")
	@CacheLookup
	private WebElement afterYouApplyTag;
	
	@FindBy(linkText="After You Close")
	@CacheLookup
	private WebElement afterYouCloseTag;
	
	//@FindBy(css="a.c62toggle")// > span:contains('What should I consider when thinking about my home improvement loan options?')")
	@FindBy(xpath="//a[@class='c62toggle']/span[text()='What should I consider when thinking about my home improvement loan options?']")
	@CacheLookup
	private WebElement whatShouldIConsiderWhenThinkingAboutMyHomeImprovementLoanOptions;
	
	public RemodelAndRenovateYourHomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		System.out.println("Remodel and Renovate Your Home Page initialized");
	}
	
	public void verifyItems(){
		//JavascriptExecutor JS = (JavascriptExecutor)driver;
		try{
			whatShouldIConsiderWhenThinkingAboutMyHomeImprovementLoanOptions.click();
			loanOptionsTag.click();
			howToApplyTag.click();
			afterYouApplyTag.click();
			afterYouCloseTag.click();
			System.out.println("Remodel Elements Found");
			for(int i=0;i<4;++i){
				driver.navigate().back();
			}
			
			} catch (NoSuchElementException e){
			System.out.println("no found elements");
		}	
	}
	
	public HomeLendingPage navigateToHomeLending(){
		try{
			overviewTag.click();
			System.out.println("home lending overview page backed");
		}catch(NoSuchElementException e){
			System.out.println("overview button not found");
		}
		return new HomeLendingPage(this.driver);
	}
	
	public WebDriver navigateToHomeLendingDriver(){
		try{
			overviewTag.click();
			System.out.println("home lending overview page backed");
		}catch(NoSuchElementException e){
			System.out.println("overview button not found");
		}
		return this.driver;
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
}
