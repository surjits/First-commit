package com.splashbi.pageelement;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.splashbi.pageobject.DomainPage;
import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;

public enum DynamicPageElement implements InitPageElement {
//****************Domain Page***********************************
	TABLE_TO_DRAG(getValueFromPropFile("table_to_drag")),
	DOMAIN_TABLE(getValueFromPropFile("domain_table")),
    SEARCHED_DOMAIN(getValueFromPropFile("searched_domain")),
    CREATED_DOMAIN_TAB(getValueFromPropFile("created_domain_tab")),
    DOMAIN_FILTER_TAB(getValueFromPropFile("domain_filter_tab")),
    CHECKBOX_TO_SELECT_DOMAIN(getValueFromPropFile("checkbox_to_select_domain")),
    CHECKBOX_TO_SELECT_AVAILABLE_USER(getValueFromPropFile("checkbox_to_select_available_user")),
    CHECKBOX_TO_ENABLE_SHARED_USER(getValueFromPropFile("checkbox_to_enable_shared_user")),

    DOMAIN_NAME_BUTTON(getValueFromPropFile("domain_name_button")),
    BUSINESS_APP_NAME(getValueFromPropFile("business_app")),
    DB_CONNECTOR(getValueFromPropFile("db_connector")),
    FILTER_TAB(getValueFromPropFile("filter_tab")),
    JOIN_TAB(getValueFromPropFile("joins_tab")),
    SGGESTED_JOINS(getValueFromPropFile("suggested_joins")),

