package BaseClass;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utilities.Common_Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test {
	public static WebDriver driver = null;
	protected ExtentReports extent;
	protected ExtentTest test;
	Common_Utility commonUtility = new Common_Utility();

	@BeforeTest
	public WebDriver setUp() throws IOException {
		Properties prop = null;
		ChromeOptions options = null;
		FirefoxOptions options1 = null;
		EdgeOptions options2 = null;
		if (driver == null) {
			prop = commonUtility.getConfigProperties();
		}
		String browser = prop.getProperty("browser");
		if (browser != null && browser.equalsIgnoreCase("Chrome")) {
			System.setProperty(prop.getProperty("chrome_driver"), prop.getProperty("chromedriver_path"));
			options = new ChromeOptions();
			options.setBinary(prop.getProperty("testchrome_path"));
			options.addArguments("--disable-notification");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
			Common_Utility.writeLog("Launched Chrome browser and navigated to: " + prop.getProperty("testurl"));
		} else if (browser != null && browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			options1 = new FirefoxOptions();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
			Common_Utility.writeLog("Launched Firefox browser and navigated to: " + prop.getProperty("testurl"));
		} else if (browser != null && browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			options2 = new EdgeOptions();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
			Common_Utility.writeLog("Launched Edge browser and navigated to: " + prop.getProperty("testurl"));
		}
		return driver;
	}

	@BeforeMethod
	public void setup(Method method) {
		String testName = method.getName();
		Common_Utility.initLogger(testName);
		Common_Utility.writeLog("===== Test Started: " + testName + " =====");

		extent = commonUtility.createExtentReports(testName);
		test = extent.createTest(testName);
	}

	@AfterMethod
	public void tearDownTest(Method method) {
		Common_Utility.writeLog("===== Test Ended: " + method.getName() + " =====");
		Common_Utility.stopLogger();

		if (extent != null) {
			extent.flush();
		}
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected void logInfo(String message) {
		Common_Utility.writeLog(message);
		test.info(message);
	}

	protected void logPass(String message) {
		Common_Utility.writeLog(message);
		test.pass(message);
	}

	protected void logFail(String message) {
		Common_Utility.writeLog(message);
		test.fail(message);
	}

}
