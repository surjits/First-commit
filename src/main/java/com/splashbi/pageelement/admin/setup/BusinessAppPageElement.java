package com.splashbi.pageelement.admin.setup;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum BusinessAppPageElement implements InitPageElement {
    BUSINESS_APP_HOME(getValueFromPropFile("verify_home")),
    SEARCH_BUSINESSAPP(getValueFromPropFile("search_business_app")),
    BUSINESSAPP_SEARCHED(getValueFromPropFile("searched_businessapp")),
    CREATE_BUSINESS_APP(getValueFromPropFile("create_businessapp_button")),
    CREATE_BUSINESS_APP_HOME(getValueFromPropFile("verify_create_businessapp_page")),
    BUSINESS_APP_NAME_FIELD(getValueFromPropFile("business_appname_field")),
    SAVE_BUSINESS_APP(getValueFromPropFile("save_business_app")),
    WARNING_POPUP(getValueFromPropFile("warning_popup"));

    private String loc;
    public String expression;
    WebElement e;
    BusinessAppPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"BusinessappPage.properties",key);
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
