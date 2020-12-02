package com.splashbi.report;

import com.splashbi.setup.TestSetup;
import org.apache.log4j.Logger;

public class Log4j extends TestSetup {
	private static Logger Log = Logger.getLogger(Log4j.class);
	//Log = Logger.getLogger(“devpinoyLogger”);
	public static void info(String message) {
		 
		 Log.info(message);
		 
		 }
		 
		 public static void warn(String message) {
		 
		    Log.warn(message);
		 
		 }
		 
		 public static void error(String message) {
		 
		    Log.error(message);
		 
		 }
		 
		 public static void fatal(String message) {
		 
		    Log.fatal(message);
		 
		 }
		 
		 public static void debug(String message) {
		 
		    Log.debug(message);
		 
		 }

}
