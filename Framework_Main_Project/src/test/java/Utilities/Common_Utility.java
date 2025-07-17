package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
	public static Properties prop;
	public static Properties loc;
	private static BufferedWriter writer;

	public Properties getConfigProperties() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\User1\\eclipse-workspace\\Framework_Main_Project\\src\\test\\resources\\Configfiles\\config.properties");
		prop.load(fis);
		return prop;
	}

	public Properties getLocator() throws IOException {
		loc = new Properties();
		FileReader fr1 = new FileReader(
				"C:\\Users\\User1\\eclipse-workspace\\Framework_Main_Project\\src\\test\\resources\\Configfiles\\locators.properties");
		loc.load(fr1);
		return loc;
	}

	// Wait for visibility of element
	public WebElement waitForElementVisible(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return element;
	}

	// Wait for element to be clickable
	public WebElement waitatelementToBeClickable(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

	// Wait for presence of element in DOM
	public WebElement waitForElementPresent(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	// Wait for a specific title
	public boolean waitForTitleContains(WebDriver driver, int time, String partialTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.titleContains(partialTitle));
	}

	// Wait for element to disappear
	public boolean waitForElementInvisible(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
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
			String adbPath = prop.getProperty("abd_path");
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
				WebElement otpInputField1 = driver.findElement(By.xpath(loc.getProperty("otp_InputField1")));
				WebElement otpInputField2 = driver.findElement(By.xpath(loc.getProperty("otp_InputField2")));
				WebElement otpInputField3 = driver.findElement(By.xpath(loc.getProperty("otp_InputField3")));
				WebElement otpInputField4 = driver.findElement(By.xpath(loc.getProperty("otp_InputField4")));
				WebElement otpInputField5 = driver.findElement(By.xpath(loc.getProperty("otp_InputField5")));
				WebElement otpInputField6 = driver.findElement(By.xpath(loc.getProperty("otp_InputField6")));
				otpInputField1.sendKeys(Character.toString(otp.charAt(0)));
				otpInputField2.sendKeys(Character.toString(otp.charAt(1)));
				otpInputField3.sendKeys(Character.toString(otp.charAt(2)));
				otpInputField4.sendKeys(Character.toString(otp.charAt(3)));
				otpInputField5.sendKeys(Character.toString(otp.charAt(4)));
				otpInputField6.sendKeys(Character.toString(otp.charAt(5)));
				System.out.println("OTP entered: " + otp);
				driver.findElement(By.xpath(loc.getProperty("submit_button"))).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public ExtentReports createExtentReports(String testCaseName) {
		try {
			String reportFolderPath = prop.getProperty("extentsreportsfolder_path");
			File reportFolder = new File(reportFolderPath);
			if (!reportFolder.exists()) {
				reportFolder.mkdirs();
			}
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportFileName = testCaseName + "_ExtentReport_" + timestamp + ".html";
			String reportFilePath = reportFolderPath + File.separator + reportFileName;
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFilePath);
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("Test Report - " + testCaseName);
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			return extent;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void initLogger(String testCaseName) {
		try {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File logDir = new File("src/test/resources/logs");
			if (!logDir.exists()) {
				logDir.mkdirs();
			}
			File logFile = new File(logDir, testCaseName + "_ExecutionLog_" + timestamp + ".log");
			writer = new BufferedWriter(new FileWriter(logFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeLog(String message) {
		try {
			if (writer != null) {
				String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
				writer.write("[" + time + "] " + message);
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void stopLogger() {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
