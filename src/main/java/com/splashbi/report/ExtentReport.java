package com.splashbi.report;

import java.io.File;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.splashbi.utility.Constant;

public class ExtentReport {
	public ExtentReports extent;
	static Logger log = Logger.getLogger(ExtentReport.class);
	public ExtentReports getInstance(String testName) {
		System.out.println("Entered getInstance method and class name is "+ testName);
		File theDir = new File(String.valueOf(Constant.REPORT_PATH)); // Defining Directory/Folder Name
		try {
			if (!theDir.exists()) { // Checks that Directory/Folder Doesn't Exists!
				System.out.println("Directory " + Constant.REPORT_PATH + " does not exist");
				boolean result = theDir.mkdir();
				if (result) {
					JOptionPane.showMessageDialog(null, "New Folder created!");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//if(extent == null) {
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_") +"_"+testName+".html";
		    // String fileName = testName+".html";
			log.debug("File name is :" +fileName);
			extent = new ExtentReports(theDir + "/" + fileName, true, DisplayOrder.OLDEST_FIRST);
            log.debug("Dir name is :"+theDir+" and File Name is: "+fileName);
			extent.loadConfig(new File(Constant.EXTENT_CONFIG_PATH));
			// optional
			extent.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo("Environment", "QA");
		return extent;
	}

}
