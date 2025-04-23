package BaseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Utilities.Common_Utility;

public class Base_Test {
	public static WebDriver driver = null;

	@BeforeTest
	public void setUp() throws IOException {
		Common_Utility commonUtility = new Common_Utility();
		driver =  commonUtility.initiateWebsite();
	}	
	
	@AfterTest
    public void teardown() {
		if (driver != null) {
    	driver.close();
		}
    }	
	
}
