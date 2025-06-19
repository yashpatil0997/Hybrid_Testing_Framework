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
	public String[][] getData(String excelSheetName, int startRow, int startCol)
			throws EncryptedDocumentException, IOException {
		Common_Utility common_utility = new Common_Utility();
		Properties prop = common_utility.getConfigProperties();
		File file = new File(prop.getProperty("excelsheet_path"));
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(excelSheetName);
		int totalRows = sheet.getLastRowNum();
		Row header = sheet.getRow(0);
		int totalCols = header.getLastCellNum();
		int effectiveRows = totalRows - startRow + 1;
		int effectiveCols = totalCols - startCol;
		String[][] testData = new String[effectiveRows][effectiveCols];
		DataFormatter formatter = new DataFormatter();
		for (int i = startRow, a = 0; i <= totalRows; i++, a++) {
			Row row = sheet.getRow(i);
			for (int j = startCol, b = 0; j < totalCols; j++, b++) {
				Cell cell = row.getCell(j);
				testData[a][b] = formatter.formatCellValue(cell);
				System.out.print(testData[a][b] + " | ");
			}
			System.out.println();
		}
		wb.close();
		fis.close();
		return testData;
	}
}
