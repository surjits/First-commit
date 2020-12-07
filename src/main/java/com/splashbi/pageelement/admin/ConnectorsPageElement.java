package com.splashbi.pageelement.admin;

import com.splashbi.pageelement.ElementLocator;
import com.splashbi.pageelement.InitPageElement;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum ConnectorsPageElement implements InitPageElement {
    CONNECTOR_HOME(getValueFromPropFile("connectors_home")),
    CHECK_STATUS_OF_ALL_CONNECTORS(getValueFromPropFile("check_status_of_all_connectors")),
    LIST_OF_VALID_CONNECTORS(getValueFromPropFile("valid_connector_list")),
    LIST_OF_INVALID_CONNECTORS(getValueFromPropFile("invalid_connector_list")),
    STATUS_CHECK_LOADER_IMG(getValueFromPropFile("status_loader_img")),
    SEARCH_CONNECTOR(getValueFromPropFile("search_connector")),
    ORACLE_EBS(getValueFromPropFile("oracle_EBS")),
    CREATE_CONNECTORS(getValueFromPropFile("create_connector")),
    DB_CONNECTORS_HEADER(getValueFromPropFile("db_connectors_header")),
    VERIFY_CREATE_CONNECTOR(getValueFromPropFile("verify_create_connector")),
    ORACLE_EBS_INFO(getValueFromPropFile("oracle_ebs_info")),
    TEST_CONNECTOR(getValueFromPropFile("test_connector")),
    SYSTEM_ITEM_LIST(getValueFromPropFile("system_item_list")),
    CONNECTOR_VALIDATE(getValueFromPropFile("connector_success_popup")),
    INFO_CONNECTOR(getValueFromPropFile("info_connector")),
    EDIT_CONNECTOR(getValueFromPropFile("edit_connector")),
    CREATED_CONNECTOR_IMAGE(getValueFromPropFile("created_connector_image")),
    CONNECTION_ACTION_LIST(getValueFromPropFile("connector_action_list")),
    /*********************Buttons in Create Connector*******************************/
    CONNECTOR_NAME(getValueFromPropFile("connector_name")),
    SYSTEM_TYPE_LIST(getValueFromPropFile("system_list")),
    CONNECTION_TYPE_LIST(getValueFromPropFile("connection_type_list")),
    CONNECTION_TYPE_VALUE(getValueFromPropFile("connection_type_value")),
    HOST_NAME(getValueFromPropFile("host_name")),
    SID(getValueFromPropFile("sid")),
    CONNECTION_USER(getValueFromPropFile("connection_user")),
    CONNECTION_PASSWORD(getValueFromPropFile("connection_password")),
    PORT_NUMBER(getValueFromPropFile("port_number")),
    SCHEMA_NAME(getValueFromPropFile("schema_name")),

    CREATE_CONNECTOR_TEST(getValueFromPropFile("db_create_test_connector")),
    CREATE_CONNECTOR_SAVE(getValueFromPropFile("db_create_save_connector")),
    CONNECTOR_SAVE_SUCCESS_POPUP(getValueFromPropFile("create_connector_success_popup")),

    /*********************Elementsin in share Connector*******************************/
    SHARE_CONNECTOR_OPTION(getValueFromPropFile("share_connector")),
    VERIFY_SHARE_CONNECTOR_PAGE(getValueFromPropFile("verify_share_connection_page")),
    AVAILABLE_USER_SEARCH(getValueFromPropFile("available_user_search_field")),
    AVAILABLE_USER_LIST_TO_SHARE(getValueFromPropFile("available_users_list")),
    SHARED_USER_LIST(getValueFromPropFile("shared_users_list")),
    SEARCHED_FIRST_AVAILABLE_USER_TO_SHARE(getValueFromPropFile("searched_first_available_user_checkbox")),
    SHIFT_RIGHT_BUTTON(getValueFromPropFile("shift_right_button")),
    SAVE_SHARE(getValueFromPropFile("save_share")),
    CONNECTOR_SHARE_TAB(getValueFromPropFile("connector_share_tab")),
    EXPAND_ALL(getValueFromPropFile("expand_all")),
    END(getValueFromPropFile("end"));
    private String loc;
    public String expression;
    WebElement e;
    ConnectorsPageElement(String val){

        loc = val;

    }
    public static String getValueFromPropFile(String key) {
        String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"ConnectorsPage.properties",key);
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
