package com.splashbi.pageelement.admin;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum AdminPageElement implements InitPageElement {
    ADMIN_HOME(getValueFromPropFile("admin_home")),
    CONNECTORS(getValueFromPropFile("connectors")),
    USERS(getValueFromPropFile("users")),
    SETUP(getValueFromPropFile("Setup")),
    SETTINGS(getValueFromPropFile("Settings")),
    ERP_MAPPING(getValueFromPropFile("erp_mapping")),
    END(getValueFromPropFile("submit"));
    private String loc;
    public String expression;
    WebElement e;
    AdminPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"AdminPage.properties",key);
        return value;
    }
    @Override
    public By getBy() {
        return ElementLocator.getLocator(loc);
    }

    @Override
    public org.openqa.selenium.By getDynamicBy(String val) {
        return null;

    }
}
