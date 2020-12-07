package com.splashbi.pageobject.admin;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;

import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.admin.ConnectorsPageElement.*;
public class ConnectorsPage extends BasePage {
    Logger logger = Logger.getLogger(ConnectorsPage.class);

    public ConnectorsPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public ConnectorsPage(WebDriver driver) {
        super(driver);
    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public boolean checkConnection(String connector_name) {
        boolean connectionStatus = false;
        waitForVisibilityOfElement(SEARCH_CONNECTOR);
        inputText(SEARCH_CONNECTOR,connector_name);
        hitEnterKey(SEARCH_CONNECTOR);
        if(isElementDisplayed(CONNECTOR,connector_name)){
            clickButton(INFO_CONNECTOR);
            waitForVisibilityOfElement(CREATED_CONNECTOR_IMAGE);
        }
        try {
            waitAndClick(TEST_CONNECTOR);
            waitForVisibilityOfElement(CONNECTOR_VALIDATE);
            test.log(LogStatus.PASS, "Checked status");
        }catch(Exception e){
            logger.error("Connection status not validated",e);
            test.log(LogStatus.FAIL,"Connection can not be validated");
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
        }
        return verifyTestConnection();
    }
    public boolean verifyTestConnection(){
        if(getTextValue(CONNECTOR_VALIDATE).contains("Valid Database Connector")){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Connection validated successfully");
            return true;
        }
        else{
            test.log(LogStatus.FAIL,"Connection is not get validated successfully");
            return false;
        }

    }

    public void checkStatusByEdit(String connector_name, String password){
        inputText(SEARCH_CONNECTOR,connector_name);
        hitEnterKey(SEARCH_CONNECTOR);
        if(!isElementDisplayed(CONNECTOR,connector_name)){
            test.log(LogStatus.FAIL,"Connector not found");
        }
        clickButton(EDIT_CONNECTOR);
        waitForVisibilityOfElement(CONNECTOR_TAB,connector_name);
        inputText(CONNECTION_PASSWORD, password);
        testAndSaveConnector();
    }
    public boolean isStatusCheckedForAllConnectors(){
        boolean isChecked = false;
        clickButton(CHECK_STATUS_OF_ALL_CONNECTORS);
        waitForInvisibilityOfElement(STATUS_CHECK_LOADER_IMG);
        if(getListSize(LIST_OF_VALID_CONNECTORS)>0 || getListSize(LIST_OF_INVALID_CONNECTORS)>0) {
            isChecked = true;
            test.log(LogStatus.PASS, "Total valid connectors: " + getListSize(LIST_OF_VALID_CONNECTORS));
            test.log(LogStatus.PASS, "Total invalid connectors: " + getListSize(LIST_OF_INVALID_CONNECTORS));
            test.log(LogStatus.PASS, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
        }
        else{
            test.log(LogStatus.FAIL, "Status check not successfull");
        }
        return isChecked;
    }
    public boolean isConnectorSaved(){
        if(getTextValue(CONNECTOR_SAVE_SUCCESS_POPUP).contains("Database Connector saved successfully.")){
            test.log(LogStatus.PASS,"Connector saved successfully");
            test.log(LogStatus.PASS, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            return true;
        }
        else{
            test.log(LogStatus.FAIL,"Connection is not saved");
            test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            return false;
        }

    }
    // Navigate to Connector Page and verify its open
    public boolean isConnectorOpen(String connector_name) throws Exception {
        try {
            waitAndClick(CONNECTOR, connector_name);
            return isElementDisplayed(CONNECTOR_TAB, connector_name);
        }catch(Exception e){
            waitAndClick(CONNECTOR, connector_name);
            return isElementDisplayed(CONNECTOR_TAB, connector_name);
        }
    }
    public void testAndSaveConnector(){
        clickButton(CREATE_CONNECTOR_TEST);
        waitForInvisibilityOfLoader();
        waitForVisibilityOfSuccessMessage();
        waitForInvisibilityOfSuccessPopup();
        clickButton(CREATE_CONNECTOR_SAVE);
        waitForInvisibilityOfLoader();
        if(isErrorPresent()){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Connector creation failed");
        }
    }

    /******* Create a New DB Connector *************/
    public String createDBConnector(Hashtable<String, String> data) {
        String connector_name = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"connectorname"));
        try {

            clickButton(CREATE_CONNECTORS);
            if (isElementDisplayed(DB_CONNECTORS_HEADER)) {
                clickButton(DB_CONNECTOR_TYPE, data.get("connector_type"));
                waitForVisibilityOfElement(VERIFY_CREATE_CONNECTOR);
                fillDBConnectorDetails(connector_name, data);
            }
            testAndSaveConnector();
            waitForVisibilityOfSuccessMessage();
            waitForVisibilityOfElement(CONNECTOR_HOME);
        }catch(Exception e){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Connector creation failed",printError(e,3));
        }
        return connector_name;
    }
    public boolean isConnectorCreated(String connector_name){
        inputText(SEARCH_CONNECTOR,connector_name);
        if(isElementDisplayed(CONNECTOR,connector_name)){
            clickButton(INFO_CONNECTOR);
            waitForVisibilityOfElement(CREATED_CONNECTOR_IMAGE);
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Created Connector",connector_name +" "+"created successfully");

        }else{
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Connector creation failed",connector_name +" "+"not created");
        }
        return true;
    }
    public void fillDBConnectorDetails(String connector_name,Hashtable<String, String> data) {
        try {
            inputText(CONNECTOR_NAME, connector_name);
            clickButton(SYSTEM_TYPE_LIST);
            selectItemFromAlist(SYSTEM_ITEM_LIST, data.get("system_type"));
            if(!getTextValue(CONNECTION_TYPE_VALUE).equalsIgnoreCase(data.get("connection_type"))){
                selectFromlistByKeyAction(CONNECTION_TYPE_LIST,data.get("connection_type"));
            }
            inputText(HOST_NAME, data.get("hostname"));
            inputText(SID, data.get("sid"));
            inputText(CONNECTION_USER, data.get("connection_user"));
            inputText(CONNECTION_PASSWORD, data.get("connection_password"));
            inputText(PORT_NUMBER, data.get("port_number"));
            inputText(SCHEMA_NAME, data.get("schema_name"));
        }catch(Exception e){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Connector creation failed",printError(e,2));
        }
    }
    public void testConnectorAndSave() throws Exception{
        clickButton(CREATE_CONNECTOR_TEST);
        clickButton(CREATE_CONNECTOR_SAVE);
    }
    public void shareConnectionWithUser(String connector_name, String username) {
        waitForVisibilityOfElement(CONNECTOR_HOME);
        inputText(SEARCH_CONNECTOR,connector_name);
        hitEnterKey(SEARCH_CONNECTOR);
        clickButton(CONNECTION_ACTION_LIST);
        clickButton(SHARE_CONNECTOR_OPTION);
        waitForVisibilityOfElement(VERIFY_SHARE_CONNECTOR_PAGE);
        inputText(AVAILABLE_USER_SEARCH,username);
        hitEnterKey(AVAILABLE_USER_SEARCH);
        wait(1);
        waitForVisibilityOfElement(USER_IN_SHARE_LIST,username);
        waitAndClick(SELECT_AVAILABLE_USER,username);
        clickButton(SHIFT_RIGHT_BUTTON);
        wait(1);
        waitForVisibilityOfElement(USER_IN_SHARE_LIST,username);
        if(getListSize(SHARED_USER_LIST)>0){
            clickButton(SAVE_SHARE);
        }
        waitForVisibilityOfSuccessMessage();
        wait(1);
    }
    public boolean verifyConnectionShare(String connector_name,String username) {
        inputText(SEARCH_CONNECTOR,connector_name);
        hitEnterKey(SEARCH_CONNECTOR);
        waitForElementToBePresent(CONNECTOR,connector_name);

        clickButton(INFO_CONNECTOR);
        waitForVisibilityOfElement(CREATED_CONNECTOR_IMAGE);
        clickButton(CONNECTOR_SHARE_TAB);
        clickButton(EXPAND_ALL);
        if(getListSize(VERIFY_USER_IN_SHARE_TAB,username)>0){
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"User found",connector_name +" "+"shared with"+""+ username);

        }else{
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Connector share failed",connector_name +" "+"not shared");
        }
        return true;
    }


}
