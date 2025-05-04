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

public class ScreenshotUtil {

	private WebDriver driver;
	public ScreenshotUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void takeScreenshot(String fileName) {
		
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm").format(new Date());

		String screenshotDir = "screenshots";
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
