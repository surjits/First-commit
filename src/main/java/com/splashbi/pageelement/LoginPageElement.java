package com.splashbi.pageelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;

public enum LoginPageElement implements InitPageElement {
	USER_NAME(getValueFromPropFile("login_name")),
	PASSWORD(getValueFromPropFile("login_password")),
	LOGIN(getValueFromPropFile("login_button")),
	VERIFY_CHANGE_PASSWORD_WINDOW(getValueFromPropFile("verify_change_password_window")),
	CURRENT_PASSWORD(getValueFromPropFile("current_password")),
	NEW_PASSWORD(getValueFromPropFile("new_password")),
	VERIFY_CHANGE_PASSWORD(getValueFromPropFile("verify_changepassword")),
	CONFIRM_PASSWORD(getValueFromPropFile("confirm_password")),
	SAVE_PASSWORD(getValueFromPropFile("save_password")),
	SAVE_PASSWORD_OK(getValueFromPropFile("ok_button")),
	VERIFY_SUCCESS_WINDOW(getValueFromPropFile("verify_success_window")),
	WARNING(getValueFromPropFile("warning"));
	
	private String loc;
	public String expression;
	WebElement e;
    LoginPageElement(String val){
    	
    	loc = val;
    	
    }
    
    public static String getValueFromPropFile(String key) {
    	String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"LoginPage.properties",key);
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
