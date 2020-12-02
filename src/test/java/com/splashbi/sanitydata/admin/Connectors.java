package com.splashbi.sanitydata.admin;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Connectors extends TestSetup {

    public static Logger logger = Logger.getLogger(Connectors.class);

    @Test(dataProvider = "LoadData")
    public void checkConnectionStatus(Hashtable<String, String> data){
            logger.info("In checkConnectionStatus and run value is :"+data.get("Run") );
            createChildTest("Check Connection status");
            admin=home.navigateToAdminPage();
            connector = admin.navigateToConnectorsPage();
            assertTrue(connector.checkConnection(data.get("connector")),"Connection not valid");
            logger.info("Testcase checkConnectionStatus Passed");
    }

    @Test(dataProvider = "LoadData")
    public void createOracleDBConnector(Hashtable<String, String> data){

            logger.info("In createOracleDBConnector and run value is :"+data.get("Run") );
            createChildTest("Create DB Connection");
            admin=home.navigateToAdminPage();
            connector = admin.navigateToConnectorsPage();
            connectorName = connector.createDBConnector(data);
            Utility.setValueInPropertyFile("Connector",connectorName);
            createChildTest("Verify DB Connection");
            assertTrue(connector.isConnectorCreated(connectorName));
            logger.info("Testcase createDBConnector Passed");
    }
    @Test(dataProvider = "LoadData")
    public void createSplashDBConnector(Hashtable<String, String> data){

            logger.info("In createSplashDBConnector and run value is :"+data.get("Run") );
            createChildTest("Create Splash DB Connection");
            admin=home.navigateToAdminPage();
            connector = admin.navigateToConnectorsPage();
            splashconnectorName = connector.createDBConnector(data);
            Utility.setValueInPropertyFile("Splash_connector",splashconnectorName);
            assertTrue(connector.isConnectorCreated(splashconnectorName));
            logger.info("Testcase createSplashDBConnector completed");
    }
    @Test(dataProvider = "LoadData")
    public void shareConnectorWithUser(Hashtable<String, String> data){
          logger.info("In shareConnectorWithUser and run value is :"+data.get("Run") );
            if(connectorName.isEmpty()){
                connectorName = data.get("connector");
            }
            if(userName.isEmpty()){
                userName=data.get("user_name");
            }
            createChildTest("Share Connection with User");
            admin=home.navigateToAdminPage();
            connector = admin.navigateToConnectorsPage();
            connector.shareConnectionWithUser(connectorName,userName);
            createChildTest("Verify Connection shared with User");
            assertTrue(connector.verifyConnectionShare(connectorName,userName));
            logger.info("Testcase shareConnectorWithUser completed");

    }
}
