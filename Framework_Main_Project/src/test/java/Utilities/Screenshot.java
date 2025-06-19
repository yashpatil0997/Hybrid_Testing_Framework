package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Screenshot {
	private WebDriver driver;
	public Screenshot(WebDriver driver) {
		this.driver = driver;
	}
	public void takeScreenshot(String fileName) throws IOException {
		Common_Utility common_utility = new Common_Utility();
		Properties fis = common_utility.getConfigProperties();
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new Date());
		String screenshotDir = fis.getProperty("screenshot_path");
		try {
			Files.createDirectories(Paths.get(screenshotDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath = screenshotDir + File.separator + fileName + " " + timestamp + ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		try {
			Files.copy(srcFile.toPath(), destFile.toPath());
			System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Failed to save screenshot: " + e.getMessage());
		}
	}
}
