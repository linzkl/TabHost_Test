package com.wellsfargo.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.log4testng.Logger;

import com.wellsfargo.common.CommonMethods;


public class FindTheRightLoanForYouPage {
	private WebDriver driver;
	CommonMethods CM = new CommonMethods();
	
	
	@FindBy(css="select#idLikeTo")
	@CacheLookup
	private WebElement idLikeToSelectorTag;
	
	@FindBy(css="select#myGoal")
	@CacheLookup
	private WebElement myGoalSelectorTag;
	
	@FindBy(css="select#newPropertyState")
	@CacheLookup
	private WebElement propertyStateTag;
	
	@FindBy(css="select#newPropertyCounty")
	@CacheLookup
	private WebElement propertyCountyTag;
	
	@FindBy(css="input#purchasePrice")
	@CacheLookup
	private WebElement purchasePriceInput;
	
	@FindBy(css="input#downPaymentAmount")
	@CacheLookup
	private WebElement downPaymentAmountInput;
	
	@FindBy(css="input#getResultsBtn.primary")
	@CacheLookup
	private WebElement getResultsButton;
	
	
	@FindBy(xpath="//input[@type='checkbox' and @id='prtId1']")
	@CacheLookup
	WebElement firstCheckBoxTag;
	
	@FindBy(css="input#prtId2")
	@CacheLookup
	WebElement secondCheckBoxTag;
	
	@FindBy(css="input#compareButtonBottom")
	@CacheLookup
	WebElement compareBtn;
	
	private Logger logger = Logger.getLogger(FindTheRightLoanForYouPage.class);
	
	public FindTheRightLoanForYouPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		
		//logger.info("\"Find the Right Loan for You Page\" initialized");
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
	public GetFinancingThatMeetsYourNeedsResultsPage BuyAHome() throws InterruptedException{
		
		CM.selectDropdown(myGoalSelectorTag, "BuyAHome");
		
		WebElement newAnnualHomeownersInsurance = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("newAnnualHomeownersInsurance")));
		
		CM.selectDropdown(idLikeToSelectorTag, "BuyVacationHome");
		
		purchasePriceInput.sendKeys("5000000");
		downPaymentAmountInput.sendKeys("4000000");
		newAnnualHomeownersInsurance.sendKeys("50000");
		
		CM.selectDropdown(propertyStateTag, "CA");
		
		(new WebDriverWait(driver,20)).until(ExpectedConditions.elementToBeClickable(propertyCountyTag));
		CM.selectDropdown(propertyCountyTag, "Monterey");
		getResultsButton.click();
		
		Thread.sleep(10000);
		return new GetFinancingThatMeetsYourNeedsResultsPage(this.driver);
	}
	
	
}
