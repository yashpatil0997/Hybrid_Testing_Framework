package TestCases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Base.Base_Test;
import Functionality.Login;
import Utilities.CommonUtility;

public class CreateLead extends Base_Test {
	@Test
	public void testCreateLead() throws IOException, InterruptedException {
		CommonUtility commonUtility = new CommonUtility();
		Properties loc = commonUtility.getLocator();
		Properties conf = commonUtility.getConfigProperties();
		Login loginObj = new Login();
		loginObj.loginTest();
		Thread.sleep(3000);
		// lead menu
		driver.findElement(By.xpath(loc.getProperty("leadmenu"))).click();
		Thread.sleep(2000);
		// Create Lead
		driver.findElement(By.xpath(loc.getProperty("createlead"))).click();
		System.out.println("Succesfully Click on + button for creating a new lead");
		Thread.sleep(2000);
		// Handle the "Source Type" dropdown
		WebElement sourceTypeDropdown = driver.findElement(By.xpath(loc.getProperty("sourcetypedropdown")));
		sourceTypeDropdown.click();
		Thread.sleep(2000);
		// Select "Database" from the dropdown options
		WebElement directoption = driver.findElement(By.xpath(loc.getProperty("sourcetypevalue")));
		directoption.click();
		Thread.sleep(2000);
		// Enter mobile number in the Mobile Number field
		WebElement mobileNumberField = driver.findElement(By.xpath(loc.getProperty("mobilenofield")));
		mobileNumberField.sendKeys(conf.getProperty("mobile"));
		Thread.sleep(2000);
		// customer name field
		WebElement CustomerNameField = driver.findElement(By.xpath(loc.getProperty("customernamefield")));
		CustomerNameField.sendKeys(conf.getProperty("customername"));
		Thread.sleep(2000);
		// Email Id field
		WebElement CustomerEmailIdField = driver.findElement(By.xpath(loc.getProperty("customeremailidfield")));
		CustomerEmailIdField.sendKeys(conf.getProperty("customeremail"));
		Thread.sleep(2000);
		// address flat no
		WebElement FlatNoField = driver.findElement(By.xpath(loc.getProperty("Flatnofield")));
		FlatNoField.sendKeys(conf.getProperty("Flatno"));
		Thread.sleep(2000);
		// Area field
		WebElement AreaField = driver.findElement(By.xpath(loc.getProperty("Arealandmarkfield")));
		AreaField.sendKeys(conf.getProperty("Area"));
		Thread.sleep(3000);
		// State Dropdown
		WebElement stateDropdown = driver.findElement(By.xpath(loc.getProperty("statedropdown")));
		stateDropdown.click();
		Thread.sleep(1000);
		// Use arrow down key to navigate through the dropdown options
		for (int i = 0; i < 21; i++) { // Adjust this number to scroll through the list
			stateDropdown.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting "Maharashtra" by pressing ENTER
		stateDropdown.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		// District dropdown
		WebElement distDropdown = driver.findElement(By.xpath(loc.getProperty("districtdropdown")));
		distDropdown.click();
		Thread.sleep(2000);

		// Use arrow down key to navigate through the dropdown options
		for (int i = 0; i < 25; i++) { // Adjust this number to scroll through the list
			distDropdown.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting "Pune" by pressing ENTER
		distDropdown.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//cityfield
		WebElement cityField = driver.findElement(By.xpath(loc.getProperty("cityfield")));
		cityField.sendKeys(conf.getProperty("City"));
		Thread.sleep(1000);
		
		//pincodefield
		WebElement pincodeField = driver.findElement(By.xpath(loc.getProperty("pincodefield")));
		pincodeField.sendKeys(conf.getProperty("Pincode"));
		Thread.sleep(2000);
		
		//project type radio button
		driver.findElement(By.xpath(loc.getProperty("projettype"))).click();
		
		//service type dropdown
		WebElement serviceType = driver.findElement(By.xpath(loc.getProperty("servicetypedropdown")));
		serviceType.click();
		Thread.sleep(1000);
		for (int i = 0; i < 1; i++) { // Adjust this number to scroll through the list
			serviceType.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting "Pune" by pressing ENTER
		serviceType.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//discom dropdown
		WebElement discomDropdown = driver.findElement(By.xpath(loc.getProperty("discomdropdown")));
		discomDropdown.click();
		Thread.sleep(1000);
		for (int i = 0; i < 1; i++) { // Adjust this number to scroll through the list
			discomDropdown.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting by pressing ENTER
		discomDropdown.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//consumer no
		WebElement consumerNo = driver.findElement(By.xpath(loc.getProperty("consumernoField")));
		consumerNo.sendKeys(conf.getProperty("consumerNo"));
		
		//Structure type
		WebElement structuretypedropdown = driver.findElement(By.xpath(loc.getProperty("structtypeDropdown")));
		structuretypedropdown.click();
		Thread.sleep(1000);
		for (int i = 0; i < 0; i++) { // Adjust this number to scroll through the list
			structuretypedropdown.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		structuretypedropdown.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Cable Brand
		WebElement cablebrandDropdown = driver.findElement(By.xpath(loc.getProperty("cablebranddropdown")));
		cablebrandDropdown.click();
		Thread.sleep(1000);
		for (int i = 0; i < 0; i++) { // Adjust this number to scroll through the list
			cablebrandDropdown.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		cablebrandDropdown.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Panel Brand
		WebElement panelbrandDropdown = driver.findElement(By.xpath(loc.getProperty("panelbranddropdown")));
		panelbrandDropdown.click();
		Thread.sleep(1000);
		for (int i = 0; i < 0; i++) { // Adjust this number to scroll through the list
			panelbrandDropdown.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		panelbrandDropdown.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Panel Technology
		WebElement paneltechnologydd = driver.findElement(By.xpath(loc.getProperty("paneltechnologydropdown")));
		paneltechnologydd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 1; i++) { // Adjust this number to scroll through the list
			paneltechnologydd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		paneltechnologydd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Peak Watt
		WebElement peakwattdd = driver.findElement(By.xpath(loc.getProperty("paneltypepeakwattdropdown")));
		peakwattdd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 1; i++) { // Adjust this number to scroll through the list
			peakwattdd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		peakwattdd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Panel Quantity
		WebElement panelqtydd = driver.findElement(By.xpath(loc.getProperty("panelquantitydropdown")));
		panelqtydd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 6; i++) { // Adjust this number to scroll through the list
			panelqtydd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		panelqtydd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Inverter Brand
		WebElement inverterbranddd = driver.findElement(By.xpath(loc.getProperty("inverterbranddropdown")));
		inverterbranddd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 0; i++) { // Adjust this number to scroll through the list
			inverterbranddd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		inverterbranddd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Inverter Type
		WebElement invertertypedd = driver.findElement(By.xpath(loc.getProperty("invertertypedropdown")));
		invertertypedd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 2; i++) { // Adjust this number to scroll through the list
			invertertypedd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		invertertypedd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Inverter Capacity
		WebElement invertercapdd = driver.findElement(By.xpath(loc.getProperty("invertercapacitydropdown")));
		invertercapdd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 2; i++) { // Adjust this number to scroll through the list
			invertercapdd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		invertercapdd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Inverter Phase
		WebElement inverterphasedd = driver.findElement(By.xpath(loc.getProperty("inverterphasedropdown")));
		inverterphasedd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 1; i++) { // Adjust this number to scroll through the list
			inverterphasedd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		inverterphasedd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Lead Status
		WebElement leadstatusdd = driver.findElement(By.xpath(loc.getProperty("leadstatusdropdown")));
		leadstatusdd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 2; i++) { // Adjust this number to scroll through the list
			leadstatusdd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		leadstatusdd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Assign lead
		WebElement leadassigneedd = driver.findElement(By.xpath(loc.getProperty("assigneedropdown")));
		leadassigneedd.click();
		Thread.sleep(1000);
		for (int i = 0; i < 5; i++) { // Adjust this number to scroll through the list
			leadassigneedd.sendKeys(Keys.ARROW_DOWN); // Simulate pressing "Arrow Down"
			Thread.sleep(500); // Add a small delay between each key press
		}

		// After scrolling, try selecting  by pressing ENTER
		leadassigneedd.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//save and create quotation
		WebElement crtquotation = driver.findElement(By.xpath(loc.getProperty("savecreateLead")));
		crtquotation.click();
		Thread.sleep(10000);
		
		//quotation pages
		WebElement quotation6page = driver.findElement(By.xpath(loc.getProperty("quotation6page")));
		quotation6page.click();
		Thread.sleep(5000);
		
		//publish quotation
		WebElement publishquotation = driver.findElement(By.xpath(loc.getProperty("publishTemplate")));
		publishquotation.click();
		Thread.sleep(10000);

	}

}
