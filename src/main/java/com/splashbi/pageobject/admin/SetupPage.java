package com.splashbi.pageobject.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageelement.admin.AdminPageElement;
import com.splashbi.pageelement.admin.SetupPageElement;
import com.splashbi.pageobject.BasePage;
import com.splashbi.pageobject.admin.setup.BusinessAppPage;
import com.splashbi.pageobject.admin.setup.FolderPage;
import com.splashbi.pageobject.admin.setup.UserGroupPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.splashbi.pageelement.admin.AdminPageElement.*;
import static com.splashbi.pageelement.admin.AdminPageElement.ADMIN_HOME;
import static com.splashbi.pageelement.admin.SetupPageElement.*;

public class SetupPage extends AdminPage {

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
    public void navigateToPageInSetup(String pagename) {
        SetupPageElement page = null;
        switch (pagename) {
            case "folder":
                page = FOLDER;
                break;
            case "businessapp":
                page = BUSINESS_APP;
                break;
            case "usergroup":
                page = USER_GROUPS;
                break;
        }
        try {
            clickButton(page);
            waitForInvisibilityOfLoader();
            wait(1);
            while (isElementDisplayed(ADMIN_HOME)) {
                clickButton(page);
                waitForInvisibilityOfLoader();
            }
            test.log(LogStatus.PASS, "Navigated to" + " " + pagename + " " + "page");

        } catch (Exception e) {
            test.log(LogStatus.ERROR, printError(e, 2));
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "Failed to navigate to " + " " + pagename);
        }
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
    public UserGroupPage navigateToUserGroup()  {
        navigateToPageInSetup("usergroup");
        return new UserGroupPage(driver);
    }

   public FolderPage navigateToFolder()  {
       navigateToPageInSetup("folder");
       return new FolderPage(driver);
   }

    public BusinessAppPage navigateToBusinessApp()  {
        navigateToPageInSetup("businessapp");
        return new BusinessAppPage(driver);
    }
}
