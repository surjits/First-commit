package com.splashbi.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.splashbi.utility.Constant;

public class ExecutionReport {
	private static final String executionReportTemplateFile = Constant.EXECUTION_REPORT_PATH;

	public void generateExecutionReport(int totalCount, int passCount, int failCount, int skipCount, Date startDate,  Date endDate) {
		System.out.println("Entering execution report method:");
		File theDir = new File(String.valueOf(Constant.REPORT_PATH));
		try {
			if (!theDir.exists()) {
				System.out.println("Directory " + Constant.REPORT_PATH + " does not exist");
				boolean dirExist = theDir.mkdir();
				if (dirExist) {
					JOptionPane.showMessageDialog(null, "New Folder created!");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		try {
		String customReportTemplateStr = this.readEmailabelReportTemplate();
		String customReportTitle = this.getCustomReportTitle("Custom TestNG Report");
		String customSuiteSummary= this.getExecutionSummary(totalCount,passCount,failCount,skipCount,startDate,endDate);
		customReportTemplateStr = customReportTemplateStr.replaceAll("\\$TestNG_Custom_Report_Title\\$",customReportTitle);
		customReportTemplateStr = customReportTemplateStr.replaceAll("\\$Test_Case_Summary\\$", customSuiteSummary);

			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")+ "execution-report.html";
			File targetFile = new File(theDir + "/"+fileName);
			FileWriter fw = new FileWriter(targetFile);
			fw.write(customReportTemplateStr);
			fw.flush();
			fw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("finally")
	private String getExecutionSummary(int totalCount, int passCount, int failCount, int skipCount, Date startDate, Date endDate) {
		StringBuffer retBuf = new StringBuffer();
		System.out.println("End time is :" + endDate);
		try {
			retBuf.append("<tr>");
			// Test name.
			retBuf.append("<td>");
			retBuf.append("Pre-Sanity Test");
			retBuf.append("</td>");

			// Total method count.
			retBuf.append("<td bgcolor=lightblue>");
			// retBuf.append(" ");
			retBuf.append(totalCount);
			retBuf.append("</td>");

			// Passed method count.
			retBuf.append("<td bgcolor=lightgreen>");
			retBuf.append(passCount);
			retBuf.append("</td>");

			// Skipped method count.
			retBuf.append("<td bgcolor=yellow>");
			retBuf.append(skipCount);
			retBuf.append("</td>");

			// Failed method count.
			retBuf.append("<td bgcolor=red>");
			retBuf.append(failCount);
			retBuf.append("</td>");

			// Get browser type.
			String browserType = "Chrome";

			// Append browser type.
			retBuf.append("<td>");
			retBuf.append(browserType);
			retBuf.append("</td>");

			// Start Date
			retBuf.append("<td>");
			retBuf.append(this.getDateInStringFormat(startDate));
			retBuf.append("</td>");

			// End Date
			retBuf.append("<td>");
			retBuf.append(this.getDateInStringFormat(endDate));
			retBuf.append("</td>");

			// Execute Time
			//long deltaTime = endDate.getTime() - startDate.getTime();
			long deltaTime = findDifference(startDate.toString(),endDate.toString());
			System.out.println("Time diff is"+deltaTime);
			String deltaTimeStr = this.convertDeltaTimeToString(deltaTime);
			System.out.println("Time diff is"+deltaTimeStr.toString());
			retBuf.append("<td>");
			retBuf.append(deltaTimeStr);
			retBuf.append("</td>");
			retBuf.append("</tr>");
			// System.out.println("Time taken is:"+deltaTimeStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}
		 
	private String getCustomReportTitle(String title) {
		StringBuffer retBuf = new StringBuffer();
		retBuf.append(title + " " + this.getDateInStringFormat(new Date()));
		return retBuf.toString();
	}
	public static long 	findDifference(String start_date, String end_date) {
       long difference_In_Time=0;
		// SimpleDateFormat converts the
		// string format to date object
		SimpleDateFormat sdf
				= new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");

		try {

			Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);

			difference_In_Time = d2.getTime() - d1.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return difference_In_Time;
	}
		private String getDateInStringFormat(Date date) {
		StringBuffer retBuf = new StringBuffer();
		if (date == null) {
			date = new Date();
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		retBuf.append(df.format(date));
		return retBuf.toString();
	}

	private String convertDeltaTimeToString(long deltaTime) {
		StringBuffer retBuf = new StringBuffer();
		long milli = deltaTime;
		long seconds = deltaTime / 1000 % 60;
		long minutes = deltaTime / (60 * 1000) % 60;
		long hours = deltaTime / (60 * 60 * 1000) % 24;
		retBuf.append(hours + ":" + minutes + ":" + seconds);
		
		return retBuf.toString();
	}

	@SuppressWarnings("finally")
	private String readEmailabelReportTemplate() {
		StringBuffer retBuf = new StringBuffer();
		try {
			@SuppressWarnings("static-access")
			File file = new File(this.executionReportTemplateFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				retBuf.append(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

}
