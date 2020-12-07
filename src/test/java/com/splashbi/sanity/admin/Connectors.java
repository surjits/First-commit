package com.splashbi.sanity.admin;

import com.splashbi.setup.TestSetup;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Connectors extends TestSetup {
    public static Logger logger = Logger.getLogger(com.splashbi.sanity.admin.Connectors.class);
    @Test(dataProvider = "LoadData")
    public void checkStatusOfAllConnectors(Hashtable<String, String> data) {
        logger.info("In checkStatusOfAllConnectors and run value is :"+data.get("Run") );
        createChildTest("Check Status of Connectors");
        admin=home.navigateToAdminPage();
        connector = admin.navigateToConnectorsPage();
        assertTrue(connector.isStatusCheckedForAllConnectors(),"Failed to Check Status of connectors");
        logger.info("Testcase checkStatusOfAllConnectors completed");
    }
    @Test(dataProvider = "LoadData")
    public void editOracleEbsConnection(Hashtable<String, String> data) {
        logger.info("In editOracleEbsConnection and run value is :"+data.get("Run") );
        createChildTest("Edit Connector and check status");
        admin=home.navigateToAdminPage();
        connector = admin.navigateToConnectorsPage();
        connector.checkStatusByEdit(data.get("Connector"),data.get("password") );
        assertTrue(connector.isConnectorSaved(),"Failed edit the connector successfuilly");
        logger.info("Testcase editOracleEbsConnection completed");
    }

}
