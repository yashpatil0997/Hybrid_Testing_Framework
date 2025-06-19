package Test_Cases;

import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import BaseClass.Base_Test;
import Utilities.Common_Utility;
import Utilities.Screenshot;

public class Test_0001 extends Base_Test {
	@Test
	public void login() throws IOException {
		Common_Utility commonUtility = new Common_Utility();
		Screenshot scr = new Screenshot(driver);
		scr.takeScreenshot("test");
		ExtentReports extent = commonUtility.createExtentReports("Test_report");
		ExtentTest test1 = extent.createTest("login via mobile", "This the test validation functionality");
		test1.pass("OTP successfully entered");
		extent.flush();
	}
}
