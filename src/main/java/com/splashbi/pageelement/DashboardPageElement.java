package com.splashbi.pageelement;

import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum DashboardPageElement implements InitPageElement {
    VERIFY_DASHBOARD_HOME(getValueFromPropFile("verify_dashboard_home")),
    CREATE_DROPDOWN(getValueFromPropFile("create_dropdown_button")),
    SEARCH_CHART_DASHBOARD(getValueFromPropFile("search_field")),
    SELECT_ALL(getValueFromPropFile("select_all")),
    SHARE_ALL_ICON(getValueFromPropFile("share_all_icon")),
    SERACH_USER_TO_SHARE(getValueFromPropFile("serach_user_to_share")),
    ADD_USER_TO_SHARE(getValueFromPropFile("add_user_to_share")),
    SAVE_DASHBOARD_SHARE(getValueFromPropFile("save_dashboard_share")),
/******************************DashBoard Info**************************************/
    DASHBORAD_INFO(getValueFromPropFile("dashboard_info")),
    DASHBOARD_DETAILS_TAB(getValueFromPropFile("dashboard_details_tab")),
    DASHBOARD_SHARE_TAB(getValueFromPropFile("dashboard_share_tab")),
    DASHBOARD_USAGE_TAB(getValueFromPropFile("dashboard_usage_tab")),
    DASHBOARD_SHARE_EXAPND_ALL(getValueFromPropFile("dashboard_share_expand_all")),
    SELECT_CHECKBOX_FOR_USER_TO_SHARE(getValueFromPropFile("select_checkbox_for_user")),
    /*************************************************************************/
    CREATE_CHART(getValueFromPropFile("create_chart")),
    VERIFY_CREATE_VIEW(getValueFromPropFile("create_view")),
    DOMAIN_SEARCH(getValueFromPropFile("domain_search_field")),
    VERIFY_NEWCHART_TAB(getValueFromPropFile("verify_new_chart_tab")),
    VERIFY_UNTITLED_CHART_TAB(getValueFromPropFile("verify_untitle_chart")),
    TARGET_DROP_LOC_IN_DIMENSION(getValueFromPropFile("target_area_in_dimension")),
    TARGET_DROP_LOC_IN_MEASURES(getValueFromPropFile("target_area_in_measures")),
    SAVE_UNTITLE_CHART(getValueFromPropFile("save_untitle_chart")),
    ENTER_CHART_NAME(getValueFromPropFile("enter_chart_name")),
    SAVE_TITLED_CHART(getValueFromPropFile("save_titled_chart")),
 /*******************************************************************/
    CREATE_DASHBOARD(getValueFromPropFile("create_dashboard")),
    VERIFY_DASHBOARD_TAB(getValueFromPropFile("dashboard_tab")),
    SEARCH_DOMAIN_FOR_DASHBOARD(getValueFromPropFile("search_domain")),
    CHART_DROP_AREA(getValueFromPropFile("chart_drag_drop_area")),
    VERIFY_CHART_DRAGGED(getValueFromPropFile("verify_chart_dragged")),
    SAVE_UNTITLED_DASHBOARD(getValueFromPropFile("save_untitled_dashboard")),
    ENTER_DASHBOARD_NAME(getValueFromPropFile("enter_dashboard_name")),
    ENTER_DESCRIPTION(getValueFromPropFile("Dashboard_description")),
    BUSINESS_APP_DROPDOWN(getValueFromPropFile("dashboard_business_app")),
    BUSINESS_APP_LIST(getValueFromPropFile("business_app_list")),
    SAVE_TITLED_DASHBOARD(getValueFromPropFile("save_titled_dashboard")),


    EXPAND(getValueFromPropFile("expandLink"));

    private String loc;
    public String expression;
    WebElement e;
    DashboardPageElement(String val){

        loc = val;

    }

    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"DashboardPage.properties",key);
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
