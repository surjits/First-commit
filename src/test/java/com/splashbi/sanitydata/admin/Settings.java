package com.splashbi.sanitydata.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.pageobject.admin.AdminPage;
import com.splashbi.pageobject.admin.SettingsPage;
import com.splashbi.setup.TestSetup;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Settings extends TestSetup {
        public static Logger logger = Logger.getLogger(Settings.class);

        @Test(dataProvider = "LoadData")
        public void setUserGroupCreationSiteInSettings(Hashtable<String, String> data){
                logger.info("In setUserGroupCreationSiteInSettings and run value is :"+data.get("Run") );
                createChildTest("Navigate to Settings Page");
                admin= home.navigateToAdminPage();
                setting = admin.navigateToSettingsPage();
                assertTrue(setting.isSettingsPageOpen(),"Failed to navigate to Settings Page");
                createChildTest("Set User Group Creation settings");
                assertTrue(setting.isUserGroupCreationSiteSet(data.get("site_option")),"Setting not Set");
                logger.info("Test case setUserGroupCreationSiteInSettings completed" );

        }
        @Test(dataProvider = "LoadData")
        public void setDashboardListviewSiteInSettings(Hashtable<String, String> data){
                logger.info("In setDashboardListviewSiteInSettings and run value is :"+data.get("Run") );
                createChildTest("Navigate to Settings Page");
                admin= home.navigateToAdminPage();
                setting = admin.navigateToSettingsPage();
                assertTrue(setting.isSettingsPageOpen(),"Failed to navigate to Settings Page");
                createChildTest("Set Dashboard Listview settings");
                assertTrue(setting.isDashBoardListViewSiteSet(data.get("site_option")),"Setting not Set");
        }
}
