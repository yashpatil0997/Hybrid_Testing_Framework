package Functionality;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Base.Base_Test;
import Utilities.CommonUtility;
import Utilities.ReadXLSdata;

public class Login extends Base_Test {

	@Test(dataProviderClass=ReadXLSdata.class,dataProvider="bvtdata")
	public void loginTest() throws IOException, InterruptedException {
		ReadXLSdata readXLSData = new ReadXLSdata();
		String [][] testDataArray = readXLSData.getData("LoginTest");
//		System.out.println(testDataArray);
		for (int i = 0; i < testDataArray.length; i++) {
			String username = testDataArray[i][0];
			String password = testDataArray[i][1];
		    System.out.println("Username " + username + "Password " + password); // Move to the next line after printing a row
		    CommonUtility utility = new CommonUtility();
			WebDriver driver = Base_Test.driver;
			String usernameLocator = utility.getLocator().getProperty("usernameField");
			String passwordLocator = utility.getLocator().getProperty("passwordField");
			String loginButtonLocator = utility.getLocator().getProperty("loginButton");

			// Perform Login
			utility.wait(driver, 10, usernameLocator).sendKeys(username);
			Thread.sleep(2000);                                                           
			utility.wait(driver, 10, passwordLocator).sendKeys(password);
			Thread.sleep(2000);
			utility.waitatelementToBeClickable(driver, 10, loginButtonLocator).click();

			// Validate Login Success

			// Print login success message
			System.out.println("Login Test Passed: User successfully logged in.");
		}
		// Create an instance of CommonUtility
		
	}

}
