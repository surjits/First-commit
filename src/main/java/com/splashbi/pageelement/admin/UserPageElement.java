package com.splashbi.pageelement.admin;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum UserPageElement implements InitPageElement {
    USER_HOME_PAGE(getValueFromPropFile("user_home")),
    SEARCH_USER(getValueFromPropFile("search_user")),
    CREATE_USER(getValueFromPropFile("create_user_button")),
    CREATE_USER_PAGE(getValueFromPropFile("create_user_window")),
    USER_NAME(getValueFromPropFile("username")),
    USER_AUTHENTICATION_LIST(getValueFromPropFile("authentication_method_list")),
    SEND_EMAIL(getValueFromPropFile("send_email")),
    FIRST_NAME(getValueFromPropFile("first_name")),
    LAST_NAME(getValueFromPropFile("last_name")),
    LETME_CREATE_PASSWORD(getValueFromPropFile("letme_create_password")),
    ENTER_PASSWORD(getValueFromPropFile("enter_password")),
    EMAIL_ADDRESS(getValueFromPropFile("email")),
    SAVE_NEXT(getValueFromPropFile("save_next")),
    SUCCESS_MESSAGE(getValueFromPropFile("validate_success")),
    DISMISS_SUCCESS(getValueFromPropFile("dismiss_success")),
    //User Privuilege option
    CREATE_DOMAIN_PRIVILEGE(getValueFromPropFile("create_domain")),
    CREATE_REPORT_PRIVILEGE(getValueFromPropFile("create_report")),
    CREATE_DASHBOARD_PRIVILEGE(getValueFromPropFile("create_dashboard")),
    CREATE_CHART_PRIVILEGE(getValueFromPropFile("create_chart")),
    CREATE_REPORTSET_PRIVILEGE(getValueFromPropFile("create_reportset")),

    COPY_DOMAIN_PRIVILEGE(getValueFromPropFile("copy_domain")),
    COPY_REPORT_PRIVILEGE(getValueFromPropFile("copy_report")),
    COPY_DASHBOARD_PRIVILEGE(getValueFromPropFile("copy_dashboard")),
    COPY_CHART_PRIVILEGE(getValueFromPropFile("copy_chart")),
    COPY_REPORTSET_PRIVILEGE(getValueFromPropFile("copy_reportset")),

    SHARE_DOMAIN_PRIVILEGE(getValueFromPropFile("share_domain")),
    SHARE_REPORT_PRIVILEGE(getValueFromPropFile("share_report")),
    SHARE_DASHBOARD_PRIVILEGE(getValueFromPropFile("share_dashboard")),
    SHARE_CHART_PRIVILEGE(getValueFromPropFile("share_chart")),
    SHARE_REPORTSET_PRIVILEGE(getValueFromPropFile("share_reportset")),
    SHARE_REPORTSET_PROCESS_PRIVILEGE(getValueFromPropFile("share_reportset_process")),

    DISTRIBUTION_EMAIL_PRIVILEGE(getValueFromPropFile("distribution_email")),
    DISTRIBUTION_FTP_PRIVILEGE(getValueFromPropFile("distribution_ftp")),
    DISTRIBUTION_FILESYSTEM_PRIVILEGE(getValueFromPropFile("distribution_filesystem")),
    DISTRIBUTION_BURSTING_PRIVILEGE(getValueFromPropFile("distribution_bursting")),
    DISTRIBUTION_REPORTSET_PRIVILEGE(getValueFromPropFile("distribution_publishing")),

    SAVE_USER_EDIT(getValueFromPropFile("user_edit_save")),
    SAVE_NEXT_USER_EDIT(getValueFromPropFile("user_edit_save_next")),
    SAVE_USER_GROUP(getValueFromPropFile("save_user_group")),
    USER_DETAILS_TAB(getValueFromPropFile("user_details")),

    END(getValueFromPropFile("end"));
    private String loc;
    public String expression;
    WebElement e;
    UserPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"UsersPage.properties",key);
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
