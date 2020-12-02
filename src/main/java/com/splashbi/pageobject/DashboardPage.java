package com.splashbi.pageobject;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.splashbi.pageelement.DynamicPageElement.*;
import static com.splashbi.pageelement.DashboardPageElement.*;
import static com.splashbi.pageelement.admin.setup.UserGroupPageElement.*;

public class DashboardPage extends BasePage {
    String chartName;
    String dashboardName;
    Logger logger = Logger.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }

    public boolean isDashBoardPageOpen() {
        waitForVisibilityOfElement(VERIFY_DASHBOARD_HOME);
        if (isElementPresent(VERIFY_DASHBOARD_HOME)) {
            return true;
        } else {
            return false;
        }
    }

    public String createChart(String domainName, String table, String col1, String col2) {
        chartName = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"chartname"));
        try {
            clickButton(CREATE_DROPDOWN);
            clickButton(CREATE_CHART);
            waitForInvisibilityOfLoader();
            waitForElementToBePresent(VERIFY_CREATE_VIEW);
            inputText(DOMAIN_SEARCH, domainName);
            clickButton(DOMAIN_IN_DASHBOARD, domainName);
            waitForInvisibilityOfLoader();
            waitForVisibilityOfElement(VERIFY_NEWCHART_TAB);
            clickButton(TABLE_EXPAND_ICON, table);
            dragAndDrop(COLUMN_TO_DRAG, col1, TARGET_DROP_LOC_IN_DIMENSION);
            wait(1);
            dragAndDrop(COLUMN_TO_DRAG, col2, TARGET_DROP_LOC_IN_MEASURES);
            clickButton(SAVE_UNTITLE_CHART);
            waitForVisibilityOfElement(SAVE_TITLED_CHART);
            inputText(ENTER_CHART_NAME, chartName);
            clickButton(SAVE_TITLED_CHART);
            waitForInvisibilityOfLoader();

        } catch (Exception e) {
            e.printStackTrace();
            test.log(LogStatus.FAIL, "Chart creation failed");
        }
        return chartName;
    }

    public String createDashBoard(String chartName, String businessapp) {
        dashboardName = Utility.getRandomNumber("ATDASH");
        try {
            clickButton(CREATE_DROPDOWN);
            clickButton(CREATE_DASHBOARD);
            waitForVisibilityOfElement(VERIFY_DASHBOARD_TAB);
            clickButton(VERIFY_DASHBOARD_TAB);
            inputText(SEARCH_DOMAIN_FOR_DASHBOARD, chartName);
            waitForVisibilityOfElement(CHART_TO_DRAG, chartName);
            dragAndDrop(CHART_TO_DRAG, chartName, CHART_DROP_AREA);
            clickButton(SAVE_UNTITLED_DASHBOARD);
            waitForVisibilityOfElement(ENTER_DASHBOARD_NAME);
            inputText(ENTER_DASHBOARD_NAME, dashboardName);
            inputText(ENTER_DESCRIPTION, "Pre-Sanity dashboard");
            clickButton(SAVE_TITLED_DASHBOARD);
            waitForInvisibilityOfLoader();
            waitForVisibilityOfSuccessMessage();
            if (getTextValue(VERIFY_DASHBOARD_TAB).equalsIgnoreCase(dashboardName)) {
                test.log(LogStatus.PASS, "Dashboard created ");
                test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            test.log(LogStatus.FAIL, "Chart creation failed");
        }
        return dashboardName;
    }
    public boolean isDashboardPresent(String dashboardname){
        boolean isPresent = false;
        if (isElementDisplayed(SEARCH_CHART_DASHBOARD)) {
            inputText(SEARCH_CHART_DASHBOARD, dashboardname);
        }
        waitForVisibilityOfElement(DASHBOARD_OBJECT_SEARCHED, dashboardname);
        if(isElementDisplayed(DASHBOARD_OBJECT_SEARCHED, dashboardname)){
            isPresent = true;
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS,"Dashboard Present");
        }
        else{
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.FAIL,"Dashboard "+""+dashboardname+""+"not found");
        }
        return isPresent;
    }
    public boolean isDsahboardCreated(String dashboardName) {
        boolean created = false;
        try {
            if (getTextValue(VERIFY_DASHBOARD_TAB).equalsIgnoreCase(dashboardName)) {
                created = true;
                test.log(LogStatus.PASS, "Dashboard created successfully ");
                // test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Dashboard creation failed", e);
            test.log(LogStatus.FAIL, "Dashboard creation failed", printError(e, 2));
        }
        return created;
    }

    public void shareDashboardObjectWithUser(String itemname, String username) throws Exception {
            isDashboardPresent(itemname);
            clickButton(SELECT_ALL);
            waitAndClick(SELECT_CHART_DASHBOARD_TO_SHARE, itemname);
            clickButton(SHARE_ALL_ICON);
            waitForVisibilityOfElement(SERACH_USER_TO_SHARE);
            inputText(SERACH_USER_TO_SHARE, username);
            if(isElementDisplayed(VERIFY_USER_AVAILABLE,username)) {
                clickButton(SELECT_CHECKBOX_FOR_USER_TO_SHARE);
            }
            clickButton(ADD_USER_TO_SHARE);
            waitForVisibilityOfElement(VERIFY_USER_ADDED, username);
            if (isElementDisplayed(VERIFY_USER_ADDED, username)) {
                clickButton(ENABLE_SHARED_USER);
            }
            clickButton(SAVE_DASHBOARD_SHARE);
            waitForVisibilityOfSuccessMessage();

        }

    public boolean isDashboardSharedWithUser(String dashboard, String user) throws Exception {
        boolean isShared = false;
        if (isElementDisplayed(DASHBOARD_OBJECT_SEARCHED, dashboard)) {
            clickButton(DASHBORAD_INFO);
        }
        if (isElementDisplayed(DASHBOARD_SHARE_TAB)) {
            clickButton(DASHBOARD_SHARE_TAB);
            clickButton(DASHBOARD_SHARE_EXAPND_ALL);
        }
        int counter = 1;
        while (!isElementPresent(SHARED_USER_IN_USERLIST, user)) {
            clickButton(DASHBOARD_DETAILS_TAB);
            wait(1);
            counter++;
            clickButton(DASHBOARD_SHARE_TAB);
            if (counter == 5) {
                logger.error("Couuld not find the user" + " " + user + " " + "in shared user list after 5 seconds");
                test.log(LogStatus.ERROR, "User" + " " + user + " " + "not found in shared user list");
                break;
            }
            isShared = true;
            test.log(LogStatus.PASS, "User found in shared userlist for dashboard" + test.addScreenCapture(addScreenshot()));
        }
        return isShared;
    }


    public boolean isChartCreated(String chartName, String col1, String col2) {
        boolean created = false;
        try {
            waitForVisibilityOfElement(VERIFY_CHARTNAME_TAB, chartName);
            if (isElementPresent(VERIFY_COL_IN_DIMENSIONS, col1) && isElementPresent(VERIFY_COL_IN_MEASURES, col2)) {
                created = true;
            }
            test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
            test.log(LogStatus.PASS, "Chart Created and columns verified ");
        }catch(Exception e){
            e.printStackTrace();
            test.log(LogStatus.FAIL, "Chart creation failed",printError(e,2));
        }
        return created;
    }

}
