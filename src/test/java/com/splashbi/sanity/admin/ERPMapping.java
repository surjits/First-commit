package com.splashbi.sanity.admin;

import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class ERPMapping extends TestSetup {
    String empName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Employee");
    @Test(dataProvider = "LoadData")
    public void exportUserMapping(Hashtable<String, String> data) {
        logger.info("In exportUserMapping and run value is :"+data.get("Run") );
        String filetemp = "oracle_ebs_user";
        createChildTest("Navigate to ERP-Mapping Page");
        admin=home.navigateToAdminPage();
        erpmap = admin.navigateToERPMappingPage();
        assertTrue(erpmap.isERPMappingPageOpen(),"Failed to navigate to ERP-MAPPING Page");
        createChildTest("Export User Mapping");
        erpmap.navigateToOracleEBusinessSuite();
        erpmap.exportUserMapping(empName);
        createChildTest("Verify File in Download folder");
        assertTrue(erpmap.isFileDownloaded(filetemp));
        logger.info("Testcase exportUserMapping completed");

    }

}
