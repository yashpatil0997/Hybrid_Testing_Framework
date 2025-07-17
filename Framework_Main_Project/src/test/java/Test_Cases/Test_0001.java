package Test_Cases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.Logs;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import BaseClass.Base_Test;
import Utilities.Common_Utility;
import Utilities.ExcelReader;
import Utilities.Screenshot;

public class Test_0001 extends Base_Test {
	@Test
	public void Validate_Pageload() throws IOException {
		try {
			Common_Utility commonUtility = new Common_Utility();
			Properties prop = commonUtility.getConfigProperties();
			Properties loc = commonUtility.getLocator();
			ExcelReader Excel = new ExcelReader();
			Screenshot scr = new Screenshot(driver);
			// All for the logs
			logInfo("Reading configuration and locators...");
			logInfo("Entering username in search bar...");
			// This for the Excel Sheet
			String[][] testdata = Excel.getExcelData("TestSheet", 0, 0);
			String username = testdata[0][0];
			driver.findElement(By.xpath(loc.getProperty("search_bar"))).sendKeys(username);
			// This for the Screen Shot
			scr.takeScreenshot("Screen_of_SearchPage");
			// This for the Report
			logPass("Page loaded Successfully");
			extent.flush();
		} catch (Exception e) {
			// All for the logs
			logFail("Login test failed: " + e.getMessage());
		}

	}
}
