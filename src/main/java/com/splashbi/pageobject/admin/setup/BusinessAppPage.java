package com.splashbi.pageobject.admin.setup;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.pageobject.admin.UsersPage;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.splashbi.pageelement.admin.setup.BusinessAppPageElement.*;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.setup.FolderPageElement.*;
import static com.splashbi.pageelement.admin.setup.FolderPageElement.SAVE_FOLDER;

public class BusinessAppPage extends BasePage {
    String businessapp_name="";
    Logger logger = Logger.getLogger(UsersPage.class);

    public BusinessAppPage(WebDriver driver) {
        super(driver);
    }
    public BusinessAppPage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public boolean isBusinessAppPageOpen(){
        if(isElementDisplayed(BUSINESS_APP_HOME)){
            return true;
        }
        else{
            return false;
        }
    }
    public String createBusinessApplication() {
        businessapp_name = Utility.getRandomNumber("DPBUSINESSAPP");
        businessapp_name=Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"businessapp"));
        clickButton(CREATE_BUSINESS_APP);
        waitForVisibilityOfElement(CREATE_BUSINESS_APP_HOME);
        inputText(BUSINESS_APP_NAME_FIELD,businessapp_name);
        clickButton(SAVE_BUSINESS_APP);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfSuccessMessage();
        return businessapp_name;
    }

    public boolean verifyBusinessApp(String businessAppName) {
        boolean isPresent = false;
        waitForVisibilityOfElement(VERIFY_FOLDER_HOME);
        inputText(SEARCH_BUSINESSAPP, businessAppName);
        //hitEnterKey(SEARCH_USER);
        if (isElementDisplayed(BUSINESSAPP_SEARCHED)) {
            isPresent = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Created Business App", businessAppName + " " + "created successfully");

        } else {
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "Business App creation failed", businessAppName + " " + "not created");
        }
        return isPresent;
    }
}
