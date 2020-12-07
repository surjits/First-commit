package com.splashbi.presanity.admin;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Users extends TestSetup {
    public static Logger logger = Logger.getLogger(Users.class);

    @Test(dataProvider = "LoadData")
    public void createUser(Hashtable<String, String> data){
        logger.info("In createUser and run value is :"+data.get("Run") );
        createChildTest("Navigate to Users Page");
        admin=home.navigateToAdminPage();
        users=admin.navigateToUsersPage();
        assertTrue(users.isUsersPageOpen(),"Failed to navigate to Users Page");
        createChildTest("Create User");
        userName=users.createUser(data,firstName,lastName,user_password);
        Utility.setValueInPropertyFile("User_name",userName);
        Utility.setValueInPropertyFile("Employee",empName);
        assertTrue(users.isUserCreated(userName),"User not Created");
        logger.info("Testcase createUser Passed");
    }

}
