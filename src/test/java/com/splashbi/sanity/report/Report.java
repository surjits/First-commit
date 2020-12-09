package com.splashbi.sanity.report;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Report extends TestSetup {
    public static Logger logger = Logger.getLogger(com.splashbi.sanity.report.Report.class);
    String reportName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH, "Report");
    @Test(dataProvider = "LoadData")
    /********Pre-Req: Craete Report*************/
    public void RunAndSubmitAnyReport(Hashtable<String, String> data) {
        logger.info("In RunAndSubmitAnyReport and run value is :" + data.get("Run"));
        createChildTest("Run and Submit the Report");
        report = home.navigateToReportPage();
        assertTrue(report.isReportAvailable(reportName));
        report.runAndSubmitReport(reportName);
        createChildTest("Verify Process in Maintenence Page");
        maintenence= home.navigateToAdminPage().navigateToMaintenancePage();
        assertTrue(maintenence.isMaintenancePageOpen());
        assertTrue(maintenence.isProcessFound(reportName));
        maintenence.getProcessStatus();
        logger.info("Testcase RunAndSubmitAnyReport completed");

    }
}
