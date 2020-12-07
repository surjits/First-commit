package com.splashbi.presanity.report;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Reports extends TestSetup {

    public static Logger logger = Logger.getLogger(Reports.class);
    @Test(dataProvider = "LoadData")
    public void createReport(Hashtable<String, String> data){
        logger.info("In createReport and run value is :"+data.get("Run") );
        if(domainName.isEmpty()){
            domainName = data.get("domainName");
        }
        domainName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Domain");
        createChildTest("Create Report");
        report = home.navigateToReportPage();
        reportName=report.createReport(domainName,data.get("tablename"));
        assertTrue(report.isReportCreated(reportName));
        logger.info("Testcase createReport completed");

    }
    @Test(dataProvider = "LoadData")
    public void createReportSet(Hashtable<String, String> data){
        logger.info("In createReportSet and run value is :"+data.get("Run") );
        if(businessAppName.isEmpty()){
            businessAppName = data.get("businessapp");
        }
        businessAppName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"BusinessApp");
        createChildTest("Create Report Set");
        report = home.navigateToReportPage();
        String reportsetname=report.createReportSet(businessAppName);
        assertTrue(report.isReportSetCreated(reportsetname));
        logger.info("Testcase createReportSet completed");

    }
}
