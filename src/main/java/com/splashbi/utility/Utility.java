package com.splashbi.utility;

import java.io.*;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;

import com.google.gson.*;
import com.splashbi.pageobject.admin.ConnectorsPage;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utility {
	static Logger logger = Logger.getLogger(Utility.class);
public static String getValueFromPropertyFile(String fileloc,String key) {
		
		Properties prop = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream(new File(fileloc));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
		     prop.load(f);
		}catch(IOException e) {
			System.out.println("Not able to load property file");
		}
				
		return prop.getProperty(key);
	}
	public static String getValueFromDataFile(String key) {

		Properties prop = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream(new File(Constant.DATA_OUTPUT_PATH));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(f);
		}catch(IOException e) {
			System.out.println("Not able to load property file");
		}

		return prop.getProperty(key);
	}
	public static void setValueInPropertyFile(String key, String val){

		Properties prop = new Properties();

		FileInputStream f = null;
		try {
			f = new FileInputStream(new File(Constant.DATA_OUTPUT_PATH));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(f);
		}catch(IOException e) {
			System.out.println("Not able to load property file");
		}
		prop.setProperty(key,val);
		try {
			FileOutputStream fout = new FileOutputStream(new File(Constant.DATA_OUTPUT_PATH));
			prop.store(fout, "updated");
			fout.close();
		}catch(Exception e){
			logger.debug("Error in writing to File",e);
		}

		//return prop.getProperty(key);
	}
	public static void SetValueInpropfile(String key, String val ) throws IOException {
	Properties prop = new Properties();
	prop.setProperty(key,val);
	File file =new File(Constant.DATA_OUTPUT_PATH);
	FileOutputStream fout = new FileOutputStream(file);
	prop.store(fout,"updated");
	fout.close();

	}
public static String getRandomNumber(String prefix)
{
	Random random = new Random();
	String staticNum = prefix;
	int randomNum = random.nextInt(90) + 10;
	String randomValue = Integer.toString(randomNum);
	String randomNumber = staticNum+randomValue;
	return randomNumber;
}

public static String getExpressionForDynamiLocator(String filename,String key) {
	String value="";
	value = getValueFromPropertyFile(Constant.OR_PATH+"/"+filename,key);
	return value;
}
public static Object[][] getData(File jsonfilepath, String testCaseName){ 
	Gson gson = new GsonBuilder().create();
	 JsonObject job = null;
	try {
		job = gson.fromJson(new FileReader(jsonfilepath), JsonObject.class);
	} catch (JsonSyntaxException e) {
		e.printStackTrace();
	} catch (JsonIOException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}		 
	 JsonElement cate= job.getAsJsonArray(testCaseName);
	 int trows= job.getAsJsonArray(testCaseName).size();
	 System.out.println("Row 1: "+job.getAsJsonArray(testCaseName).get(0));
	 System.out.println("Total Rows: "+trows);		
	 
	 Hashtable<String, String> table=null;
	 int index=0;
	 Object data[][]=new Object[trows][1];
	 
	 System.out.println("============================================================================================================================");
	 table=new Hashtable<String, String>();
	 for(int rowNum=0;rowNum<trows;rowNum++) {
		 JsonElement rowdata= job.getAsJsonArray(testCaseName).get(rowNum);
		 String values=rowdata.toString().substring(1, rowdata.toString().length()-1);
		 String[] splitvalues= values.split("\",");
		 int tcols = splitvalues.length;
		 System.out.println("Total Columns: "+splitvalues.length);
		 table=new Hashtable<String, String>();
	 for(int c=0;c<splitvalues.length;c++) {
		 if(c<splitvalues.length-1) {
		 table.put(splitvalues[c].split("\":")[0].substring(1, splitvalues[c].split("\":")[0].length()), splitvalues[c].split("\":")[1].substring(1, splitvalues[c].split("\":")[1].length()));
		 //System.out.println(splitvalues[c].split(":")[0].substring(1, splitvalues[c].split(":")[0].length()-1)+"=="+splitvalues[c].split(":")[1].substring(1, splitvalues[c].split(":")[1].length()));
		 System.out.println(splitvalues[c].split("\":")[0].substring(1, splitvalues[c].split("\":")[0].length())+"=="+splitvalues[c].split("\":")[1].substring(1, splitvalues[c].split("\":")[1].length()));
		 }else {
			 table.put(splitvalues[c].split("\":")[0].substring(1, splitvalues[c].split("\":")[0].length()), splitvalues[c].split("\":")[1].substring(1, splitvalues[c].split("\":")[1].length()-1));
			 System.out.println(splitvalues[c].split("\":")[0].substring(1, splitvalues[c].split("\":")[0].length())+"=="+splitvalues[c].split("\":")[1].substring(1, splitvalues[c].split("\":")[1].length()-1));				 
		 }			 
	 }
	 //System.out.println("Row Data: "+table);
	 data[index][0]=table;	
	 index++;
	 System.out.println();
	 }
	 System.out.println("===========================================================================================================================");
	return data;		
}

public static String getValueForKey(File jsonfilepath, String testCaseName, String key){
	String value = null;
	try {
		Gson gson = new GsonBuilder().create();
		JsonObject job = null;
		try {
			job = gson.fromJson(new FileReader(jsonfilepath), JsonObject.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JsonElement jsonElement = job.getAsJsonArray(testCaseName);
		JsonArray js = jsonElement.getAsJsonArray();


		JsonObject rootObject = js.get(0).getAsJsonObject();
		value = rootObject.get(key).getAsString();
		//	value = rowdata.get("Run").getAsString();
		JsonArray child = rootObject.getAsJsonArray("Create");
		System.out.println(child);

	}catch(Exception e){
		System.out.println("Failed");
		e.printStackTrace();
	}

	return value;
}
public Object[][] testDataSet() {
	Hashtable<String, String> table=null;
	return getData(new File("F://Automation//SplashBi//Testdata//smoketest.json"), "TC_DOM_TABLE_FILTERS_014");
}

public static void main(String args[]) throws IOException {


	setValueInPropertyFile("user_name","test");
	String val1="test";
	String val2 ="test1";
	System.out.println("value is" +val1+" "+val2);
	if(getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"user_name").equalsIgnoreCase("")){
		System.out.println("value not updated");
	}else{
		System.out.println("value updated and name="+getValueFromPropertyFile(Constant.DATA_OUTPUT_PATH,"user_name"));
	}
}
  

}
