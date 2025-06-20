package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	public String[][] getExcelData(String excelSheetName, int startRow, int startCol)
	        throws EncryptedDocumentException, IOException {

	    Common_Utility common_utility = new Common_Utility();
	    Properties prop = common_utility.getConfigProperties();
	    File file = new File(prop.getProperty("excelsheet_path"));
	    System.out.println("Reading Excel file from: " + file.getAbsolutePath());
	    try (FileInputStream fis = new FileInputStream(file);
	         Workbook wb = WorkbookFactory.create(fis)) {
	        Sheet sheet = wb.getSheet(excelSheetName);
	        if (sheet == null) {
	            System.out.println("Sheet '" + excelSheetName + "' not found.");
	            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	                System.out.println(" - " + wb.getSheetName(i));
	            }
	            throw new IllegalArgumentException("Sheet '" + excelSheetName + "' does not exist in the Excel file.");
	        }
	        Row header = sheet.getRow(0);
	        if (header == null) {
	            throw new IllegalStateException("Header row is empty in sheet: " + excelSheetName);
	        }
	        int totalRows = sheet.getLastRowNum();
	        int totalCols = header.getLastCellNum();
	        int effectiveRows = totalRows - startRow + 1;
	        int effectiveCols = totalCols - startCol;
	        String[][] testData = new String[effectiveRows][effectiveCols];
	        DataFormatter formatter = new DataFormatter();
	        System.out.println("Reading data from Sheet: " + excelSheetName);
	        for (int i = startRow, a = 0; i <= totalRows; i++, a++) {
	            Row row = sheet.getRow(i);
	            for (int j = startCol, b = 0; j < totalCols; j++, b++) {
	                Cell cell = (row != null) ? row.getCell(j) : null;
	                testData[a][b] = formatter.formatCellValue(cell);
	                System.out.print(testData[a][b] + " | ");
	            }
	            System.out.println();
	        }
	        return testData;
	    }
	}
}
