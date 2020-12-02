package com.splashbi.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.splashbi.pageelement.LandingPageElement.*;

import java.io.IOException;

import com.relevantcodes.extentreports.ExtentTest;

public class LandingPage extends BasePage {
	
	Logger logger = Logger.getLogger(LoginPage.class);
	DomainPage dpage = null;
    public LandingPage(WebDriver driver, ExtentTest test) {
    	super(driver, test);
	}
    
    public DomainPage navigateToDomainPage() throws Exception {
    	clickButton(DOMAIN,"");
    	dpage = new DomainPage(driver,test);
    	return dpage;
    }

}
