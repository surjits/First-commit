package com.splashbi.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;
public class ExcelUtility {
	public String filepath = null;
	public String sheetname = null;
	public String filaname = null;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	//private Workbook workbook = null;
	private XSSFWorkbook workbook = null;
	private Sheet sheet = null;
	private Row row   =null;
	private Cell cell = null;
	private FormulaEvaluator evaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
	private DataFormatter formatter = new DataFormatter();
	public Logger logger = Logger.getLogger(ExcelUtility.class);
	//String log4j = Constant.LOG4J_PATH;
	
	public ExcelUtility(String path) {
		this.filepath = path;
		//PropertyConfigurator.configure(log4j);
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setSheet(String sheet) {
		sheetname = sheet;
	}
	
	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return (sheet.getLastRowNum()-sheet.getFirstRowNum());
	}
	
	public int getRowNumByFirstColVal(String sheetname, String val) {
		int rowNum = 0;
		try {
			
			for(int i = 0; i <= getRowCount(sheetname); i++) {
				if (workbook.getSheet(sheetname).getRow(i).getCell(0).getStringCellValue().trim().equals(val))
				{
					rowNum = i;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowNum;
	}
	
	public String getCellDataByRowAndColNum(String sheetname,int rowNum, int colNum) {
		Row row = workbook.getSheet(sheetname).getRow(rowNum);
		Cell cell = row.getCell(colNum);
		return formatter.formatCellValue(cell,evaluator);
	}
	
	public String getCellDataByRowNumAndColName(String sheetname, int rowNum, String colName) {
		
		Row row = workbook.getSheet(sheetname).getRow(rowNum);
		Cell cell = row.getCell(getColNumByColName(sheetname,colName));
		String cellValue = formatter.formatCellValue(cell,evaluator);
		
		return cellValue;
	}
	
	public int getColNumByColName(String sheetname, String colname) {
		int colNum = 0;
		Row row = workbook.getSheet(sheetname).getRow(0);
		for (int i = 1; i < row.getLastCellNum();i++) {
			if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname)) {
				colNum = i;
			}
		}
		return colNum;
	}
	
	public String getDataForTestName(ExcelUtility xls, String Sheet, String rowName, String colName) {
		int rows = xls.getRowCount(Sheet);
		String colVal = null;
		for (int rNum = 1; rNum <= rows; rNum++) {
			String tcName = xls.getCellDataByRowNumAndColName(Sheet, rNum,"TC_Name");
			if (tcName.equals(rowName)) {
				colVal = (xls.getCellDataByRowNumAndColName(Sheet, rNum,colName));			
				break;
			}
		}
		return colVal;
	}
	
	public Object[][] getData(ExcelUtility xls, String sheetName,String tcName) {
		    System.out.println("TC name is: " +tcName);
			int testRowNum=0;
			
			while(!xls.getCellDataByRowNumAndColName(sheetName, testRowNum,"TC_NAME").equals(tcName)){
				testRowNum++;
			}
			int cols=0;
			while(!xls.getCellDataByRowAndColNum(sheetName, 0, cols).equals("")){
				cols++;
			}
			 //System.out.println("Test col num is :"+cols);
			int lastDataRownum = testRowNum;
			Object[][] data = new Object[1][1];
			int dataRow=0;
			Hashtable<String,String> table=null;
			for(int rNum=testRowNum;rNum<=lastDataRownum;rNum++){
				table = new Hashtable<String,String>();
				for(int cNum=0;cNum<cols;cNum++){
					String key=xls.getCellDataByRowAndColNum(sheetName,0,cNum);
					String value= xls.getCellDataByRowAndColNum(sheetName, testRowNum, cNum);
					table.put(key, value);
				}
				data[dataRow][0] =table;
				dataRow++;
			}
			
			return data;
		}



public static void main(String args[]) throws Exception {
	ExcelUtility xl = new ExcelUtility(Constant.TEST_DATA);
	System.out.println("Number of rows = "+xl.getRowCount("scope1"));
	System.out.println("Col num for col Run is = "+(xl.getColNumByColName("scope1", "Run")));
	System.out.println("Value in Run col in 1st row : "+xl.getCellDataByRowNumAndColName("scope1", 1, "Status"));
}

}
