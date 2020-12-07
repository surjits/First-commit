package com.splashbi.sanity.admin;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Setup extends TestSetup {
    String foldername = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"folder");
    public static Logger logger = Logger.getLogger(com.splashbi.sanity.admin.Setup.class);
    @Test(dataProvider = "LoadData")
    public void verifyEditFolderWindo(Hashtable<String, String> data){
        logger.info("In verifyEditFolderWindo and run value is :"+data.get("Run") );
        createChildTest("Navigate to Folder Page");
        folder = home.navigateToAdminPage().navigateToSetupPage().navigateToFolder();
        assertTrue(folder.isFolderPageOpen(),"Failed to navigate to Folder Page");
        createChildTest("Navigate to Edit folder window");
        assertTrue(folder.isEditFolderOpen(foldername));
        logger.info("Testcase verifyEditFolderWindo Passed");
    }
}
