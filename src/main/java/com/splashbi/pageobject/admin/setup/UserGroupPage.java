package com.splashbi.pageobject.admin.setup;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.pageobject.admin.AdminPage;
import com.splashbi.pageobject.admin.UsersPage;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.ConnectorsPageElement.CONNECTOR_VALIDATE;
import static com.splashbi.pageelement.admin.UserPageElement.SEARCH_USER;
import static com.splashbi.pageelement.admin.UserPageElement.USER_HOME_PAGE;
import static com.splashbi.pageelement.admin.setup.UserGroupPageElement.*;

public class UserGroupPage extends AdminPage {
    String usergroup_name="";
    Logger logger = Logger.getLogger(UsersPage.class);

    public UserGroupPage(WebDriver driver) {
        super(driver);
    }
    public UserGroupPage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public String createUserGroup() {
        usergroup_name =Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"usergrpname"));
        clickButton(CREATE_USER_GROUP);
        waitForVisibilityOfElement(CREATE_USERGROUP_FORM);
        inputText(USERGROUP_NAME, usergroup_name);
        clickButton(SAVE_USERGROUP);
        waitForVisibilityOfElement(USERGROUP_CREATED_SUCCESS);
        waitForInvisibilityOfSuccessPopup();
        return usergroup_name;
    }
    public boolean isUserGroupPageOpen(){
        if(isElementDisplayed(CREATE_USER_GROUP)){
            return true;
        }
        else{
            return false;
        }
    }

    public void addAnyUserToGroup(String usergroup) {

            if (!getTextValue(SEARCHED_USERGROUP).contains("usergroup")) {
                inputText(SEARCH_USER_GROUP, usergroup);
                hitEnterKey(SEARCH_USER_GROUP);
            }
            clickButton(USERGROUP_ACTION_LIST);
            clickButton(EDIT_USERGROUP);
            //  clickButton(USERGROUP_SEARCHED);
            waitForElementToBePresent(AVAILABLE_EMPLYEES_LIST);
            selectFirstItemFromList(AVAILABLE_EMPLYEES_LIST);
            clickButton(ADD_USER);
            waitForVisibilityOfElement(USERLIST_IN_SELECTED);
            clickButton(SELECTED_EMPLOYEE_BUTTON);
            clickButton(SAVE_USERGROUP);
            waitForVisibilityOfElement(VERIFY_SUCESS);
            waitForInvisibilityOfSuccessPopup();
        }
    public boolean isUserPresentInGroup(String group) {
        boolean isPresent = false;
        try {
            if(isUserGroupCreated(group)) {
                clickButton(INFO_USERGROUP);
            }
            clickButton(MEMBERS_TAB);
            waitAndClick(USER_EXPAND);
            // clickButton(USER_EXPAND);
            int counter = 1;
            while(!isElementPresent(USERLIST_IN_GROUP)){
                clickButton(DETAILS_TAB);
                wait(1);
                counter++;
                clickButton(MEMBERS_TAB);
                if(counter==5){
                    break;
                }
            }
            if(getListSize(USERLIST_IN_GROUP) > 0){
                isPresent = true;
                test.log(LogStatus.PASS, "User found", "User" + " " + "present in user group");
                test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            }
        }catch(Exception e){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "User not found after waiting 5 seconds", "User" + " " + "not present in user group");
        }
        return isPresent;
    }

    public boolean isUserGroupCreated(String usergroup) throws Exception {
        boolean isCreated = false;
        waitForVisibilityOfElement(SEARCH_USER_GROUP);
        inputText(SEARCH_USER_GROUP, usergroup);
        waitForVisibilityOfElement(SEARCHED_USERGROUP);
        if (isElementDisplayed(SEARCHED_USERGROUP)) {
            isCreated = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Created User", usergroup + " " + "created successfully");

        } else {
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "User creation failed", usergroup + " " + "not created");
        }
        return isCreated;
    }
}

