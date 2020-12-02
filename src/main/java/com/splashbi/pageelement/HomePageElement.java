package com.splashbi.pageelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;

public enum HomePageElement implements InitPageElement {
	    HOME_LINK(getValueFromPropFile("homeLink")),
	    HOME(getValueFromPropFile("home")),
	    REPORT(getValueFromPropFile("reportLink")),
	    DASHBOARD(getValueFromPropFile("dashboardLink")),
	    DOMAIN(getValueFromPropFile("domainLink")),
	    ADMINISTRATOR(getValueFromPropFile("administratorLink")),
	    LOGOUT(getValueFromPropFile("logout")),
	    VERIFY_LOGOUT(getValueFromPropFile("verify_logout")),
	    PROFILE_IMAGE(getValueFromPropFile("profile_image")),
	    EXPAND(getValueFromPropFile("expandLink"));
		
		private String loc;
		public String expression;
		WebElement e;
		HomePageElement(String val){
	    	
	    	loc = val;
	    	
	    }
	    
	    public static String getValueFromPropFile(String key) {
	    	String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"HomePage.properties",key);
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
