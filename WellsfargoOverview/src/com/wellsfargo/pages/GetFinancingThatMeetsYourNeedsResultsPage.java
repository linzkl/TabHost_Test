package com.wellsfargo.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GetFinancingThatMeetsYourNeedsResultsPage {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@type='checkbox' and @id='prtId1']")
	@CacheLookup
	WebElement firstCheckBoxTag;
	
	@FindBy(css="input#prtId2")
	@CacheLookup
	WebElement secondCheckBoxTag;
	
	@FindBy(css="input#compareButtonBottom")
	@CacheLookup
	WebElement compareBtn;
	
	@FindBy(css="a#graphButton")
	@CacheLookup
	WebElement viewAsGraphBtn;
	
	@FindBy(css="a#tableButton")
	@CacheLookup
	WebElement viewAsTableBtn;
	
	@FindBy(css="input.primary#compareReturnBtn")
	@CacheLookup
	WebElement returnToResultBtn;
	
	private Logger logger = Logger.getLogger(GetFinancingThatMeetsYourNeedsResultsPage.class);
	
	public GetFinancingThatMeetsYourNeedsResultsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		logger.info("GetFinancingThatMeetsYourNeedsResults initialized");
	}
	
	public void CompareResults(){
		logger.info(firstCheckBoxTag.toString());
		firstCheckBoxTag.click();
		secondCheckBoxTag.click();
		compareBtn.click();
		WebElement resultTable = (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(viewAsTableBtn));
		try{
			resultTable.click();
			//viewAsTableBtn.click();
			logger.info("View as a table verified");
			//logger.error("*******SJWI");
		} catch (NoSuchElementException e){
			//logger.info("Can not find the table view");
			logger.error("Cannot find the table view");
		}
		try{
			viewAsGraphBtn.click();
			logger.info("View as a graph verified");
		} catch (NoSuchElementException e){
			//logger.info("Can not find the graph view");
			logger.error("Cannot find the graph view");
		}	
		
		try{
			returnToResultBtn.click();
			logger.info("Back to result page");
		} catch (NoSuchElementException e){
			logger.info("Return failed");
		}
	}
}
