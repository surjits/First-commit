package com.splashbi.pageobject;

import com.splashbi.utility.Constant;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.splashbi.pageelement.LandingPageElement.HOME;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.DomainPageElement.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.utility.Utility;

public class DomainPage extends BasePage {
	String domainname ="";
	String ebs_domainname ="";
	Logger logger = Logger.getLogger(DomainPage.class);
	public DomainPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	public DomainPage(WebDriver driver) {
		super(driver);
	}

	public void setTest(ExtentTest test) {
		this.setExtentTest(test);

	}
	public void searchTableInViews(String tablename) throws Exception{
		clickButton(VIEWS_CHECKBOX);
		waitForInvisibilityOfLoader();
		inputText(TABLE_SEARCH_BOX,tablename);
		clickButton(TABLE_SEARCH_ICON);
		waitForInvisibilityOfLoader();
	}

	public String createDomainWithNewFolder(String domain,String businessapp, String dbconnector)  {
		try {

			System.out.println("business app= "+businessapp);
			logger.info("Entering createDomainWithNewFolder method");
			waitAndClick(CREATE_DOMAIN);

			inputText(DOMAIN_NAME,domain);
			//clickButton(BUSINESS_APP_LIST);
			selectFromlistByKeyAction(BUSINESS_APP_LIST,businessapp);
		   // selectItemFromAlist(LIST_OF_BUSINESSAPPS,businessapp);
			clickButton(CREATE_FOLDER_CHECKBOX);
			clickButton(CREATE_FOLDER);
			String foldername = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"foldername"));
			logger.info("Folder name is: "+foldername);
			inputText(FOLDER_NAME,foldername);
			//clickButton(DB_CONNECTOR_LIST);

