package TestCases;

import javax.mail.*;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMultipart;

import java.util.Properties;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class test {

	public static String fetchOTPFromGmail(String email, String password) {

		String otp = null;

		try {

			// Gmail IMAP settings

			Properties props = new Properties();

			props.put("mail.store.protocol", "imaps");

			props.put("mail.imap.host", "imap.gmail.com");

			props.put("mail.imap.port", "993");

			// Set the system property to enforce TLS 1.2

			System.setProperty("https.protocols", "TLSv1.2");

			// Connect to Gmail

			Session session = Session.getDefaultInstance(props, null);

			Store store = session.getStore("imaps");

			store.connect("imap.gmail.com", email, password);

			// Open the Inbox folder

			Folder inbox = store.getFolder("INBOX");

			inbox.open(Folder.READ_ONLY);

			// Get the latest email

			Message[] messages = inbox.getMessages();

			if (messages.length == 0) {

				System.out.println("No emails found.");

				return null;

			}

			Message latestMessage = messages[messages.length - 1];

			// Get email subject and content

			String subject = latestMessage.getSubject();

			System.out.println("Subject: " + subject);

			String content = getTextFromMessage(latestMessage);

			System.out.println("Content: " + content);

			// Extract OTP from the content

			Pattern otpPattern = Pattern.compile("\\d{4}"); // Regex for a 4-digit OTP

			Matcher matcher = otpPattern.matcher(content);

			if (matcher.find()) {

				otp = matcher.group();

			}

			System.out.println("Extracted OTP: " + otp);

			// Close the connections

			inbox.close(false);

			store.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return otp;

	}

	private static String getTextFromMessage(Message message) throws Exception {

		if (message.isMimeType("text/plain")) {

			return message.getContent().toString();

		} else if (message.isMimeType("multipart/*")) {

			Multipart multipart = (Multipart) message.getContent();

			for (int i = 0; i < multipart.getCount(); i++) {

				BodyPart bodyPart = multipart.getBodyPart(i);

				if (bodyPart.isMimeType("text/plain")) {

					return bodyPart.getContent().toString();

				}

			}

		}

		return null;

	}

	public static void main(String[] args) {

		// Replace with your Gmail credentials

		String email = "your-email@gmail.com";

		String password = "your-password";

		// Fetch OTP

		String otp = fetchOTPFromGmail(email, password);

		if (otp != null) {

			System.out.println("OTP fetched: " + otp);

		} else {

			System.out.println("No OTP found.");

		}

	}

}
