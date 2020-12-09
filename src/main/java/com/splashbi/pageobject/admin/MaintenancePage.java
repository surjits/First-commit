package com.splashbi.pageobject.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.splashbi.pageelement.admin.MaintenancePageElement.*;

public class MaintenancePage extends BasePage {
    Logger logger = Logger.getLogger(MaintenancePage.class);

    public MaintenancePage(WebDriver driver) {
        super(driver);
    }
    public MaintenancePage(WebDriver driver, ExtentTest test) {

        super(driver, test);

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public boolean isMaintenancePageOpen(){
        waitForVisibilityOfElement(MAINTENANCE_HOME);
        if(isElementDisplayed(MAINTENANCE_HOME)){
            return true;
        }else{
            return false;
        }
    }
    public void searchProcess(String process){
        waitForVisibilityOfElement(INPUT_SEARCH_PROCESS);
        inputText(INPUT_SEARCH_PROCESS,process);
        clickButton(APPLY_BUTTON);
        wait(1);
    }
    public boolean isProcessFound(String processName){
        searchProcess(processName);
        if(isElementDisplayed(FIRST_SEARCH_RECORD)){
            test.log(LogStatus.PASS, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Process "+" "+processName+ " "+"found");
            return true;
        }
        else{
            test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Process "+" "+processName+ " "+"Not found");
            return false;
        }
    }
    public void getProcessStatus(){
        int counter =1;
        String status="";
        if(isElementDisplayed(PROCESS_STATUS)){
            while(!getAttributeValue(PROCESS_STATUS,"title").equalsIgnoreCase("completed")){
                if(counter>3) {
                    test.log(LogStatus.INFO, "Process status not completed after 3 refresh");
                    break;
                }
                clickButton(REFRESH_BUTTON);
                waitForInvisibilityOfLoader();
                counter++;
            }
            status = getAttributeValue(PROCESS_STATUS,"title");
        }
        test.log(LogStatus.INFO, "Process status is"+" "+status);

    }

}
