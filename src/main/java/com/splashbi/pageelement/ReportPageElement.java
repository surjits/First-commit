package com.splashbi.pageelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.splashbi.utility.Constant;
import com.splashbi.utility.Utility;

public enum ReportPageElement implements InitPageElement{
	
	CREATE_DROPDOWN(getValueFromPropFile("create_dropdown_button")),
	VERIFY_DOMAINS_HEADER(getValueFromPropFile("verify_domains_header")),
	VERIFY_COLUMN_PROPERTIES_WINDOW(getValueFromPropFile("verify_column_properties_window")),
	VERIFY_ALL_OMAINS_TABLE(getValueFromPropFile("verify_all_domains_table")),
	CREATE_REPORT(getValueFromPropFile("create_report_button")),
	/*********************ReposrtSet Creation******************/
	CREATE_REPORT_SET(getValueFromPropFile("create_reportset_button")),
	REPORTLISTTABLE(getValueFromPropFile("reportlist")),
	REPORTS_CHECKBOX_LIST(getValueFromPropFile("reports_checkbox_list")),
	REPORTS_NAME_LIST(getValueFromPropFile("reports_name_list")),
	OK_ADD_REPORTS(getValueFromPropFile("ok_add_reports")),
	SAVE_REPORT_SET(getValueFromPropFile("save_reportset")),
	REPORTSET_NAME_FIELD(getValueFromPropFile("input_reportset_name")),
	BUSINESS_APP_LIST(getValueFromPropFile("business_app_list")),
	CREATE_FOLDER(getValueFromPropFile("create_folder")),
	NEW_FOLDER_NAME(getValueFromPropFile("new_folder_name")),
	REPORT_SET_SAVE(getValueFromPropFile("repset_save")),
	REPORTLIST_IN_REPORTSET(getValueFromPropFile("reportlist_in_reportset")),
	DOMAIN_SEARCH_FIELD(getValueFromPropFile("domain_search_field")),
	MOVE_TO_RIGHT(getValueFromPropFile("move_to_right")),
	ENTER_REPORT_NAME(getValueFromPropFile("input_report_name")),
	ERROR_POPUP(getValueFromPropFile("error_popup")),
	SELECT_ALL_REPORT_COLS(getValueFromPropFile("select_all_report_cols")),
	OK_IN_ERROR_POPUP(getValueFromPropFile("ok_in_error_popup")),
	SAVE_REPORT(getValueFromPropFile("save_report")),
	SAVE_REPORT_NAME(getValueFromPropFile("save_reportname")),
	VERIFY_REPORT_CREATED(getValueFromPropFile("verify_report_creation")),
	REPORT_FILTER_CRITERIA(getValueFromPropFile("report_filter_criteria")),
	ADD_COL_PROPERTY(getValueFromPropFile("add_column_property")),
	DROP_PLACE(getValueFromPropFile("drop_place")),
	SAVE_REPORT_FILTER(getValueFromPropFile("save_report_filter")),
	RUN_REPORT(getValueFromPropFile("run_report")),
	OPTION_MORE(getValueFromPropFile("more_option")),
    SUBMIT_REPORT(getValueFromPropFile("submit")),
	SUBMIT_SUCCESS(getValueFromPropFile("verify_success")),
	SQL_SELECT_CONTENT(getValueFromPropFile("sql_select_content")),
	VIEW_SQL(getValueFromPropFile("view_sql"));
	
	private String loc;
	public String expression;
	WebElement e;
	ReportPageElement(String val){
    	
    	loc = val;
    	
    }
    
    public static String getValueFromPropFile(String key) {
    	String value = Utility.getValueFromPropertyFile(Constant.OR_PATH+"/"+"ReportPage.properties",key);
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

