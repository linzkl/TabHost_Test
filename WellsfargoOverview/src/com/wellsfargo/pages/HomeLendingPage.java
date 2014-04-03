package com.wellsfargo.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.wellsfargo.common.CommonMethods;

public class HomeLendingPage {
	private WebDriver driver;
	CommonMethods CM = new CommonMethods();
	
	@FindBy(linkText="Find the Right Loan for You")
	@CacheLookup
	private WebElement findTheRightLoanForYouTag;
	
	@FindBy(how=How.XPATH,using="//a[contains(@href, '/mortgage/buying-a-house/buying-a-vacation-home')]")
	@CacheLookup
	private WebElement buyAVacationHomeTag;
	
	@FindBy(how=How.LINK_TEXT,using="Renovate or Remodel Your Home")
	@CacheLookup
	private WebElement renovateOrRemodelYourHomeTag;
	
	public HomeLendingPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Home Lending Page initialized");
	}
	
	public void verifyGoalTags(){
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try{
			js.executeScript("return $(\"a[href='/mortgage/buying-a-house/']\").mouseover();");
		} catch (NoSuchElementException e) {
			System.out.println("\"Buy a Home\" not found");
		}
		try{
			buyAVacationHomeTag.click();
			CM.verifyTitle(driver, "Mortgage | Vacation & Second Home Mortgage | Learn the Basics | Wells Fargo");
			driver.navigate().back();
		} catch (InterruptedException e){
			System.out.println("\"Buy a vacation home\" not found");
		}
		
		try{
			js.executeScript("return $(\"a[href='/mortgage/home-improvement-loans/']\").mouseover();");
		} catch (NoSuchElementException e) {
			System.out.println("\"Improve your home\" not found");
		}
		try{
			renovateOrRemodelYourHomeTag.click();
			CM.verifyTitle(driver, "Remodel and Renovate Your Home | Wells Fargo");
			RemodelAndRenovateYourHomePage RARYHP = navigateToRemodelAndRenovate();
			//RemodelAndRenovateYourHomePage RARYHP = PageFactory.initElements(driver, RemodelAndRenovateYourHomePage.class);
			RARYHP.verifyItems();
			
			this.driver = RARYHP.navigateToHomeLendingDriver();
			//driver.navigate().back();
			System.out.println("navigated back");
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (InterruptedException e){
			System.out.println("\"Renovate or remodel your home\" not found");
		}
		
		try{
			js.executeScript("return $(\"a[href='/mortgage/debt-management/']\").mouseover();");
		} catch (NoSuchElementException e) {
			System.out.println("\"Manage Your Debt\" not found");
		}
		try{
			CM.verifyText(driver, "Pay Off Your Mortgage Sooner");
			//buyAVacationHomeTag.getTagName()
		} catch (NoSuchElementException e){
			System.out.println("\"Pay Off Your Mortgage Sooner\" not found");
		}
	}
	
	public RemodelAndRenovateYourHomePage navigateToRemodelAndRenovate(){
		return new RemodelAndRenovateYourHomePage(this.driver);
	}
	
	public FindTheRightLoanForYouPage navigateToFindRightLoan(){
		try{
			findTheRightLoanForYouTag.click();
			System.out.println("\"Find the Right Loan for You Page\" button clicked");
		}catch(NoSuchElementException e){
			System.out.println("\"Find the Right Loan for You Page\" navigate button not found");
		}
		return new FindTheRightLoanForYouPage(this.driver);
	}
	
	public WebDriver navigateToFindRightLoanDriver(){
		try{
			findTheRightLoanForYouTag.click();
			System.out.println("\"Find the Right Loan for You Page\" button clicked");
		}catch(NoSuchElementException e){
			System.out.println("\"Find the Right Loan for You Page\" navigate button not found");
		}
		return this.driver;
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
	
	
}
