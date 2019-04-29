package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Email;
import enums.Commands;
import service.EmailService;

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
		String subject = "";
		String body = "";
		String to = "";

		output.println("RETR " + i);
		while (!response.equals(".")) {
			response = response();

			if (response.contains("To: ")) {
				to = response;
				System.out.println(to);
			}

			

			if (response.contains("Date: ")) {
				date = response;
				System.out.println(date);
			}

			if (response.contains("Subject: ")) {
				subject = response;
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

		}

		return new Email(id, subject, body, from, to, null, date);

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
