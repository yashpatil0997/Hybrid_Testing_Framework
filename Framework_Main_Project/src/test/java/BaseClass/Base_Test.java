package BaseClass;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import Utilities.Common_Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test {
	public static WebDriver driver = null;

	@BeforeTest
	public WebDriver setUp() throws IOException {
		Common_Utility commonUtility = new Common_Utility();
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
		} else if (browser != null && browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			options1 = new FirefoxOptions();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		} else if (browser != null && browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			options2 = new EdgeOptions();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		}
		return driver;
	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.close();
		}
	}

}
