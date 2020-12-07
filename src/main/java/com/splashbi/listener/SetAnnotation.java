package com.splashbi.listener;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.splashbi.utility.Utility;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import com.splashbi.setup.TestSetup;
import com.splashbi.utility.Constant;
import com.splashbi.utility.ExcelUtility;

public class SetAnnotation implements IAnnotationTransformer {


	  public static ExcelUtility xl = new ExcelUtility(Constant.TEST_DATA);
	  public static String sheet = TestSetup.sheetName;
	 
	 /* public boolean getRunValueOfTest(String testname) {
		  Object[][] getTestData = Utility.getData(new File(Constant.TEST_DATA_JSON), testname);
		  boolean run = true;
		  if(xl.getDataForTestName(xl, sheet, testname, "Run").equals("No")) {
		  run = false;
		  }
		  return run;
	  }*/
	public boolean getRunValueOfTest(String testname) {
		boolean run = true;
		if(Utility.getValueForKey(new File(Constant.SANITY_TEST_DATA),testname,"Run").equals("No")) {
			run = false;
		}
		return run;
	}
	  
	   public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) 
	   {
		   {
	         annotation.setEnabled(false);
	         System.out.println("Disabled run value for test: "+testMethod.getName());
	      }
		
	   }

}
