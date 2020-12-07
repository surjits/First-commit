package com.splashbi.presanity;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.setup.TestSetup;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;


public class OracleEBS extends TestSetup {
    public static Logger logger = Logger.getLogger(OracleEBS.class);

    @Test(dataProvider = "LoadData")
    public void checkConnectionStatus(Hashtable<String, String> data){
        try{
            logger.info("In checkConnectionStatus and run value is :"+data.get("Run") );
            ExtentTest childTest=extent.startTest("Login To SplashBi");
            setChildTest(childTest);
            login.signIn();
            assertTrue(home.verifyHomePage(),"Failed to login to application");
            //Check Connection
            ExtentTest childTest1 = extent.startTest("Check ORACLE_EBS Connection status");
            setChildTest(childTest1);
            home.navigateToAdminPage();
            admin.navigateToConnectorsPage();
            assertTrue(connector.checkConnection(data.get("connector")),"Connection not valid");

        } catch(Exception e){
            test.log(LogStatus.FAIL, "Test Failed");
            logger.error("Test case checkConnectionStatus Failed",e);

        }
    }

   @Test(dataProvider = "LoadData")
    public void createDBConnector(Hashtable<String, String> data){
        try{
            logger.info("In checkConnectionStatus and run value is :"+data.get("Run") );
            ExtentTest childTest=extent.startTest("Login To SplashBi");
            setChildTest(childTest);
            login.signIn();
            assertTrue(home.verifyHomePage(),"Failed to login to application");
            //Create Connection
            ExtentTest childTest1 = extent.startTest("Create DB Connection");
            setChildTest(childTest1);
            home.navigateToAdminPage();
            admin.navigateToConnectorsPage();
            String connectorname= connector.createDBConnector(data);
            logger.info("Testcase createDBConnector Passed");
            assertTrue(connector.isConnectorCreated(connectorname));

        }catch(Exception e){
            test.log(LogStatus.FAIL, "Test Failed");
            logger.error("Testcase createDBConnector Failed",e);
            Assert.fail();
        }

    }
}
