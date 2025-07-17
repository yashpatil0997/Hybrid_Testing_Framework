package Test_Cases;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import BaseClass.Base_Test;
import Utilities.Common_Utility;
import Utilities.ExcelReader;
import Utilities.Screenshot;

public class Test_0002 extends Base_Test {

	@Test
	public void validate_Search() throws IOException {
		try {
			Thread.sleep(5000);
			Common_Utility commonUtility = new Common_Utility();
			Properties prop = commonUtility.getConfigProperties();
			Properties loc = commonUtility.getLocator();
			ExcelReader Excel = new ExcelReader();
			Screenshot scr = new Screenshot(driver);
			// All for the logs
			logInfo("Test 2 - Start");
			logInfo("Test 3 - Read Excel");
			//Read data from Excel
			String[][] testdata = Excel.getExcelData("TestSheet", 0, 0);
			String username = testdata[0][0];
			driver.findElement(By.xpath(loc.getProperty("search_bar"))).sendKeys(username);
			driver.findElement(By.xpath(loc.getProperty("search_button"))).click();
			//Screenshot
			scr.takeScreenshot("test");
			Thread.sleep(2000);
			logPass("Search value enter successfully");
			extent.flush();
		} catch (Exception e) {
			// All for the logs
			logFail("Login test failed: " + e.getMessage());
			e.printStackTrace();
		}	
	}
}
