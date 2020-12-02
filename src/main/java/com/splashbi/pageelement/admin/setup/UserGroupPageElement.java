package com.splashbi.pageelement.admin.setup;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum UserGroupPageElement implements InitPageElement {
    CREATE_USER_GROUP(getValueFromPropFile("create_usergroup_button")),
    SEARCH_USER_GROUP(getValueFromPropFile("search_usergroup")),
    SEARCHED_USERGROUP(getValueFromPropFile("searched_usergroupname")),
    USERGROUP_SEARCHED(getValueFromPropFile("usergroup_searched")),
    CREATE_USERGROUP_FORM(getValueFromPropFile("create_usergroup_form")),
    USERGROUP_NAME(getValueFromPropFile("usergroup_name_field")),
    AVAILABLE_USERGROUP_PANEL(getValueFromPropFile("available_usergroup_panel")),
    SEARCH_AVAILABLE_USERS(getValueFromPropFile("search_available_users")),
    SEARCH_SELECTED_USERS(getValueFromPropFile("search_selected_users")),
    ADD_USER(getValueFromPropFile("add_user_button")),
    SELECT_ALL_AVAILABLE_EMPLYEES(getValueFromPropFile("select_all_available_employees")),
    AVAILABLE_EMPLYEES_LIST(getValueFromPropFile("available_employees_list")),
    USERLIST_IN_SELECTED(getValueFromPropFile("userlist_in_selected")),

    SELECTED_EMPLOYEE_BUTTON(getValueFromPropFile("selected_employee_button")),
    EDIT_ENABLE(getValueFromPropFile("enable")),
    SAVE_USERGROUP(getValueFromPropFile("save_usergroup")),
    VERIFY_SUCESS(getValueFromPropFile("success_message")),
    CANCEL_USERGROUP(getValueFromPropFile("cancel_usergroup")),
    USERGROUP_CREATED_SUCCESS(getValueFromPropFile("success_message")),
    USERGROUP_ACTION_LIST(getValueFromPropFile("usergroup_action_list")),
    EDIT_USERGROUP(getValueFromPropFile("edit_usergroup")),
    INFO_USERGROUP(getValueFromPropFile("info_usergroup")),
    MEMBERS_TAB(getValueFromPropFile("members_tab")),
    DETAILS_TAB(getValueFromPropFile("details_tab")),
    USER_EXPAND(getValueFromPropFile("users_expand_button")),
    EXPAND_ALL(getValueFromPropFile("expand_all")),
    COLLAPSE_ALL(getValueFromPropFile("collapse_all")),
    USERLIST_IN_GROUP(getValueFromPropFile("userlist_in_members")),
    USER_GROUP_EXIST_WARNING(getValueFromPropFile("usergroup_exits_warning")),
    SEARCH_USER_IN_MEMBERS(getValueFromPropFile("search_user_in_members")),
    WARNING_POPUP(getValueFromPropFile("warning_popup"));

    private String loc;
    public String expression;
    WebElement e;
    UserGroupPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"UserGroupPage.properties",key);
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



