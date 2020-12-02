package com.splashbi.sanitydata.domain;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.splashbi.sanitydata.admin.Connectors;
import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static org.testng.Assert.assertTrue;

public class Domain extends TestSetup {

public static Logger logger = Logger.getLogger(Domain.class);
//String domainName="";
@Test(dataProvider = "LoadData")
public void createDomainWithMultiTable(Hashtable<String, String> data){
        logger.info("In createDomainAndAddTable and run value is :"+data.get("Run") );
        String domainname = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"domainname"));
        createChildTest("Create Domain");
        domain=home.navigateToDomainPage();
        domainName=domain.createDomainWithNewFolder(domainname,data.get("bussinessApp"),data.get("dbconnector"));
        Utility.setValueInPropertyFile("Domain",domainName);
        assertTrue(domain.isDomainTabOpen(domainName),"Domain creation failed");

        createChildTest("Add Tables to Domain");
        String[] tables = data.get("tablenames").split(",");
        domain.addTablesToDomain(tables);
        domain.verifyTableAdded(tables);
        logger.info("Testcase createDomainWithMultiTable completed");
}

@Test(dataProvider = "LoadData")
public void createOracleEBSDomainWithMultiTable(Hashtable<String, String> data){
        logger.info("In createOracleEBSDomainWithMultiTable and run value is :"+data.get("Run") );
        String domainname = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"ebs_domainname"));
        createChildTest("Create Domain");
        home.navigateToDomainPage();
        ebsDomainName=domain.createDomainWithNewFolder(domainname,data.get("bussinessApp"),data.get("dbconnector"));
        assertTrue(domain.isDomainTabOpen(ebsDomainName),"Domain creation failed");

        createChildTest("Add Tables to Domain");
        String[] tables = data.get("tablenames").split(",");
        domain.addTablesToDomain(tables);
        domain.verifyTableAdded(tables);
        logger.info("Testcase createOracleEBSDomainWithMultiTable completed");

}

@Test(dataProvider = "LoadData")
public void createJoinForDomainTable(Hashtable<String, String> data) throws InterruptedException {
        logger.info("In createJoinForDomainTable and run value is :"+data.get("Run") );
        createChildTest("Search For Domain");
        domainName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Domain");
        domain = home.navigateToDomainPage();
        assertTrue(domain.isDomainPresent(domainName),"Domain not present");

        createChildTest("Create Join For Table");
        domain.createJoinForTable(domainName,data.get("tablename"));
        assertTrue(domain.isTablePresentInJoin(data.get("tablename")));
        logger.info("Testcase createJoinForDomainTable completed");

}
@Test(dataProvider = "LoadData")
public void shareDomainWithAnUser(Hashtable<String, String> data) throws InterruptedException {
        logger.info("In shareDomainWithAnUser and run value is :"+data.get("Run") );
       /* if(userName.isEmpty()){
                logger.info("username is empty");
                userName = data.get("user_name");
        }
        if(domainName.isEmpty()){
                domainName = data.get("domainName");
        }*/
        domainName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Domain");
        userName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"User_name");
        createChildTest("Search For Domain");
        domain = home.navigateToDomainPage();
        assertTrue(domain.isDomainPresent(domainName),"Domain not present");

        createChildTest("Share Domain with User");
        domain.shareDomainWithUser(domainName,userName);

        createChildTest("Login with other User and verify domain present");
        login = home.logOut();
        login.signInWithOtherUser(userName,user_password, new_password);
        assertTrue(home.verifyHomePage(),"Failed to login to application");
        domain=home.navigateToDomainPage();
        assertTrue(domain.isDomainPresent(domainName),"Domain not present");
        logger.info("Testcase shareDomainWithAnUser completed");

}

}
