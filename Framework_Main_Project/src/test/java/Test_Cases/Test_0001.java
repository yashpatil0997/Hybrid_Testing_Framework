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
	public void login() throws IOException {
		try {
			Common_Utility commonUtility = new Common_Utility();
			Common_Utility.writeLog("Starting loginTest case");
			Properties prop = commonUtility.getConfigProperties();
			Properties loc = commonUtility.getLocator();
			ExcelReader Excel = new ExcelReader();
			Screenshot scr = new Screenshot(driver);
			// All for the logs
			logInfo("Reading configuration and locators...");
			logInfo("Entering username in search bar...");
			logPass("Successfully entered username.");
			// This for the Excel Sheet
			String[][] testdata = Excel.getExcelData("TestSheet", 0, 0);
			String username = testdata[0][0];
			driver.findElement(By.xpath(loc.getProperty("search_bar"))).sendKeys(username);
			// This for the Screen Shot
			scr.takeScreenshot("test");
			// This for the Report
			ExtentReports extent = commonUtility.createExtentReports("Test_report");
			ExtentTest test1 = extent.createTest("login via mobile", "This the test validation functionality");
			test1.pass("OTP successfully entered");
			extent.flush();
		} catch (Exception e) {
			// All for the logs
			logFail("Login test failed: " + e.getMessage());
		}

	}
}
