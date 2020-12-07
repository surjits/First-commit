package com.splashbi.pageobject;

import com.relevantcodes.extentreports.ExtentTest;
import com.splashbi.pageobject.admin.ERPMappingPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SplashPage extends BasePage {
    Logger logger = Logger.getLogger(SplashPage.class);

    public SplashPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public SplashPage(WebDriver driver) {
        super(driver);
    }
    public SplashPage(){

    }
    public void setTest(ExtentTest test) {
        this.setExtentTest(test);

    }
    public void searchItem(String item){

    }
}
