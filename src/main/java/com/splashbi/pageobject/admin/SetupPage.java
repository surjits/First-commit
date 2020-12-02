package com.splashbi.pageobject.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.splashbi.pageelement.admin.AdminPageElement.SETUP;
import static com.splashbi.pageelement.admin.SetupPageElement.*;

public class SetupPage extends BasePage {

    Logger logger = Logger.getLogger(UsersPage.class);

    public SetupPage(WebDriver driver) {
        super(driver);
    }
    public SetupPage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }

    public boolean isSetupPageOpen(){
        boolean userPage = false;
        if(isElementDisplayed(SETUP_HOME)){
            userPage = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Navigated to Setup Page","In Users Page");

        }else{
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Failed top navigate to Setup page","Not in userpage");
        }
        return userPage;

    }
    public void navigateToUserGroup(){
        try {
            logger.info("Entered navigateToUserGroup method");

            waitAndClick(USER_GROUPS);
            test.log(LogStatus.PASS, "Navigated to UserGroup Page: ");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Failed to Navigate to UserGroup Page: ");
        }
    }
    public void navigateToFolder(){
        try {
            logger.info("Entered navigateToUserGroup method");

            waitAndClick(FOLDER);
            test.log(LogStatus.PASS, "Navigated to Folder Page: ");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Failed to Navigate to Folder Page: ");
        }
    }
    public void navigateToBusinessApp(){
        try {
            logger.info("Entered navigateToBusinessApp method");

            waitAndClick(BUSINESS_APP);
            test.log(LogStatus.PASS, "Navigated to BusinessApp: ");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Failed to Navigate to BusinessApp Page: ");
        }
    }
}
