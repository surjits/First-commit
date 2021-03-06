package com.splashbi.pageobject.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.splashbi.pageelement.DomainPageElement.DB_CONNECTOR_LIST;
import static com.splashbi.pageelement.DomainPageElement.LIST_OF_BUSINESSAPPS;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.ConnectorsPageElement.CONNECTION_TYPE_LIST;
import static com.splashbi.pageelement.admin.ERPMappingPageElement.*;

public class ERPMappingPage extends BasePage {
    Logger logger = Logger.getLogger(ERPMappingPage.class);

    public ERPMappingPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public ERPMappingPage(WebDriver driver) {
        super(driver);
    }
    public ERPMappingPage(){

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public boolean isERPMappingPageOpen(){
        boolean isnavigated = false;
        try {
            if (isElementDisplayed(ERP_MAPPING_HOME)) {
                isnavigated = true;
            }
        }catch(Exception e){
            test.log(LogStatus.FAIL, "ERP Mapping Page not displayed" + test.addScreenCapture(addScreenshot()));

        }
        return isnavigated;
    }
    public void createERPMapping(String splashBi_empname, String ebs_connection,String authmethod) {
        try {

            clickButton(CREATE_ERP_MAPPING);
            waitForInvisibilityOfLoader();
            waitForVisibilityOfElement(CREATE_ERP_MAPPING_HOME);
            waitForVisibilityOfElement(SPLASHBI_EMP_NAME_DROPDOWN);
            selectFromlistByKeyAction(SPLASHBI_EMP_NAME_DROPDOWN,splashBi_empname);
            selectFromlistByKeyAction(EBS_CONNECTION_DROPDOWN,ebs_connection);
            selectFromlistByKeyAction(AUTHENTICATION_METHOD_DROPDOWN,authmethod);
            clickButton(SELECT_USER_MAPPING);
            clickButton(SEARCH_EBS_USER);
            wait(2);
            waitForInvisibilityOfLoader();
            waitForVisibilityOfElement(SEARCH_AND_SELECT_EBS_USER_WINDOW);
            List<WebElement> ebsUsers = getWebElementList(EBS_USER_LIST);
            String ebsUser = ebsUsers.get(0).getText();
            selectFirstItemFromList(EBS_USER_LIST);
            clickButton(SAVE_ERP_MAPPING);
            waitForVisibilityOfElement(ORACLE_EBUSINESS_SUIT_HOME);
            wait(1);
        }catch(Exception e){
            test.log(LogStatus.FAIL,"Failed to create ERP mapping", printError(e,3));
            logger.error("Failed to navigate to Oracle E Business suite",e);
        }
    }
    public void navigateToOracleEBusinessSuite(){
        try {
            waitAndClick(ORACLE_EBUSINESS_SUIT);
            waitForInvisibilityOfLoader();
            waitForVisibilityOfElement(ORACLE_EBUSINESS_SUIT_HOME);
            //  wait(1);
        }catch(Exception e){
            test.log(LogStatus.FAIL,"Failed to navigate to Oracle E Business suite");
            logger.error("Failed to navigate to Oracle E Business suite",e);
        }
    }
    public boolean isUserMappingDeleted(String user){
        boolean isDeleted = false;
        try {
            clickButton(SEARCH_USER_MAPPING);
            inputText(SEARCH_USER_MAPPING, user);
            hitEnterKey(SEARCH_USER_MAPPING);
            //waitForVisibilityOfElement(USER_MAPPING_SEARCHED);
            if(isElementDisplayed(USER_SEARCHED_IN_USER_MAPPING,user)){
                clickButton(DELETE_USER_MAPPING);
                clickOkIfWarningPresent();
                waitForVisibilityOfElement(SEARCH_USER_MAPPING);

            }
            isDeleted = true;
            test.log(LogStatus.PASS,"User mapping deleted successfully");

        }catch(Exception e){
            isDeleted = true;
        }
        return isDeleted;
    }
    public boolean verifyImportEBS(String connection) {
        boolean isPresent = false;
        try {
            if (isElementDisplayed(ORACLE_EBUSINESS_SUIT_HOME)) {
                clickButton(ROLES_RESPONSIBILITY_TAB);
            }
            inputText(SEARCH_USER_IN_ROLE_RESPONSIBILITY_TAB, connection);
            hitEnterKey(SEARCH_USER_IN_ROLE_RESPONSIBILITY_TAB);

            if (isElementDisplayed(USER_SEARCHED_IN_USER_MAPPING, connection)) {
                isPresent = true;
                test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
                test.log(LogStatus.PASS, "Imported", connection + " " + "imported successfully");

            } else {
                test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
                test.log(LogStatus.FAIL, "Import failed", connection + " " + "not imported");
            }
        }catch(Exception e){
            throw e;
        }
        return isPresent;
    }
    public void searchUserInErpMapping(String empname){
        waitForVisibilityOfElement(SEARCH_USER_MAPPING);
        inputText(SEARCH_USER_MAPPING, empname);
        //hitEnterKey(SEARCH_USER_MAPPING);
    }
    public boolean verifyUserMapping(String splashBi_empname ) {
        boolean isPresent = false;
        searchUserInErpMapping(splashBi_empname);
        if (isElementDisplayed(USER_SEARCHED_IN_USER_MAPPING,splashBi_empname)) {
            isPresent = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Created Mapping", splashBi_empname + " " + "mapped successfully");

        } else {
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "Mapping creation failed", splashBi_empname + " " + "not mapped");
        }
        return isPresent;
    }
    public void importEBSResponsibility(String ebsConncetion,String responsibility) {

        if (isElementDisplayed(ORACLE_EBUSINESS_SUIT_HOME)) {
            clickButton(ROLES_RESPONSIBILITY_TAB);
            clickButton(IMPORT_EBS_RESPONSIBILITIES);
        }
        selectFromlistByKeyAction(IMPORT_EBS_CONNECTION_LIST,ebsConncetion);
        waitForInvisibilityOfLoader();
        clickButton(ADD_USER_TO_RESONSIBILITY_CHECKBOX);
        inputText(SEARCH_RESPONSIBILITY, responsibility);
        hitEnterKey(SEARCH_RESPONSIBILITY);
        waitAndClick(FIRST_AVAILABLE_ROLE_RESPONSIBILITY_TO_IMPORT);
        clickButton(MOVE_TO_RIGHT_ARROW);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfElement(VERIFY_FIRSTROW_IN_SELECTED_ROLE);
        clickButton(SAVE_IMPORT_RESPONSIBILITY);
        waitForInvisibilityOfLoader();

    }
    public void addResponsibilityToUser(String splashBiEmpName){

        if(isElementDisplayed(ORACLE_EBUSINESS_SUIT_HOME)) {
            clickButton(SEARCH_USER_MAPPING);
        }
        inputText(SEARCH_USER_MAPPING,splashBiEmpName);
        hitEnterKey(SEARCH_USER_MAPPING);
        if(isElementDisplayed(USER_SEARCHED_IN_USER_MAPPING,splashBiEmpName)){
            clickButton(ADD_RESPONSIBILITY_ACTION);
            waitForInvisibilityOfLoader();
            waitForVisibilityOfElement(ROLES_RESPONSIBILITY_WINDOW);
            test.log(LogStatus.INFO, "Snapshot Before adding responsibility: " + test.addScreenCapture(addScreenshot()));
        }
        clickButton(FIRST_AVAILABLE_ROLE_RESPONSIBILITY_TO_ADD);
        clickButton(RIGHT_ARROW_TO_MOVE);
        if(isElementDisplayed(VERIFY_FIRSTROW_IN_SELECTED_ROLE_To_ADD)){
            clickButton(SAVE_USER_RESPONSIBILITY);
        }

    }
    public boolean isResponsibilityAdded(String splashBi_empname ) {
        boolean isAdded = false;
        waitForVisibilityOfElement(ORACLE_EBUSINESS_SUIT_HOME);
        wait(1);
        clickButton(SEARCH_USER_MAPPING);
        inputText(SEARCH_USER_MAPPING, splashBi_empname);
        hitEnterKey(SEARCH_USER_MAPPING);
        if (isElementDisplayed(USER_SEARCHED_IN_USER_MAPPING,splashBi_empname)) {
            clickButton(ADD_RESPONSIBILITY_ACTION);
            if(isElementDisplayed(VERIFY_FIRSTROW_IN_SELECTED_ROLE_To_ADD)){
                isAdded = true;
            }
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Added responsibility ", splashBi_empname + " " + "added responsibility successfully");
        } else {
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "Mapping creation failed", splashBi_empname + " " + "not mapped");
        }
        return isAdded;
    }
    public void exportUserMapping(String empname){
        searchUserInErpMapping(empname);
        if (!isElementDisplayed(USER_SEARCHED_IN_USER_MAPPING,empname)){
            clearTextBox(SEARCH_USER_MAPPING);
            hitEnterKey(SEARCH_USER_MAPPING);
        }
        clickButton(MASS_EDIT_ICON_IN_USER_MAPPING);
        clickButton(CHECKBOX_TO_SELECT_FIRST_EMPLOYEE);
        clickButton(EXPORT_USER_MAPPING);
        waitForVisibilityOfElement(EXPORT_USER_MAPPING_POPUP_HEADER);
        test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
        clickButton(OK_BUTTON_IN_USER_MAPPING_EXPORT_POPUP);
        wait(2);

    }
    public boolean isFileDownloaded(String filename) {
        boolean download = false;
        try {
            String downlodfile = Utility.checkIfFileDownloaded(Constant.DOWNLOAD_PATH, filename, 1);
            if (downlodfile.contains(filename)) {
                download = true;
                test.log(LogStatus.PASS, "Found the file: "+downlodfile+" "+"in Download folder");
            }
        } catch (Exception e) {
            logger.error("File download has some issue", e);
            test.log(LogStatus.FAIL, "Could not find the donloaded file");
        }
        return download;
    }
    public String importEBSUser(String connector){
        String ebsUser;
        waitForVisibilityOfElement(IMPORT_EBS_USER);
        clickButton(IMPORT_EBS_USER);
        selectFromlistByKeyAction(IMPORT_USER_CONNECTION_LIST,connector);
        ebsUser = getAttributeValue(FIRST_AVAILABLE_USER_TO_ADD_FOR_IMPORT,"title");
        clickButton(SELECT_FIRST_AVAILABLE_USER_TO_ADD_FOR_IMPORT);
        clickButton(MOVE_RIGHT_AVAILABLE_USER);
        waitForVisibilityOfElement(FIRST_USER_IN_SELECTED_LIST_TO_IMPORT);
        clickButton(SAVE_IMPORT_USER);
        waitForVisibilityOfSuccessMessage();
        return ebsUser;
    }
    public static void main(String args[]){
        WebDriver driver=null;
        ERPMappingPage erp = new ERPMappingPage();
        erp.test();
    }
}
