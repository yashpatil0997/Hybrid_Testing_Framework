package TestCases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base_Test;
import Functionality.Login;
import Utilities.CommonUtility;

public class ConverttoDeal extends Base_Test {
	@Test
	public void leadtoDeal() throws IOException, InterruptedException {
		CommonUtility commonUtility = new CommonUtility();
		Properties loc = commonUtility.getLocator();
		Properties conf = commonUtility.getConfigProperties();
		// Login loginObj = new Login();
		// loginObj.loginTest();
		Thread.sleep(5000);
		CreateLead crtlead = new CreateLead();
		crtlead.testCreateLead();
		Thread.sleep(2000);
		// lead menu
		// driver.findElement(By.xpath(loc.getProperty("leadmenu"))).click();
		// Thread.sleep(2000);
		WebElement quotation = driver.findElement(By.xpath(loc.getProperty("quotationtab")));
		quotation.click();
		Thread.sleep(1000);
		// status
		// Locate the dropdown/search field
		WebElement statusDropdown = driver.findElement(By.xpath(loc.getProperty("quotStatus")));
		// statusDropdown.click(); // Open the dropdown
		// Thread.sleep(1000);

		// Enter "Finalised" in the search field and select it
		String valueToSearch = "Finalised"; // Desired value
		statusDropdown.sendKeys(valueToSearch); // Type over the existing value
		Thread.sleep(500); // Wait briefly to allow dropdown options to populate
		statusDropdown.sendKeys(Keys.ENTER); // Confirm the selection
		Thread.sleep(8000);
		// Conver to deal button
		WebElement convertToDeal = driver.findElement(By.xpath(loc.getProperty("converttoDeal")));
		convertToDeal.click();
		Thread.sleep(3000);

		// File paths to upload
		String[] photoPaths = { "C:\\Users\\User\\Downloads\\ok.jpeg", "C:\\Users\\User\\Downloads\\ok.jpeg",
				"C:\\Users\\User\\Downloads\\ok.jpeg", "C:\\Users\\User\\Downloads\\ok.jpeg",
				"C:\\Users\\User\\Downloads\\ok.jpeg", "C:\\Users\\User\\Downloads\\ok.jpeg",
				"C:\\Users\\User\\Downloads\\ok.jpeg" };

		// Locators for file input fields
		String[] fileInputLocators = { loc.getProperty("lightBill"), loc.getProperty("taxBill"),
				loc.getProperty("aadharCard"), loc.getProperty("panCard"), loc.getProperty("cancelCheque"),
				loc.getProperty("signature"), loc.getProperty("photo") };

		// JavaScript Executor for handling hidden elements
		JavascriptExecutor js = (JavascriptExecutor) driver;	

		for (int i = 0; i < photoPaths.length; i++) {
			WebElement fileInput = driver.findElement(By.xpath(fileInputLocators[i]));

			try {
				// Use JavaScript to ensure the element is visible
				js.executeScript("arguments[0].style.display = 'block';", fileInput);

				// Upload the photo
				fileInput.sendKeys(photoPaths[i]);

				// Verify the photo upload (e.g., look for a success message or preview)
				boolean isUploaded = verifyUploadSuccess(photoPaths[i], fileInputLocators[i]);
				Assert.assertTrue(isUploaded, "Photo upload failed for: " + fileInputLocators[i]);

			} catch (Exception e) {
				System.out.println("Error uploading file for locator: " + fileInputLocators[i]);
				e.printStackTrace();
			}

			Thread.sleep(1000); // Optional: Wait between uploads
		}

		System.out.println("All photos uploaded successfully.");
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
