package com.splashbi.sanity.admin;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Users extends TestSetup {
    public static Logger logger = Logger.getLogger(com.splashbi.sanity.admin.Users.class);
    String userName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"User_name");
    @Test(dataProvider = "LoadData")
    public void exportUserTemplateWithData(Hashtable<String, String> data){
        logger.info("In exportUserTemplateWithData and run value is :"+data.get("Run") );
        String filetemp = "User_Data_Template";
        createChildTest("Navigate to Users Page");
        admin=home.navigateToAdminPage();
        users=admin.navigateToUsersPage();
        assertTrue(users.isUsersPageOpen(),"Failed to navigate to Users Page");
        createChildTest("Export User Template");
        users.exportUsersTemplate();
        createChildTest("Verify File in Download folder");
        assertTrue(users.isFileDownloaded(filetemp));
        logger.info("Testcase exportUserTemplateWithData completed");
    }
    @Test(dataProvider = "LoadData")
    public void changeLanguageOfUser(Hashtable<String, String> data){
        logger.info("In changeLanguageOfUser and run value is :"+data.get("Run") );
        createChildTest("Navigate to Users Page");
        admin=home.navigateToAdminPage();
        users=admin.navigateToUsersPage();
        assertTrue(users.isUsersPageOpen(),"Failed to navigate to Users Page");
        createChildTest("Edit Language Of User");
        users.searchUser(userName);
        users.editUserParameter("language",data.get("language"));
        createChildTest("Verify Language in Settings Page");
        admin=home.navigateToAdminPage();
        setting=admin.navigateToSettingsPage();
        assertTrue(setting.isLanguageUpdated(userName,data.get("language")));
        logger.info("Testcase changeLanguageOfUser completed");
    }
}
