package com.splashbi.pageobject.admin.setup;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.pageobject.admin.UsersPage;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.splashbi.pageelement.admin.setup.FolderPageElement.*;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.setup.UserGroupPageElement.*;

public class FolderPage extends BasePage {
    String folder_name="";
    Logger logger = Logger.getLogger(UsersPage.class);

    public FolderPage(WebDriver driver) {
        super(driver);
    }
    public FolderPage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public boolean isFolderPageOpen(){
        if(isElementDisplayed(VERIFY_FOLDER_HOME)){
            return true;
        }
        else{
            return false;
        }
    }
    public String createFolder(String businessApp) {
        folder_name = Utility.getRandomNumber("DPFOLDER");
        clickButton(CREATE_FOLDER);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfElement(CREATE_FOLDER_PAGE);
        inputText(FOPLDER_NAME_FIELD,folder_name);
        wait(1);
       // clickButton(BUSINESS_APP_LIST);
        selectFromlistByKeyAction(BUSINESS_APP_LIST,businessApp);
       // selectItemFromAlist(BUSINESS_APP_NAME_LIST,businessApp);
       // selectFirstItemFromList(BUSINESS_APP_NAME_LIST);

       //waitAndClick(BUSINESS_APP_NAME,businessApp);
        clickButton(SAVE_FOLDER);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfSuccessMessage();
        return folder_name;
    }
    public boolean verifyFolder(String folderName) {
        boolean isPresent = false;
        waitForVisibilityOfElement(VERIFY_FOLDER_HOME);
        inputText(SEARCH_FOLDER, folderName);
        //hitEnterKey(SEARCH_USER);
        if (isElementDisplayed(FOLDER_SEARCHED)) {
            isPresent = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Created Folder", folderName + " " + "created successfully");

        } else {
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "Folder creation failed", folderName + " " + "not created");
        }
        return isPresent;
    }

}