    VERIFY_TABLE_IN_SUGGESTED_JOIN_WINDOW(getValueFromPropFile("verify_table_in_suggested_join_window")),
    CREATE_JOIN(getValueFromPropFile("create_join")),
    SELECT_ALL_JOIN_TABLE(getValueFromPropFile("table_join_save")),
    SEARCH_DATA_IN_JOIN(getValueFromPropFile("searched_data_in_join")),
    SEARCH_FIELD_IN_MASTER_TABLE(getValueFromPropFile("search_column_in_master_table")),
    ADD_FILTER(getValueFromPropFile("add_filter")),
    FILTER_NAME_TO_DRAG(getValueFromPropFile("filter_name_to_drag")),
    FILTER_NAME(getValueFromPropFile("filter_name")),
    FREE_TEXT_FOR_FILTER(getValueFromPropFile("freetext_for_filter_button")),
    INPUT_FREE_TEXT_AREA(getValueFromPropFile("free_text_area")),
    DOMAIN_EDIT(getValueFromPropFile("domain_edit")),
    TABLE_EDIT(getValueFromPropFile("domain_table_edit")),
    CLICK_DOMAIN(getValueFromPropFile("click_domain")),
    EDIT_DOMAIN(getValueFromPropFile("domain_edit")),
    EDIT_DOMAIN_TABLE(getValueFromPropFile("domain_table_edit")),
    MASTER_TABLE_IN_JOIN(getValueFromPropFile("master_table_in_join")),
	LOV_TYPE_VALUE(getValueFromPropFile("lov_type")),
	VERIFY_LOV(getValueFromPropFile("verify_lov")),
    TABLE_IN_AVAILABLE_DRILL_TABLES(getValueFromPropFile("table_in_available_drll_tables")),
    VERIFY_TABLE_IN_DRILL_SET(getValueFromPropFile("table_in_drill_set")),

//*********************************REPORT PAGE****************************************
    DOMAIN_NAME_IN_REPORT(getValueFromPropFile("click_domain_in_report")),
    EXPAND_DOMAIN_TABLE(getValueFromPropFile("expand_domain_table")),
    SELECT_ALL_CHECKBOX(getValueFromPropFile("selectall_checkbox")),
    SELECT_PROPERTY_COLUMN(getValueFromPropFile("select_property_column")),
    DRAG_COLUMN(getValueFromPropFile("drag_column")),
    VERIFY_REPORT_CREATION(getValueFromPropFile("verify_report_creation")),
	EXPAND(getValueFromPropFile("expandLink")),
    BUSINESS_APP_NAME_IN_REPORTSET(getValueFromPropFile("repset_business_app")),
    VERIFY_REPSET_TAB(getValueFromPropFile("verify_repset_tab")),
    //*********************************CONNECTOR PAGE****************************************
    DB_CONNECTOR_TYPE(getValueFromPropFile("db_connector_type")),
    CONNECTOR(getValueFromPropFile("connector")),
    CONNECTOR_TAB(getValueFromPropFile("connector_tab")),
    SYSTEM_TYPE(getValueFromPropFile("system_type")),
    DB_CONNECTION_TYPE(getValueFromPropFile("db_connector_type")),
    USER_IN_SHARE_LIST(getValueFromPropFile("user_in_share_list")),
    CONNECTION_TYPE(getValueFromPropFile("connection_type")),
    VERIFY_USER_IN_SHARE_TAB(getValueFromPropFile("verify_user_in_sharetab")),
    SELECT_AVAILABLE_USER(getValueFromPropFile("select_available_user")),
    //*********************************USERS PAGE****************************************
    USER_AUTHENTICATION_METHOD(getValueFromPropFile("authentication_method")),
    USER_SEARCHED(getValueFromPropFile("user_searched")),
    INFO_USER(getValueFromPropFile("user_info")),
    USERNAME_IN_DETAILS_TAB(getValueFromPropFile("username_in_details")),
   /* USER_CREATE_PRIVILEGE(getValueFromPropFile("user_create_privilege")),
    USER_COPY_PRIVILEGE(getValueFromPropFile("user_copy_privilege")),
    USER_SHARE_PRIVILEGE(getValueFromPropFile("user_share_privilege")),
    USER_DISTRIBUTION_PRIVILEGE(getValueFromPropFile("user_copy_privilege"));
*/
    //*********************************USERS GROUP PAGE****************************************
	AVAILABLE_USER(getValueFromPropFile("user_available")),
    USER_IN_MEMBERS(getValueFromPropFile("user_in_members")),
    //*********************************DASH BOARD PAGE****************************************
    DOMAIN_IN_DASHBOARD(getValueFromPropFile("domain_to_click")),
    TABLE_EXPAND_ICON(getValueFromPropFile("expand_table_icon")),
    COLUMN_TO_DRAG(getValueFromPropFile("column_to_drag")),
    VERIFY_COL_IN_DIMENSIONS(getValueFromPropFile("verify_col_in_dimensions")),
    VERIFY_COL_IN_MEASURES(getValueFromPropFile("verify_col_in_measures")),
    VERIFY_CHARTNAME_TAB(getValueFromPropFile("verify_chartname_tab")),
    CHART_TO_DRAG(getValueFromPropFile("chart_to_drag")),
    VERIFY_DRAGGED_CHART_NAME(getValueFromPropFile("chart_to_drag")),
    SELECT_CHART_DASHBOARD_TO_SHARE(getValueFromPropFile("select_chart_dashboard")),
    SELECT_USER_TO_SHARE(getValueFromPropFile("select_user_to_share")),
    VERIFY_USER_ADDED(getValueFromPropFile("verify_user_added")),
    ENABLE_SHARED_USER(getValueFromPropFile("enable_shared_user")),
    DASHBOARD_OBJECT_SEARCHED(getValueFromPropFile("item_to_share")),
    VERIFY_USER_AVAILABLE(getValueFromPropFile("verify_user_present")),
    SHARED_USER_IN_USERLIST(getValueFromPropFile("shared_user_in_userlist")),
    //*********************************ERP MAPPING PAGE****************************************
    SPLASHBI_EMP_NAME(getValueFromPropFile("splashbi_emp_name")),
    EBS_CONNECTION_NAME(getValueFromPropFile("ebs_connection_name")),
    //*********************************ERP MAPPING PAGE****************************************
    USER_SEARCHED_IN_USER_MAPPING(getValueFromPropFile("user_in_mapping_list")),
    //*********************************SETTINGS PAGE****************************************
    SETTING_OPTION(getValueFromPropFile("setting_option")),
	//****************************Sanity case*****************
    CURRENT_SETTING_IN_USER_SETTINGS(getValueFromPropFile("current_setting_in_user_settings_language"));

	private String loc;
	public String expression;
	WebElement e;
	static Logger logger = Logger.getLogger(DynamicPageElement.class);
	DynamicPageElement(String val){
    	
    	loc = val;
    	
    }
    
    public static String getValueFromPropFile(String key) {
    	String val=null;
    	try {
    		val = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"DynamicElement.properties",key);
    	}catch(Exception e) {
    		logger.error("Didn't find any key/value for given key", e);
    	}
    	return val;
       
    }
	 @Override
	    public By getBy() {
			return null;
		}
	 @Override
	 public By getDynamicBy(String val) {
		 return ElementLocator.getDynamicLocator(loc, val);
	 }

}
