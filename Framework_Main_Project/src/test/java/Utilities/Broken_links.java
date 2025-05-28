package Utilities;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Broken_links {
	public static void checkBrokenLinks(WebDriver driver) {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Checking for broken links on: " + driver.getCurrentUrl());
		System.out.println("Total links found: " + links.size());
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			if (url == null || (!url.startsWith("http") && !url.startsWith("https"))) {
				continue;
			}
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("HEAD");
				connection.connect();
				int statusCode = connection.getResponseCode();
				if (statusCode >= 400) {
					System.out.println("Broken Link: " + url + " | Status Code: " + statusCode);
				} else {
					System.out.println("Valid Link: " + url + " | Status Code: " + statusCode);
				}
			} catch (Exception e) {
				System.out.println("Error checking URL: " + url + " | Exception: " + e.getMessage());
			}
		}
	}
}
