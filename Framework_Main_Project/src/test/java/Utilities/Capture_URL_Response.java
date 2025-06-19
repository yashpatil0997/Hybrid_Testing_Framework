package Utilities;

import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.network.model.RequestId;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.Response;
import org.testng.Assert;

public class Capture_URL_Response {
	public void captureAndValidateApiResponse(ChromeDriver driver, String apiURL) {
		DevTools devtools = driver.getDevTools();
		devtools.createSession();
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		final RequestId[] requestID = new RequestId[1];
		devtools.addListener(Network.requestWillBeSent(), requestConsumer -> {
			System.out.println("Request URL: " + requestConsumer.getRequest().getUrl());
		});
		devtools.addListener(Network.responseReceived(), responseConsumer -> {
			Response response = responseConsumer.getResponse();
			requestID[0] = responseConsumer.getRequestId();
			if (response.getUrl().contains(apiURL)) {
				Assert.assertEquals(response.getStatus(), 200, "Unexpected response status!");
				try {
					String responseBody = devtools.send(Network.getResponseBody(requestID[0])).getBody();
					System.out.println("Response Body:\n" + responseBody);
					JSONObject jsonResponse = new JSONObject(responseBody);
					JSONArray responseData = jsonResponse.getJSONObject("response").getJSONObject("data")
							.getJSONArray("data");
					System.out.println("Parsed Data: " + responseData.toString());
				} catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Error parsing response body.");
				}
			}
		});
	}
}
