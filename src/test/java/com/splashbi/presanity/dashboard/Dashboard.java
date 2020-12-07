package com.splashbi.presanity.dashboard;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Dashboard extends TestSetup {
    String chartName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Chart");
    String businessAppName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"BusinessApp");
    String userName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"User_name");

    public static Logger logger = Logger.getLogger(Dashboard.class);

    @Test(dataProvider = "LoadData")
    public void createNonEbsChart(Hashtable<String, String> data){
        logger.info("In createNonEbsChart and run value is :"+data.get("Run") );
       /* if(domainName.isEmpty()){
            domainName = data.get("domainName");
        }*/
            domainName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Domain");
            createChildTest("Create Chart");
            dashboard = home.navigateToDashboardPage();
            assertTrue(dashboard.isDashBoardPageOpen(),"Failed to navigate to Dashboard page");
            chartName=dashboard.createChart(domainName,data.get("tablename"),data.get("col_dimension"),data.get("col_measures"));
            dashboard.isChartCreated(chartName,data.get("col_dimension"),data.get("col_measures"));
            Utility.setValueInPropertyFile("Chart",chartName);
           logger.info("Testcase createNonEbsChart completed");
    }
    @Test(dataProvider = "LoadData")
    public void createDashboard(Hashtable<String, String> data){
        logger.info("In createDashboard and run value is :"+data.get("Run") );
        if(chartName.isEmpty()){
            chartName = data.get("chartName");
        }

            createChildTest("Create Dashboard");
            dashboard = home.navigateToDashboardPage();
            assertTrue(dashboard.isDashBoardPageOpen(),"Failed to navigate to Dashboard page");
            dashboardName=dashboard.createDashBoard(chartName,data.get("businessapp"));
            assertTrue(dashboard.isDsahboardCreated(dashboardName));
           logger.info("Testcase createDashboard completed");

    }
    @Test(dataProvider = "LoadData")
    public void shareDashboardWithUser(Hashtable<String, String> data) throws Exception {
        logger.info("In shareDashboardWithUser and run value is :"+data.get("Run") );
        if(chartName.isEmpty()){
            chartName = data.get("chartName");
        }
        if(userName.isEmpty()){
            userName = data.get("user_name");
        }
        createChildTest("Search For Dashboard Object");
        dashboard = home.navigateToDashboardPage();
        assertTrue(dashboard.isDashboardPresent(chartName),"Dashboard not present");
        createChildTest("Share Dashboard with User");
        dashboard.shareDashboardObjectWithUser(chartName,userName);
        assertTrue(dashboard.isDashboardSharedWithUser(chartName,userName));

        createChildTest("Login with other User and verify dashboard present");
        login = home.logOut();
        login.signInWithOtherUser(userName,user_password, new_password);
        assertTrue(home.verifyHomePage(),"Failed to login to application");
        dashboard = home.navigateToDashboardPage();
        assertTrue(dashboard.isDashboardPresent(chartName),"Dashboard not present");
        logger.info("Testcase shareDashboardWithUser completed");
    }
}
