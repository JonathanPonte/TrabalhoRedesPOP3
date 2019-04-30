package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Email;
import model.FilesDownload;
import enums.Commands;
import service.EmailService;
import utils.Util;

public class Controller {

	public static void setAdressAndDoor(String adress, int door) throws IOException {
		EmailService.EmailServic(adress, door);
	}

	public static String response() throws IOException {
		return EmailService.readOneLine();

	}

	public static boolean login(String email, String password) throws IOException {

		checkEmail(email);
		return checkPassword(password);

	}

	public static void checkEmail(String email) throws IOException {
		PrintWriter output = EmailService.getOutPut();
		output.println(Commands.USER + " " + email);
		System.out.println("email");
		System.out.println(response());

	}

	public static boolean checkPassword(String password) throws IOException {

		PrintWriter output = EmailService.getOutPut();
		output.println(Commands.PASS + " " + password);
		System.out.println("password");
		String a = response();
		System.out.println(a);
		if (a == "-ERR [AUTH] Username and password not accepted.") {
			return false;
		} else {
			return true;
		}

	}

	public static List<Email> ListEmail() throws IOException {

		List<Email> emails = new ArrayList<Email>();

		int size = getNumberOfEmail();

		for (int i = 1; i <= size; i++) {
			emails.add(getEmail(i));
		}

		return emails;
	}

	public static Email getEmail(int i) throws IOException {
		PrintWriter output = EmailService.getOutPut();

		boolean isAllowed = true;
		int id = i;
		String response = "";
		String from = "";
		String date = "";
		String time = "";
		String subject = "";
		String body = "";
		String to = "";
		String base64 = "";
		String fileName = "";
		ArrayList<FilesDownload> filesDownloads = new ArrayList<FilesDownload>();
		
		FilesDownload fd = null;
		
		
		

		output.println("RETR " + i);
		while (!response.equals(".")) {
			response = response();

			if (response.contains("To: ")) {
				to = response.split(" ")[1];
				System.out.println(to);
			}

			if (response.contains("From: ")) {
				from = Util.fetchEmailFrom(response);
				System.out.println(from);
			}

			if (response.contains("Date: ")) {
				String[] dateTime = response.split(" ");
				date = date + dateTime[1] + " " + dateTime[2] + " " + dateTime[3] + " " + dateTime[4];
				System.out.println(date);
				time = time + dateTime[5];
			}

			if (response.contains("Subject: ")) {
				subject = Util.fetchSubject(response);
				System.out.println(subject);
			}

			if (response.contains("UTF-8") && isAllowed) {

				isAllowed = false;

				while (!response.contains("--")) {
					response = EmailService.readOneLine();

					if (!response.contains("--")) {
						body += response + " ";
					}

				}
				System.out.println(body);
			}
			
			if (response.contains("filename")) {
				String[] filename = response.split("\"");

				fileName = filename[1];
				fd = new FilesDownload();
				
				fd.setFileNames(fileName);

			}

			if (response.contains("X-Attachment-Id")) {

				while (!response.contains("--")) {

					response = EmailService.readOneLine();

					if (!response.contains("--")) {
						base64 += response;
					}

				}
				
				
				
				fd.setBases64(base64);
				base64 = "";
				
				filesDownloads.add(fd);
				
			//	bases64.add(base64);
				
			}

		}
		
		

		return new Email(id, subject, body, from, to, base64, date, time, fileName, filesDownloads);

	}

	public static int getNumberOfEmail() throws IOException {
		PrintWriter output = EmailService.getOutPut();
		output.println("LIST");
		String response = response();

		System.out.println(response);

		int numberofemails = Integer.parseInt(response.split(" ")[1]);

		while (!response.equals(".")) {
			response = EmailService.readOneLine();
			System.out.println(response);
		}

		return numberofemails;
	}

}
