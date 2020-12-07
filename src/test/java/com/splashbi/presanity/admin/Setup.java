package com.splashbi.presanity.admin;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Setup extends TestSetup {

    public static Logger logger = Logger.getLogger(Setup.class);
    @Test(dataProvider = "LoadData")
    public void createUserGroupAndAddUser(Hashtable<String, String> data){
            logger.info("In createUser and run value is :"+data.get("Run") );
            createChildTest("Navigate to User Group Page");
            admin = home.navigateToAdminPage();
            setup = admin.navigateToSetupPage();
            assertTrue(setup.isSetupPageOpen(),"Failed to navigate to Setup Page");
            usergroup = setup.navigateToUserGroup();
            assertTrue(usergroup.isUserGroupPageOpen(),"Failed to navigate to Setup Page");
            createChildTest("Create User Group");
            String usergroupname = usergroup.createUserGroup();
            createChildTest("Add User to User Group");
            usergroup.addAnyUserToGroup(usergroupname );
            assertTrue(usergroup.isUserPresentInGroup(usergroupname),"User not present in user group");
            logger.info("Testcase createUserGroupAndAddUser Passed");

    }
    @Test(dataProvider = "LoadData")
    public void createFolder(Hashtable<String, String> data){
            logger.info("In createFolder and run value is :"+data.get("Run") );
            if(businessAppName.isEmpty()){
                businessAppName = data.get("businessapp");
            }
            createChildTest("Navigate to Folder Page");
            admin = home.navigateToAdminPage();
            setup = admin.navigateToSetupPage();
            assertTrue(setup.isSetupPageOpen(),"Failed to navigate to Setup Page");
            folder = setup.navigateToFolder();
            assertTrue(folder.isFolderPageOpen(),"Failed to navigate to Folder Page");
            createChildTest("Create Folder");
            String foldername = folder.createFolder(businessAppName);
            createChildTest("Verfy Created Folder");
            assertTrue(folder.verifyFolder(foldername),"Folder not found");
            logger.info("Testcase createFolder completed");

    }

    @Test(dataProvider = "LoadData")
    public void createBusinessApplication(Hashtable<String, String> data){
           logger.info("In createBusinessApplication and run value is :"+data.get("Run") );
            createChildTest("Navigate to BusinessApp Page");
            admin = home.navigateToAdminPage();
            setup = admin.navigateToSetupPage();
            assertTrue(setup.isSetupPageOpen(),"Failed to navigate to Setup Page");
            businessapp = setup.navigateToBusinessApp();
            assertTrue(businessapp.isBusinessAppPageOpen(),"Failed to navigate to Business App Page");
            createChildTest("Create Business Application");
            businessAppName = businessapp.createBusinessApplication();
            Utility.setValueInPropertyFile("BusinessApp",businessAppName);
            createChildTest("Verify Created BusinessApp");
            assertTrue(businessapp.verifyBusinessApp(businessAppName),"Business Application not found");
            logger.info("Testcase createBusinessApplication Passed");

    }
}
