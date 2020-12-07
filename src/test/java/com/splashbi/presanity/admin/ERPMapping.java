package com.splashbi.presanity.admin;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class ERPMapping extends TestSetup {
    String userName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"User_name");
    String empName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Employee");
    String connectorName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Connector");

    public static Logger logger = Logger.getLogger(ERPMapping.class);

    @Test(dataProvider = "LoadData")
    public void createUserMapping(Hashtable<String, String> data){

        logger.info("In createUserMapping and run value is :"+data.get("Run") );

      /*  if(userName.isEmpty()){
            empName = data.get("splashBi_empname");
        }
        if(connectorName.isEmpty()){
            connectorName = data.get("connector");
        }*/
        createChildTest("Navigate to ERP-Mapping Page");
        admin=home.navigateToAdminPage();
        erpmap = admin.navigateToERPMappingPage();
        assertTrue(erpmap.isERPMappingPageOpen(),"Failed to navigate to ERP-MAPPING Page");
        createChildTest("Create ERP Mapping");
        erpmap.navigateToOracleEBusinessSuite();
        erpmap.createERPMapping(empName,connectorName,data.get("authentication_method"));
        assertTrue(erpmap.verifyUserMapping(empName));
        logger.info("Testcase createUserMapping completed");

    }
    @Test(dataProvider = "LoadData")
    public void importOracleEBSResponsibilities(Hashtable<String, String> data){
        logger.info("In importOracleEBSResponsibilities and run value is :"+data.get("Run") );
        if(connectorName.isEmpty()) {
            connectorName = data.get("connector");
        }
        createChildTest("Navigate to ERP-Mapping Page");
        admin=home.navigateToAdminPage();
        erpmap = admin.navigateToERPMappingPage();
        assertTrue(erpmap.isERPMappingPageOpen(),"Failed to navigate to ERP-MAPPING Page");
        createChildTest("Import EBS Roles-Responsibility");
        erpmap.navigateToOracleEBusinessSuite();
        erpmap.importEBSResponsibility(connectorName,data.get("responsibility"));
        assertTrue(erpmap.verifyImportEBS(data.get("responsibility")));
        logger.info("Testcase importOracleEBSResponsibilities completed");
    }

    @Test(dataProvider = "LoadData")
    public void addSResponsibilitiesToUser(Hashtable<String, String> data){
        logger.info("In addSResponsibilitiesToUser and run value is :"+data.get("Run") );
        if(userName.isEmpty()){
            empName = data.get("splashBi_empname");
        }
        createChildTest("Navigate to ERP-Mapping Page");
        admin=home.navigateToAdminPage();
        erpmap = admin.navigateToERPMappingPage();
        assertTrue(erpmap.isERPMappingPageOpen(),"Failed to navigate to ERP-MAPPING Page");
        createChildTest("Add Roles-Responsibility to User");
        erpmap.navigateToOracleEBusinessSuite();
        erpmap.addResponsibilityToUser(empName);
        assertTrue(erpmap.isResponsibilityAdded(empName));
        logger.info("Testcase addSResponsibilitiesToUser completed");

    }

}
