package com.splashbi.presanity.domain;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Hashtable;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Domain extends TestSetup {

        public static Logger logger = Logger.getLogger(Domain.class);
        String domainName="";
        public String simpleDomain ="";
        public String domainWithOneTable ="";
        public String domainWithMultiTable ="";
        @Test(dataProvider = "LoadData")
        public void createDomainWithoutTable(Hashtable<String, String> data){
                logger.info("In createDomainAndAddTable and run value is :"+data.get("Run") );
                String domainname = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"domainname"));
                createChildTest("Create Domain");
                domain=home.navigateToDomainPage();
                simpleDomain=domain.createDomainWithNewFolder(domainname,data.get("bussinessApp"),data.get("dbconnector"));
                assertTrue(domain.isDomainTabOpen(simpleDomain),"Domain creation failed");
                logger.info("Testcase createDomainWithoutTable completed");
                //Utility.setValueInPropertyFile("Domain",domainName);
        }
        @Test(dataProvider = "LoadData")
        public void createDomainWithOneTableWithDrillQuerySet(Hashtable<String, String> data){
                logger.info("In createDomainAndAddTable and run value is :"+data.get("Run") );
                String domainname = Utility.getRandomNumber(Utility.getValueFromPropertyFile(Constant.CONFIG_PATH,"domainname"));
                createChildTest("Create Domain");
                domain=home.navigateToDomainPage();
                domainWithOneTable=domain.createDomainWithNewFolder(domainname,data.get("bussinessApp"),data.get("dbconnector"));
                assertTrue(domain.isDomainTabOpen(simpleDomain),"Domain creation failed");
                createChildTest("Add table and Set drill query");
                domain.addTableToDomain(data.get("tablename"));
                assertTrue(domain.verifyTableAdded(data.get("tablename")));
                domain.setDrillQueryForTable("tablename");
                logger.info("Testcase createDomainWithOneTableWithDrillQuerySet completed");
        }
        @Test(dataProvider = "LoadData")
        public void createLovForDomain(Hashtable<String, String> data){
                logger.info("In createLovForDomain and run value is :"+data.get("Run") );
                createChildTest("Search for Domain");
                domain=home.navigateToDomainPage();
                assertTrue(domain.isDomainPresent(domainWithOneTable));
                createChildTest("Create LOV");
                String lovname = domain.createDomainLOV(domainWithOneTable,data.get("lov_type"),data.get("lov_query"));
                assertTrue(domain.isLOVcreated(lovname),"LOV creation failed");
                logger.info("Testcase createLovForDomain completed");
                //Utility.setValueInPropertyFile("Domain",domainName);
        }
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
                assertTrue(domain.verifyAllTablesAdded(tables));
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
                domain.verifyAllTablesAdded(tables);
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
                domain.createSuggestedJoinForTable(domainName,data.get("tablename"));
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
                //domainName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Domain");
                userName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"User_name");
                createChildTest("Search For Domain");
                domain = home.navigateToDomainPage();
                assertTrue(domain.isDomainPresent(simpleDomain),"Domain not present");
                createChildTest("Share Domain with User");
                domain.shareDomainWithUser(simpleDomain,userName);
                createChildTest("Login with other User and verify domain present");
                login = home.logOut();
                login.signInWithOtherUser(userName,user_password, new_password);
                assertTrue(home.verifyHomePage(),"Failed to login to application");
                domain=home.navigateToDomainPage();
                assertTrue(domain.isDomainPresent(simpleDomain),"Domain not present");
                logger.info("Testcase shareDomainWithAnUser completed");
        }
        @Test(dataProvider = "LoadData")
        public void addDrillTablesToDrillSet(Hashtable<String, String> data) throws InterruptedException {
                logger.info("In addDrillTablesToDrillSet and run value is :"+data.get("Run") );
                createChildTest("Search For Domain");
                domainName = Utility.getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"Domain");
                domain = home.navigateToDomainPage();
                assertTrue(domain.isDomainPresent(domainName),"Domain not present");
                createChildTest("Enable Drill Set For Tables");
                String[] tables = data.get("tablenames").split(",");
                String[] drilltables = Arrays.copyOfRange(tables,0,2);
                domain.setDrillQueryForTables(drilltables);
                assertEquals(drilltables.length,domain.drillTypeTableCount(),"Drill type table count not correct");
                createChildTest("Create Drill set with Tables");
                domain.createDrillset(domainName);
                assertTrue(domain.verifyAllTableInDrillSet(drilltables));
                logger.info("Testcase addDrillTablesToDrillSet completed");

        }

}
