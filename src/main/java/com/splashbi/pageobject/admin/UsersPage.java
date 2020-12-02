package com.splashbi.pageobject.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.pageobject.LoginPage;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.ConnectorsPageElement.CREATED_CONNECTOR_IMAGE;
import static com.splashbi.pageelement.admin.ConnectorsPageElement.INFO_CONNECTOR;
import static com.splashbi.pageelement.admin.UserPageElement.*;

import java.util.Hashtable;

public class UsersPage extends BasePage {
    String username="";
    Logger logger = Logger.getLogger(UsersPage.class);

    public UsersPage(WebDriver driver) {
        super(driver);
    }
    public UsersPage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public boolean isUsersPageOpen(){
        boolean userPage = false;
        waitForElementToBePresent(USER_HOME_PAGE);
        if(isElementDisplayed(USER_HOME_PAGE)){
            userPage = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Navigated to users Page","In Users Page");

        }else{
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Failed top navigate to user page","Not in userpage");
        }
        return userPage;

    }
    public boolean isUserCreated(String user) {
        boolean userCreated = false;
        waitForVisibilityOfElement(USER_HOME_PAGE);
        clickButton(SEARCH_USER);
        inputText(SEARCH_USER,user);
        hitEnterKey(SEARCH_USER);
        if(isElementDisplayed(USER_SEARCHED,user)){
            userCreated = true;
            clickButton(INFO_USER,user);
            waitForVisibilityOfElement(USER_DETAILS_TAB);
            waitForVisibilityOfElement(USERNAME_IN_DETAILS_TAB,user);
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Created User",user +" "+"created successfully");

        }else{
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"User creation failed",user +" "+"not created");
        }
        return userCreated;
    }

    public String createUser(Hashtable<String, String> input,String first, String last, String password)  {
        String user ="";
        clickButton(CREATE_USER);
        waitForVisibilityOfElement(CREATE_USER_PAGE);
        user=fillUserBasicDetails(input,first, last,password);
        clickButton(SAVE_NEXT);
       /* if(isElementDisplayed(DISMISS_SUCCESS)){
            clickButton(DISMISS_SUCCESS);
        }*/
        waitForInvisibilityOfLoader();
        waitForVisibilityOfElement(SUCCESS_MESSAGE);
        wait(2);
        fillUserSettings(input);
        clickButton(SAVE_NEXT_USER_EDIT);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfElement(SUCCESS_MESSAGE);
        waitAndClick(SAVE_USER_GROUP);
        waitForInvisibilityOfLoader();
        if(isErrorPresent()){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"User creation failed",user +" "+"not created");
        }
        waitForInvisibilityOfSuccessPopup();

        return user;
    }
    public String fillUserBasicDetails(Hashtable<String, String> input,String firstname, String lastname, String password) {
        username=Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"username"));
        inputText(USER_NAME,username);
        clickButton(USER_AUTHENTICATION_LIST);
        waitAndClick(USER_AUTHENTICATION_METHOD,input.get("authentication_method"));
        clickButton(LETME_CREATE_PASSWORD);
        waitForElementToBePresent(ENTER_PASSWORD);
        clickButton(SEND_EMAIL);
        inputText(ENTER_PASSWORD,password);
        inputText(FIRST_NAME,firstname);
        inputText(LAST_NAME,lastname);
        inputText(EMAIL_ADDRESS,input.get("email"));
        return username;

    }
    public void fillUserSettings(Hashtable<String, String> input) {
        if(input.get("create_privilege").equalsIgnoreCase("yes"))
        {
            selectCreatePrivilege(input);
        }
        if(input.get("copy_privilege").equalsIgnoreCase("yes"))
        {
            selectCopyPrivilege(input);
        }
        if(input.get("share_privilege").equalsIgnoreCase("yes"))
        {
            selectSharePrivilege(input);
        }
        if(input.get("distribution_privilege").equalsIgnoreCase("yes"))
        {
            selectDistributionPrivilege(input);
        }
    }
    public void selectCreatePrivilege(Hashtable<String, String> input) {
        if(input.get("create_Domain").equalsIgnoreCase("yes")){
            clickButton(CREATE_DOMAIN_PRIVILEGE);
        }
        if(input.get("create_Report").equalsIgnoreCase("yes")){
            clickButton(CREATE_REPORT_PRIVILEGE);
        }
        if(input.get("create_Dashboard").equalsIgnoreCase("yes")){
            clickButton(CREATE_DASHBOARD_PRIVILEGE);
        }
        if(input.get("create_Chart").equalsIgnoreCase("yes")){
            clickButton(CREATE_CHART_PRIVILEGE);
        }
        if(input.get("copy_Report_set").equalsIgnoreCase("yes")){
            clickButton(CREATE_REPORTSET_PRIVILEGE);
        }

    }
    public void selectCopyPrivilege(Hashtable<String, String> input) {
        if(input.get("copy_Domain").equalsIgnoreCase("yes")){
            clickButton(COPY_DOMAIN_PRIVILEGE);
        }
        if(input.get("copy_Report").equalsIgnoreCase("yes")){
            clickButton(COPY_REPORT_PRIVILEGE);
        }
        if(input.get("copy_Dashboard").equalsIgnoreCase("yes")){
            clickButton(COPY_DASHBOARD_PRIVILEGE);
        }
        if(input.get("copy_Chart").equalsIgnoreCase("yes")){
            clickButton(COPY_CHART_PRIVILEGE);
        }
        if(input.get("copy_Report_set").equalsIgnoreCase("yes")){
            clickButton(COPY_REPORTSET_PRIVILEGE);
        }

    }
    public void selectSharePrivilege(Hashtable<String, String> input) {
        if(input.get("share_Domain").equalsIgnoreCase("yes")){
            clickButton(SHARE_DOMAIN_PRIVILEGE);
        }
        if(input.get("share_Report").equalsIgnoreCase("yes")){
            clickButton(SHARE_REPORT_PRIVILEGE);
        }
        if(input.get("share_Dashboard").equalsIgnoreCase("yes")){
            clickButton(SHARE_DASHBOARD_PRIVILEGE);
        }
        if(input.get("share_Chart").equalsIgnoreCase("yes")){
            clickButton(SHARE_CHART_PRIVILEGE);
        }
        if(input.get("share_Report_set").equalsIgnoreCase("yes")){
            clickButton(SHARE_REPORTSET_PRIVILEGE);
        }
        if(input.get("share_Report_set_process").equalsIgnoreCase("yes")){
            clickButton(SHARE_REPORTSET_PROCESS_PRIVILEGE);
        }

    }
    public void selectDistributionPrivilege(Hashtable<String, String> input) {
        if(input.get("distribution_email").equalsIgnoreCase("yes")){
            clickButton(DISTRIBUTION_EMAIL_PRIVILEGE);
        }
        if(input.get("distribution_ftp").equalsIgnoreCase("yes")){
            clickButton(DISTRIBUTION_FTP_PRIVILEGE);
        }
        if(input.get("distribution_filesystem").equalsIgnoreCase("yes")){
            clickButton(DISTRIBUTION_FILESYSTEM_PRIVILEGE);
        }
        if(input.get("distribution_bursting").equalsIgnoreCase("yes")){
            clickButton(DISTRIBUTION_BURSTING_PRIVILEGE);
        }
        if(input.get("distribution_publishing").equalsIgnoreCase("yes")){
            clickButton(DISTRIBUTION_REPORTSET_PRIVILEGE);
        }

    }
}
