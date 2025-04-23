package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base_Test;
import Functionality.Login;
import Utilities.CommonUtility;
import Utilities.ReadXLSdata;

public class Test2 extends Base_Test {

	@Test
	public void Uploadfile() throws IOException, InterruptedException, AWTException {
		CommonUtility commonUtility = new CommonUtility();
		Properties loc = commonUtility.getLocator();
		Properties conf = commonUtility.getConfigProperties();
		
		Login loginObj = new Login();
		loginObj.loginTest();
		Thread.sleep(4000);
		driver.findElement(By.xpath(loc.getProperty("leadmenu"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(loc.getProperty("FirstLead"))).click();
		Thread.sleep(4000);
		WebElement convertToDeal = driver.findElement(By.xpath(loc.getProperty("converttoDeal")));
		convertToDeal.click();
		Thread.sleep(2000);
		WebElement uploadButton = driver.findElement(By.xpath(loc.getProperty("lightBill")));
		uploadButton.click();
		Thread.sleep(2000);
		
//		String photoPath = "C:\\Users\\User1\\Pictures\\Screenshots\\Screenshot 2024-12-27 124146.png";
		Robot robot = new Robot();

		StringSelection fileSelection = new StringSelection("C:\\Users\\User1\\Pictures\\Screenshots\\Screenshot 2024-12-27 124146.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileSelection, null);

		robot.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		System.out.println("File uploaded successfully!");
	}



	private boolean verifyUploadSuccess(String filePath, String locator) {
		try {
			// Example: Verify a preview image or success message
			WebElement previewElement = driver.findElement(By.xpath("//img[contains(@src, 'ok.jpeg')]"));
			return previewElement.isDisplayed();
		} catch (Exception e) {
			System.out.println("Verification failed for locator: " + locator);
			return false;
		}
	}

}
