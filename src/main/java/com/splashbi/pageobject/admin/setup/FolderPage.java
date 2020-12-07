package com.splashbi.pageobject.admin.setup;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.pageobject.admin.AdminPage;
import com.splashbi.pageobject.admin.UsersPage;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.splashbi.pageelement.admin.ERPMappingPageElement.SEARCH_USER_MAPPING;
import static com.splashbi.pageelement.admin.setup.FolderPageElement.*;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.setup.UserGroupPageElement.*;

public class FolderPage extends AdminPage {
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
        if(isElementDisplayed(FOLDER_HOME)){
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
        selectFromlistByKeyAction(BUSINESS_APP_LIST,businessApp);
        clickButton(SAVE_FOLDER);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfSuccessMessage();
        waitForInvisibilityOfSuccessPopup();
        return folder_name;
    }
    public boolean verifyFolder(String folderName) {
        boolean isPresent = false;
        waitForVisibilityOfElement(FOLDER_HOME);
        inputText(SEARCH_FOLDER, folderName);
        waitForVisibilityOfElement(FOLDER_SEARCHED);
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
    public boolean isEditFolderOpen(String folderName){
        inputText(SEARCH_FOLDER,folderName);
        if (!isElementDisplayed(FOLDER_SEARCHED)){
            clearTextBox(SEARCH_FOLDER);
            hitEnterKey(SEARCH_FOLDER);
            clickButton(FIRST_FOLDER_INFO);
        }else{
            clickButton(FOLDER_INFO);
        }
        waitForVisibilityOfElement(EDIT_FOLDER);
        clickButton(EDIT_FOLDER);
        waitForVisibilityOfElement(EDIT_FOLDER_WINDOW);
        if(isElementDisplayed(EDIT_FOLDER_WINDOW)){
            test.log(LogStatus.PASS, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Edit Folder window is open");
            return true;
        }else{
            test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL, "Edit Folder window not open");
            return false;
        }
    }
    public void openEditFolderWindow(String folderName){
        inputText(SEARCH_FOLDER, folderName);
        waitForVisibilityOfElement(FOLDER_SEARCHED);
        clickButton(FOLDER_INFO);
        clickButton(EDIT_FOLDER);
    }

}
