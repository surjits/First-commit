package com.splashbi.pageobject;

import static com.splashbi.pageelement.HomePageElement.*;

import com.splashbi.pageelement.HomePageElement;
import com.splashbi.pageobject.admin.AdminPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage  extends BasePage {
	public AdminPage adminPage ;
	public DomainPage domainPage ;
	public ReportPage reportPage ;
	public DashboardPage dashboardPage ;
	public LoginPage loginPage ;

	Logger logger = Logger.getLogger(HomePage.class);
	public HomePage() {

	}
	public HomePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	public HomePage(WebDriver driver) {

		super(driver);
	}
	public void setTest(ExtentTest test) {
		this.setExtentTest(test);

	}
	public void navigateToPage(String pagename){
		HomePageElement page=null;
		switch(pagename){
			case "admin":
				page = ADMINISTRATOR;
				break;
			case "domain":
				page = DOMAIN;
				break;
			case "report":
				page = REPORT;
				break;
			case "dashboard":
				page = DASHBOARD;
				break;
		}
		try {
			clickButton(page);
			waitForInvisibilityOfLoader();
			while(isElementDisplayed(HOME)) {
				clickButton(page);
				waitForInvisibilityOfLoader();
			}
			test.log(LogStatus.PASS,"Navigated to page"+" "+pagename);

		} catch (Exception e) {
			test.log(LogStatus.ERROR, printError(e,2));
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.FAIL,"Failed to navigate to "+" "+pagename);
		}

	}
	public AdminPage navigateToAdminPage()  {
		navigateToPage("admin");
		return new AdminPage(driver);
	}
	public DomainPage navigateToDomainPage() {
		navigateToPage("domain");
		return new DomainPage(driver);
	}
	public DashboardPage navigateToDashboardPage()  {
		navigateToPage("dashboard");
		return new DashboardPage(driver);
	}

	public ReportPage navigateToReportPage() {
		navigateToPage("report");
		return new ReportPage(driver);
	}

	public boolean verifyHomePage()  {
		logger.info("Entering method verifyHomePage()");
		boolean present = false;
		try {
			waitForVisibilityOfElement(LOGOUT);
			if(isElementDisplayed(LOGOUT)) {
				present = true;
				//test.log(LogStatus.PASS, "Login Successfull and Entered Home Page:"+test.addScreenCapture(addScreenshot()));
			}
		}catch(Exception e) {
			logger.error("Could not land in home page:",e);
			test.log(LogStatus.FAIL,"Could not enter Home Page");
			throw e;
		}
		return present;

	}
	public LoginPage logOut(){

		try{
			clickButton(LOGOUT);
			waitForVisibilityOfElement(VERIFY_LOGOUT);
			if(isElementPresent(VERIFY_LOGOUT)){

			}
		}catch(Exception e){
			test.log(LogStatus.FAIL,"Could not Logout");
		}
		return new LoginPage(driver);
	}


}
