package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This method will read and write data from excel file
 * @author Ashwini
 *
 */
public class ExcelFileUtility {
	
	/**
	 * This method will read the data from excel sheet and return the value when sheetname, rowno and cell number is specified
	 * @param SheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcel(String SheetName, int rowNo, int cellNo) throws Throwable
	{
		FileInputStream fis= new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(rowNo);
		Cell cel = row.getCell(cellNo);
		String value = cel.getStringCellValue();
		return value;
	}
	
	/**
	 * This method will write the data into the excel sheet
	 * @param SheetName
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String SheetName, int rowNo, int cellNo, String value) throws Throwable
	{
		FileInputStream fis= new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		Cell cel = ro.createCell(cellNo);
		cel.setCellValue(value);
		FileOutputStream fout=new FileOutputStream(IPathConstants.ExcelPath);
		wb.write(fout);	
		wb.close();
	}
	
	/**
	 * This method will return last row number
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String SheetName) throws Throwable
	{
		FileInputStream fis= new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int row = sh.getLastRowNum();
		return row;		
	}
	
	/**
	 * This Method will read the multiple data from excel sheet with the help of sheet name and return 2 dimensional object [][]
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 */
	public Object[][] readMultipleDataFromExcel(String SheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);	
		Sheet sh = wb.getSheet(SheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();
		Object[][] data=new Object[lastRow][lastCell];
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;		
	}
}
