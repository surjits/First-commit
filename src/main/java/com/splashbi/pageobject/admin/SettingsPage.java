package com.splashbi.pageobject.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.SettingsPageElement.*;
public class SettingsPage extends BasePage {
    String usergroup_name="";
    Logger logger = Logger.getLogger(UsersPage.class);

    public SettingsPage(WebDriver driver) {
        super(driver);
    }
    public SettingsPage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }

    public boolean isSettingsPageOpen(){
        if(isElementDisplayed(VERIFY_SETTINGS_HOME)){
            return true;
        }
        return false;
    }
    public void setSiteValue(String option){
        clickButton(SITE_TAB);
        wait(1);
        if(!getTextValue(DEFAULT_SETTING).equalsIgnoreCase(option)){
            clickButton(SITE_DROPDOWN);
            selectItemFromAlist(SETTING_LIST,option);
        }
        clickButton(SETTING_SAVE);
    }
    public boolean isUserGroupCreationSiteSet(String option){
        clickButton(USER_SETTINGS);
        waitForVisibilityOfElement(VERIFY_USERSETTING);
        waitAndClick(ROLES_AND_PRIVILEGE);
        waitAndClick(USERGROUP_CREATION);
        waitForVisibilityOfElement(USERGROUP_CREATION_WINDOW);
        setSiteValue(option);
        waitForInvisibilityOfLoader();
        if(getTextValue(DEFAULT_SETTING).equalsIgnoreCase(option)){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Settings updated successfully");
            return true;
        }
        else {
            test.log(LogStatus.FAIL,"Settings not updated successfully");
            return false;
        }
    }
    public boolean isDashBoardListViewSiteSet(String option){
        clickButton(USER_SETTINGS);
        waitForVisibilityOfElement(VERIFY_USERSETTING);
        waitAndClick(VISUAL);
        waitAndClick(DASHBOARD_LISTVIEW_DISPLAY);
        waitForVisibilityOfElement(DASHBOARD_LISTVIEW_DISPLAY_WINDOW);
        setSiteValue(option);
        waitForInvisibilityOfLoader();
        if(getTextValue(DEFAULT_SETTING).equalsIgnoreCase(option)){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Settings updated successfully");
            return true;
        }
        else {
            test.log(LogStatus.FAIL,"Settings not updated successfully");
            return false;
        }
    }
}
