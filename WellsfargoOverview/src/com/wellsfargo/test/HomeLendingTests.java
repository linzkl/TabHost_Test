package com.wellsfargo.test;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import com.wellsfargo.common.CommonMethods;
import com.wellsfargo.pages.FindTheRightLoanForYouPage;
import com.wellsfargo.pages.GetFinancingThatMeetsYourNeedsResultsPage;
import com.wellsfargo.pages.HomeLendingPage;
import com.wellsfargo.pages.HomePage;

public class HomeLendingTests {
	CommonMethods CM = new CommonMethods();
	HomePage HP;
	HomeLendingPage HLP;
	FindTheRightLoanForYouPage FTRLFYP;
	GetFinancingThatMeetsYourNeedsResultsPage GFTMYNRP;
	private String baseURL = "https://www.wellsfargo.com/";
	
	//@Parameters({"remoteBrowserType"})
	@BeforeClass(alwaysRun=true)
	public void setup(){//String remoteBrowserType   remoteBrowserType
		CM.openBrowser("chrome");
		System.out.println("chrome" + " started");//remoteBrowserType
		PropertyConfigurator.configure("log4j.properties");
		CM.navigateURL(baseURL);
	}
	
	@BeforeMethod
	public void navigate(){
	
	}
	
	@Test
	public void testHomePage() throws InterruptedException{
		HP = new HomePage(CM.getDriver());
		CM.verifyTitle(HP.getDriver(), "Wells Fargo - Personal & Business Banking - Student, Auto & Home Loans - Investing & Insurance");
		
		//CM.setBrowser(HP.getDriver());
	}
	
	@Test(dependsOnMethods={"testHomePage"})
	public void testHomeLending() throws InterruptedException{
		HLP = HP.navigateToHomeLending();
		//HLP = PageFactory.initElements(HP.getDriver(), HomeLendingPage.class);
		//HLP = PageFactory.initElements(HP.navigateToHomeLendingDriver(), HomeLendingPage.class);
		
		CM.verifyTitle(HLP.getDriver(), "Wells Fargo Home Loans");
		//CM.setBrowser(HLP.getDriver());
		HLP.verifyGoalTags();
	}
	
	@Test(dependsOnMethods={"testHomeLending"})
	public void testFindTheRightLoanForYou() throws InterruptedException{
		FTRLFYP = HLP.navigateToFindRightLoan();
		
		//FTRLFYP = PageFactory.initElements(HLP.navigateToFindRightLoanDriver(), FindTheRightLoanForYouPage.class);
		CM.verifyTitle(FTRLFYP.getDriver(), "Get Financing that Meets Your Needs | Wells Fargo");
		//FTRLFYP.BuyAHome();
		CM.verifyText(FTRLFYP.getDriver(), "Get Financing that Meets Your Needs");
		GFTMYNRP = FTRLFYP.BuyAHome();
		GFTMYNRP.CompareResults();
		//Thread.sleep(10000);
	}
	
	@AfterMethod
	public void browserWait(){
		CM.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		CM.quitBrowser();
		System.out.println("Browser closed");
	}
}
