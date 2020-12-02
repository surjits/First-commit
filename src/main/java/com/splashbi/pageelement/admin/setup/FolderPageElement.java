package com.splashbi.pageelement.admin.setup;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum FolderPageElement implements InitPageElement {
    VERIFY_FOLDER_HOME(getValueFromPropFile("verify_folder_home")),
    SEARCH_FOLDER(getValueFromPropFile("search_folder")),
    FOLDER_SEARCHED(getValueFromPropFile("searched_folder")),
    CREATE_FOLDER(getValueFromPropFile("create_folder_button")),
    CREATE_FOLDER_PAGE(getValueFromPropFile("verify_create_folder_page")),
    FOPLDER_NAME_FIELD(getValueFromPropFile("foldername_field")),
    BUSINESS_APP_LIST(getValueFromPropFile("business_app_list")),
    SAVE_FOLDER(getValueFromPropFile("save_folder")),
    BUSINESS_APP_NAME_LIST(getValueFromPropFile("business_app_name_list")),
    WARNING_POPUP(getValueFromPropFile("warning_popup"));

    private String loc;
    public String expression;
    WebElement e;
    FolderPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"FolderPage.properties",key);
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