			selectFromlistByKeyAction(DB_CONNECTOR_LIST,dbconnector);
		    selectItemFromAlist(CONNECTOR_LIST,dbconnector);
			clickButton(SAVE_BUTTON);
			waitForInvisibilityOfLoader();


		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Domain creation failed");
			test.log(LogStatus.ERROR,printError(e,3));
			logger.error(e);

		}
		return domain;
	}
	public boolean isDomainTabOpen(String domainName ){
		waitForVisibilityOfElement(CREATED_DOMAIN_TAB,domainName);
		if(isElementDisplayed(CREATED_DOMAIN_TAB,domainName)) {
			return true;
		}
		else{
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.FAIL,"Domain "+""+domainName+""+"not found");
			return false;
		}
	}
	public void searchDomain(String domainName){
		if(isElementPresent(SEARCH_DOMAIN)){
			inputText(SEARCH_DOMAIN,domainName);
			hitEnterKey(SEARCH_DOMAIN);
		}
	}

	public boolean isDomainPresent(String domainName)  {
		boolean created = false;
		searchDomain(domainName);
		if(isElementDisplayed(SEARCHED_DOMAIN,domainName)) {
			created = true;
			System.out.println("Domain created successfully");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS,"Domain Present");
		}
		else{
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.FAIL,"Domain "+""+domainName+""+"not found");
		}

		return created;
	}

	public void addTableToDomain(String tablename) throws Exception {
		System.out.println(" in addTableToDomain");
		try {
			inputText(TABLE_SEARCH_BOX,tablename);
			dragAndDrop(TABLE_TO_DRAG,tablename.toUpperCase(),TABLE_DROP_LOC);
			waitForInvisibilityOfLoader();
			waitForVisibilityOfElement(DOMAIN_TABLE,tablename.toUpperCase());

		}catch(Exception e) {
			logger.error(e.toString());
			test.log(LogStatus.FAIL, "Table add failed");
			throw e;
		}
	}

	public boolean verifyTableAdded(String[] table)  {
		boolean display = false;
		try {
			for(int i =0; i<table.length; i++) {
				if(getTextValue(DOMAIN_TABLE,table[i].toUpperCase()).equals(table[i].replaceAll("_",""))) {
					display = true;
				}
			}
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS,"Tables added successfully");
		}catch(Exception e) {
			logger.error(e.toString());
			test.log(LogStatus.FAIL, "Table add failed");
			throw e;

		}

		return display;
	}
	public void addTablesToDomain(String []tables)  {
		System.out.println(" in addTableToDomain");
		clickButton(VIEWS_CHECKBOX);
		waitForInvisibilityOfLoader();
		for(int i = 0; i< tables.length;i++) {
			System.out.println(tables[i]);
			try {

				inputText(TABLE_SEARCH_BOX, tables[i]);
				wait(1);
				clickButton(TABLE_SEARCH_ICON);
				waitForInvisibilityOfLoader();
				waitForVisibilityOfElement(TABLE_TO_DRAG, tables[i].toUpperCase());
				//wait(1);
				dragAndDrop(TABLE_TO_DRAG, tables[i].toUpperCase(), TABLE_DROP_LOC);
				waitForInvisibilityOfLoader();
				waitForVisibilityOfElement(DOMAIN_TABLE, tables[i].toUpperCase());

			} catch (Exception e) {
				logger.error(e.toString());
				test.log(LogStatus.FAIL, "Table add failed");

			}
		}
	}
	public void createFiltersFortable(String domain,String table,String filter,String text) throws Exception {
		try {
			clickButton(DOMAIN_TABLE,table.toUpperCase());
			waitAndClick(FILTER_TAB,domain);
			clickButton(ADD_FILTER,domain);
			dragAndDrop(FILTER_NAME_TO_DRAG,filter,FILTER_DROP_LOC);
			clickButton(FILTER_NAME,filter);
			clickButton(FREE_TEXT_FOR_FILTER,"1");
			inputText(INPUT_FREE_TEXT_AREA,"1",text);
			clickButton(SAVE_FILTER,"");
			test.log(LogStatus.INFO, test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS,"Filters added successfully");
		}catch(Exception e) {
			logger.error(e.toString());
			test.log(LogStatus.FAIL, "Filter Creation Failed");
			throw e;

		}
	}
	public void createMultipleFiltersFortable(String domain,String table,String [][] filterdata)  throws Exception {
		try {
			clickButton(DOMAIN_TABLE,table.toUpperCase());
			for(int i=0; i<filterdata.length;i++) {

				waitAndClick(FILTER_TAB,domain);
				clickButton(ADD_FILTER,domain);
				dragAndDrop(FILTER_NAME_TO_DRAG,filterdata[i][0],FILTER_DROP_LOC);
				clickButton(FILTER_NAME,filterdata[i][0]);
				clickButton(FREE_TEXT_FOR_FILTER,String.valueOf(i+1));
				inputText(INPUT_FREE_TEXT_AREA,String.valueOf(i+1),filterdata[i][1]);
				clickButton(SAVE_FILTER);
			}
			test.log(LogStatus.INFO, test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS,"Filters added successfully");
		}catch(Exception e) {
			logger.error(e.toString());
			test.log(LogStatus.FAIL, "Filter Creation Failed"+test.addScreenCapture(addScreenshot()));
			throw e;

		}
	}
	public String createDomainLOV(String lovType,String query) throws Exception {
		String lovName = Utility.getRandomNumber("ATDOMLOV");
		try {
			clickButton(DOMAIN_LOV_BUTTON);
			clickButton(CREATE_LOV);
			inputText(LOV_NAME_FIELD,lovName);
			clickButton(LOV_TYPE_DROPDOWN);
			clickButton(LOV_TYPE_VALUE,lovType);
			inputText(LOV_QUERY_BOX,query);
			clickButton(LOV_VALIDATE_SQL);
			clickButton(SAVE_AND_NEXT);
			waitAndClick(SAVE_LOV_BUTTON);

		}catch(Exception e) {
			logger.error(e.toString());
			test.log(LogStatus.FAIL, "LOV Creation Failed");
			throw e;
		}
		return lovName;
	}
	public boolean verifyLOVcreated(String lovName) throws Exception {
		boolean val =false;
		try {
			waitForVisibilityOfElement(VERIFY_LOV,lovName);
			if(getTextValue(VERIFY_LOV,lovName).contentEquals(lovName)) {
				val = true;
			}
			test.log(LogStatus.INFO, test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS,"Domain LOV created successfully");

		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			test.log(LogStatus.FAIL, "LOV Creation Failed"+test.addScreenCapture(addScreenshot()));
			throw e;
		}
		return val;
	}

	public void createJoinForTable(String domainName,String table) {
		try {
			clickButton(EDIT_DOMAIN,domainName);
			waitForInvisibilityOfLoader();
			waitForVisibilityOfElement(CREATED_DOMAIN_TAB, domainName);
			waitForVisibilityOfElement(EDIT_DOMAIN_TABLE, table);
			if(isElementDisplayed(EDIT_DOMAIN_TABLE, table)){
				clickButton(EDIT_DOMAIN_TABLE, table);
			}
			clickButton(JOIN_TAB, domainName);
			clickButton(SGGESTED_JOINS, domainName);
			waitForInvisibilityOfLoader();
			/*if(isElementPresent(NO_DATA_IN_SUGGESTED_JOIN)) {
				test.log(LogStatus.FAIL, "No join found ro load" + test.addScreenCapture(addScreenshot()));
			}*/
			//test.log(LogStatus.INFO, test.addScreenCapture(addScreenshot()));
			waitForElementToBePresent(VERIFY_TABLE_IN_SUGGESTED_JOIN_WINDOW,table);
			clickButton(LOAD_JOINS);
			wait(1);
			clickButton(REVERSE_JOIN);
			clickButton(SAVE_JOIN);
			clickButton(SAVE_TABLE);
			//wait(1);
			test.log(LogStatus.INFO, test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS,"Domain Table joined successfully");
		}catch(Exception e){
			test.log(LogStatus.FAIL, "Join Creation Failed"+test.addScreenCapture(addScreenshot()));
			e.printStackTrace();
		}
	}
	public boolean isTablePresentInJoin(String tablename){
		if(isElementDisplayed(MASTER_TABLE_IN_JOIN,tablename)){
			return true;
		}
		else{
			return false;
		}
	}

	public void shareDomainWithUser(String domainName, String username) {
		try {
			if (isElementPresent(MASS_EDIT_ICON)) {
				clickButton(MASS_EDIT_ICON);
				clickButton(CHECKBOX_TO_SELECT_DOMAIN,domainName);
				waitAndClick(SHARE_ICON);
				waitForElementToBePresent(VERIFY_SHARE_DOMAIN_WINDOW);
				inputText(SEARCH_AVAILABLE_USERS_TO_SHARE,username);
				clickButton(CHECKBOX_TO_SELECT_AVAILABLE_USER,username);
				clickButton(MOVE_RIGHT_ICON);
				waitForVisibilityOfElement(CHECKBOX_TO_ENABLE_SHARED_USER,username);
				clickButton(CHECKBOX_TO_ENABLE_SHARED_USER,username);
				clickButton(SAVE_SHARE);
				waitForVisibilityOfSuccessMessage();
				test.log(LogStatus.INFO, test.addScreenCapture(addScreenshot()));
				test.log(LogStatus.PASS,"Domain shared successfully with user"+" "+username);

			}
		}catch(Exception e){
			test.log(LogStatus.FAIL, "Share domain Failed"+test.addScreenCapture(addScreenshot()));
			e.printStackTrace();
			logger.error("Failed to share domain",e);

		}
	}


}
