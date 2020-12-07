package com.splashbi.pageobject;

import com.splashbi.utility.Constant;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.utility.Utility;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.splashbi.pageelement.DomainPageElement.DB_CONNECTOR_LIST;
import static com.splashbi.pageelement.ReportPageElement.*;
import static com.splashbi.pageelement.DomainPageElement.DOMAIN_HEADER;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.HomePageElement.*;
import static com.splashbi.pageelement.admin.setup.UserGroupPageElement.CREATE_USER_GROUP;

public class ReportPage extends BasePage {

	public ReportPage(ExtentTest test) {
		super(test);
	}

	public ReportPage(WebDriver driver) {
		super(driver);

	}

	public void setTest(ExtentTest test) {
		this.setExtentTest(test);

	}
	String reportName;

	public boolean isReportPageOpen(){
        waitForElementToBePresent(VERIFY_ALL_OMAINS_TABLE);
		if(isElementPresent(VERIFY_ALL_OMAINS_TABLE)){
			return true;
		}
		else{
			return false;
		}

	}
	public String createReport(String domainName,String tablename) {
		try {

			reportName=Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"reportname"));
			clickButton(CREATE_DROPDOWN);
			clickButton(CREATE_REPORT);
			waitForVisibilityOfElement(DOMAIN_SEARCH_FIELD);
			waitForVisibilityOfElement(VERIFY_DOMAINS_HEADER);
			inputText(DOMAIN_SEARCH_FIELD,domainName);
			hitEnterKey(DOMAIN_SEARCH_FIELD);
			clickButton(DOMAIN_NAME_IN_REPORT,domainName);
			waitForVisibilityOfElement(EXPAND_DOMAIN_TABLE,tablename.toUpperCase());
			//clickButton(EXPAND_DOMAIN_TABLE,tablename.toUpperCase());
			clickButton(SELECT_ALL_CHECKBOX,tablename.toUpperCase());
			clickButton(MOVE_TO_RIGHT);
			waitForElementToBePresent(VERIFY_COLUMN_PROPERTIES_WINDOW);
			clickButton(SELECT_ALL_REPORT_COLS);
			clickButton(SAVE_REPORT);
			waitForVisibilityOfElement(ENTER_REPORT_NAME);
			inputText(ENTER_REPORT_NAME,reportName);
			clickButton(SAVE_REPORT_NAME);
			waitForVisibilityOfSuccessMessage();
			waitForInvisibilityOfLoader();

		}catch(Exception e) {
			logger.error("Failed to create Report",e);
			test.log(LogStatus.FAIL,"Report creation failed:"+test.addScreenCapture(addScreenshot()));
			throw e;
		}
		return reportName;
	}

	public String createReportSet(String businessApp) {
		String repsetName = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"repsetname"));
		String foldername = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"foldername"));
		try {

			waitForVisibilityOfElement(CREATE_DROPDOWN);
			clickButton(CREATE_DROPDOWN);
			clickButton(CREATE_REPORT_SET);
			waitForInvisibilityOfLoader();
			waitForElementToBePresent(OK_ADD_REPORTS);
			wait(2);
			//List<WebElement> checkbox_list = getWebElementList(REPORTS_CHECKBOX_LIST);
			selectFirstNItemFromList(REPORTS_CHECKBOX_LIST, 3);
			List<WebElement> reports = getWebElementList(REPORTS_NAME_LIST);
			List<String> reportnames = new ArrayList<String>();
			for (int i = 0; i < 3; i++) {
				reportnames.add(reports.get(i).getText());
			}
			clickButton(OK_ADD_REPORTS);
			waitForInvisibilityOfLoader();
			clickButton(SAVE_REPORT_SET);
			waitForElementToBePresent(REPORTSET_NAME_FIELD);
			inputText(REPORTSET_NAME_FIELD, repsetName);
			clickButton(BUSINESS_APP_LIST);
			selectFromlistByKeyAction(BUSINESS_APP_LIST,businessApp);
			clickButton(CREATE_FOLDER);
			waitForVisibilityOfElement(NEW_FOLDER_NAME);
			inputText(NEW_FOLDER_NAME, foldername);
			clickButton(REPORT_SET_SAVE);
			waitForVisibilityOfSuccessMessage();
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			if(isElementDisplayed(VERIFY_REPSET_TAB,repsetName)){
				List<WebElement> addedeports = getWebElementList(REPORTLIST_IN_REPORTSET);
				if(reports.size()==addedeports.size()){
					test.log(LogStatus.PASS,"Reportset created successfully");
				}
			}
		}catch(Exception e){
			test.log(LogStatus.FAIL,"Failed to create Reportset");
		}
       return repsetName;
	}

	public void AddFilterRunAndSubmitReport(String filter) throws Exception {
		try {
			clickButton(REPORT_FILTER_CRITERIA);
			dragAndDrop(DRAG_COLUMN,filter,DROP_PLACE);
			clickButton(SAVE_REPORT_FILTER);
			waitForInvisibilityOfLoader();
			clickButton(RUN_REPORT);
			clickButton(OPTION_MORE);
			clickButton(SUBMIT_REPORT);
			waitForVisibilityOfElement(SUBMIT_SUCCESS);

		}catch(Exception e) {
			logger.error("Failed to create Report",e);
			test.log(LogStatus.FAIL,"Report creation failed:"+test.addScreenCapture(addScreenshot()));
			handleException(e);
			throw e;
		}

	}
	public boolean verifySqlViewContent(String text1, String text2) throws Exception  {
		boolean present=false;
		try {
			clickButton(VIEW_SQL);
			waitForVisibilityOfElement(SQL_SELECT_CONTENT);
			waitForInvisibilityOfLoader();
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			String content=getTextValue(SQL_SELECT_CONTENT);
			if(content.contains(text1) && content.contains(text2)) {
				present = true;
				test.log(LogStatus.PASS,"SQL content Texts validated successfully");
			}

		}catch(Exception e) {
			logger.error("SQL content verification failed");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			throw e;
		}
		return present;
	}

	public boolean isReportSetCreated(String reportSetName)  {
		boolean created = false;

			if (isElementDisplayed(VERIFY_REPSET_TAB, reportSetName)) {
				created = true;
				test.log(LogStatus.PASS, "Report set" + " " + reportSetName + " " + "created successfully");
			}else {
				test.log(LogStatus.FAIL, "Report set creation failed");
				test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			}
		return created;
	}


	public boolean isReportCreated(String reportName) {
		boolean created = false;
		System.out.println("Report name="+getTextValue(VERIFY_REPORT_CREATED));

			if(getTextValue(VERIFY_REPORT_CREATED).contains(reportName)) {
				created = true;
				test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				test.log(LogStatus.PASS,"Report created successfully");
			}else{
				logger.error("Report not created");
				test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			}
			System.out.println("Report created successfully");
		return created;
	}

}
