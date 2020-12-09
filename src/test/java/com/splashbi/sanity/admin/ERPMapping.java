package com.splashbi.sanity.admin;

import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class ERPMapping extends TestSetup {
    public static Logger logger = Logger.getLogger(com.splashbi.sanity.admin.ERPMapping.class);

    String empName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH, "Employee");
    String connector = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH, "Connector");

    @Test(dataProvider = "LoadData")
    public void exportUserMapping(Hashtable<String, String> data) {
        logger.info("In exportUserMapping and run value is :" + data.get("Run"));
        String filetemp = "oracle_ebs_user";
        createChildTest("Navigate to ERP-Mapping Page");
        admin = home.navigateToAdminPage();
        erpmap = admin.navigateToERPMappingPage();
        assertTrue(erpmap.isERPMappingPageOpen(), "Failed to navigate to ERP-MAPPING Page");
        createChildTest("Export User Mapping");
        erpmap.navigateToOracleEBusinessSuite();
        erpmap.exportUserMapping(empName);
        createChildTest("Verify File in Download folder");
        assertTrue(erpmap.isFileDownloaded(filetemp));
        logger.info("Testcase exportUserMapping completed");

    }
    @Test(dataProvider = "LoadData")
    public void importEbsUser(Hashtable<String, String> data) {
        logger.info("In exportUserMapping and run value is :" + data.get("Run"));
        createChildTest("Navigate to ERP-Mapping Page");
        admin = home.navigateToAdminPage();
        erpmap = admin.navigateToERPMappingPage();
        assertTrue(erpmap.isERPMappingPageOpen(), "Failed to navigate to ERP-MAPPING Page");
        createChildTest("Import EBS User");
        erpmap.navigateToOracleEBusinessSuite();
        String ebsuser = erpmap.importEBSUser(connector);
        createChildTest("Verify Import EBS user");
        /*** To be implemented**/
        logger.info("Testcase exportUserMapping completed");

    }
}
