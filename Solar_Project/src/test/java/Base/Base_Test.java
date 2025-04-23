package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utilities.CommonUtility;
import Utilities.ExtentManager;

public class Base_Test {
	public static WebDriver driver = null;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeTest
	public void setUp() throws IOException {
		CommonUtility commonUtility = new CommonUtility();
		extent = ExtentManager.createInstance("test-output/ExtentReport.html");
		test = extent.createTest("Base Test Setup");
		driver = commonUtility.initiateWebsite();
		test.pass("Driver initialized and website launched.");
	}

//	@AfterTest
//	public void teardown() {
//		if (driver != null) {
//			driver.close();
//			test.pass("Browser closed.");
//		}
//	}

}
