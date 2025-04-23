package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Common_Utility {
	public Properties getConfigProperties() throws IOException {
		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\User1\\eclipse-workspace\\Trythat.ai_Automation\\src\\test\\resources\\Configfiles\\config.properties");

		prop.load(fis);

		return prop;
	}

	public Properties getLocator() throws IOException {

		Properties loc = new Properties();

		FileReader fr1 = new FileReader(
				"C:\\Users\\User1\\eclipse-workspace\\Trythat.ai_Automation\\src\\test\\resources\\Configfiles\\locators.properties");

		loc.load(fr1);
		return loc;
	}

	public WebDriver initiateWebsite() throws IOException {
		WebDriver driver = null;
		Properties prop = null;
		Properties loc = null;
		ChromeOptions options = null;
		FirefoxOptions options1 = null;
		EdgeOptions options2 = null;
		if (driver == null) {
			prop = this.getConfigProperties();
			loc = this.getLocator();
		}
		String browser = prop.getProperty("browser");
		if (browser != null && browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("Webdriver.chrome.driver", "C:\\Test Chrome\\chromedriver-win64");
			options = new ChromeOptions();
			options.setBinary("C:\\Test Chrome\\chrome-win64\\chrome-win64\\chrome.exe");
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

	public WebElement wait(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return element;
	}

	public WebElement waitatelementToBeClickable(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

	public String handleAlert(WebDriver driver, int timeout, String alertXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(alertXpath)));
			return alertElement.getText();
		} catch (TimeoutException e) {
			System.out.println("Alert did not appear.");
			return null;
		} catch (NoAlertPresentException e) {
			System.out.println("Alert disappeared before we could interact with it.");
			return null;
		}

	}

	public WebDriver captureotp(WebDriver driver) throws IOException {
		try {
			String adbPath = "C:\\adb\\platform-tools-latest-windows\\platform-tools\\adb.exe";
			String command = adbPath
					+ " shell content query --uri content://sms/inbox --projection _id,address,body,date";
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			String otp = null;
			while ((line = reader.readLine()) != null) {
				System.out.println("SMS Content: " + line);
				String regex = "OTP\\s(\\d{6})";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					otp = matcher.group(1);
					System.out.println("Extracted OTP: " + otp);
					break;
				} else {
					System.out.println("OTP not found in the message.");
				}
			}
			if (otp != null && otp.length() == 6) {
				WebElement otpInputField1 = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
				WebElement otpInputField2 = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
				WebElement otpInputField3 = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
				WebElement otpInputField4 = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
				WebElement otpInputField5 = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
				WebElement otpInputField6 = driver.findElement(By.xpath("(//input[@type='text'])[6]"));
				otpInputField1.sendKeys(Character.toString(otp.charAt(0)));
				otpInputField2.sendKeys(Character.toString(otp.charAt(1)));
				otpInputField3.sendKeys(Character.toString(otp.charAt(2)));
				otpInputField4.sendKeys(Character.toString(otp.charAt(3)));
				otpInputField5.sendKeys(Character.toString(otp.charAt(4)));
				otpInputField6.sendKeys(Character.toString(otp.charAt(5)));
				System.out.println("OTP entered: " + otp);
				driver.findElement(By.xpath("//button[@type='button']")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public ExtentReports createExtentReports(String testCaseName) {
		String reportFolderPath = "C:\\Users\\User1\\eclipse-workspace\\Trythat.ai_Automation\\src\\test\\resources\\Reports\\";
		File reportFolder = new File(reportFolderPath);
		if (!reportFolder.exists()) {
			reportFolder.mkdirs();
		}
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String reportFilePath = reportFolderPath + testCaseName + "_" + "ExtentReport_" + timestamp + "_" + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFilePath);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
	
}
