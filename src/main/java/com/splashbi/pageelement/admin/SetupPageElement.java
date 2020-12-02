package com.splashbi.pageelement.admin;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum SetupPageElement implements InitPageElement {
    SETUP_HOME(getValueFromPropFile("Setup_home")),
    FOLDER(getValueFromPropFile("folder")),
    BUSINESS_APP(getValueFromPropFile("business_application")),
    USER_GROUPS(getValueFromPropFile("usergroups"));

    private String loc;
    public String expression;
    WebElement e;
    SetupPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"SetupPage.properties",key);
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

