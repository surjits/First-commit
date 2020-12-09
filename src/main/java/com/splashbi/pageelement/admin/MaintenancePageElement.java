package com.splashbi.pageelement.admin;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum MaintenancePageElement implements InitPageElement {
    MAINTENANCE_HOME(getValueFromPropFile("maintenance_home")),
    INPUT_SEARCH_PROCESS(getValueFromPropFile("input_search_process")),
    APPLY_BUTTON(getValueFromPropFile("apply_button")),
    REFRESH_BUTTON(getValueFromPropFile("refresh_button")),
    PROCESS_RECORDS(getValueFromPropFile("process_records")),
    FIRST_SEARCH_RECORD(getValueFromPropFile("first_searched_record")),
    PROCESS_STATUS(getValueFromPropFile("process_status")),
    END(getValueFromPropFile("submit"));
    private String loc;
    public String expression;
    WebElement e;
    MaintenancePageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"MaintenancePage.properties",key);
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

