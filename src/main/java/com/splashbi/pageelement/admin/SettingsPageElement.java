package com.splashbi.pageelement.admin;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum SettingsPageElement implements InitPageElement {
    USER_SETTINGS(getValueFromPropFile("user_settings")),
    VERIFY_SETTINGS_HOME(getValueFromPropFile("verify_settings_home")),
    VERIFY_USERSETTING(getValueFromPropFile("verify_globalsettings_tab")),
    ROLES_AND_PRIVILEGE(getValueFromPropFile("roles_and_privilege")),
    VISUAL(getValueFromPropFile("visual")),
    DASHBOARD_LISTVIEW_DISPLAY(getValueFromPropFile("dashboard_listview_display")),
    USERGROUP_CREATION(getValueFromPropFile("usergroup_creation")),
    USERGROUP_CREATION_WINDOW(getValueFromPropFile("verify_usergroup_creation_window")),
    DASHBOARD_LISTVIEW_DISPLAY_WINDOW(getValueFromPropFile("verify_dashboard_listview_display_window")),
    SITE_TAB(getValueFromPropFile("site_tab")),
    SITE_DROPDOWN(getValueFromPropFile("site_dropdown_list")),
    SITE_SETTING_LIST(getValueFromPropFile("site_select_list")),
    SETTINGS_LIST(getValueFromPropFile("settings_list")),
    SETTING_LIST(getValueFromPropFile("setting_list")),
    SELECT_ON(getValueFromPropFile("setting_on")),
    SELECT_OFF(getValueFromPropFile("select_off")),
    SETTING_ON(getValueFromPropFile("select_on")),
    SETTING_OFF(getValueFromPropFile("setting_off")),
    SETTING_BOTH(getValueFromPropFile("setting_both")),
    SETTING_SAVE(getValueFromPropFile("save_settings")),
    DEFAULT_SETTING(getValueFromPropFile("default_setting")),
    VALIDATE_SUCCESS(getValueFromPropFile("validate_success")),
    WARNING_POPUP(getValueFromPropFile("warning_popup")),
/***************************Sanity cases***********************************/
    LANGUAGE_IN_USER_SETTINGS(getValueFromPropFile("language_in_user_settings")),
    SETTING_USER_SEARCH(getValueFromPropFile("settings_user_serach")),
    SEARCHED_USER_DETAILS_LIST_IN_LANGUAGE_USER_SETTINGS(getValueFromPropFile("user_detail_list_in_user_settings_language")),
    END(getValueFromPropFile("end"));
    private String loc;
    public String expression;
    WebElement e;
    SettingsPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"SettingsPage.properties",key);
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
