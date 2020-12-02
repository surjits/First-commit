package com.splashbi.pageelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;

public enum LandingPageElement implements InitPageElement {
    HOME(getValueFromPropFile("home_Link")),
    REPORT(getValueFromPropFile("reportLink")),
    DOMAIN(getValueFromPropFile("domainLink")),
    ADMINISTRATOR(getValueFromPropFile("administratorLink")),
    EXPAND(getValueFromPropFile("expandLink"));
	
	private String loc;
	public String expression;
	WebElement e;
	LandingPageElement(String val){
    	
    	loc = val;
    	
    }
    
    public static String getValueFromPropFile(String key) {
    	String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"LandingPage.properties",key);
        return value;
    }
	 @Override
	    public By getBy() {
			return ElementLocator.getLocator(loc);
		}
	 
	 @Override
	 public By getDynamicBy(String val) {
         return null;
           
	 }


}

