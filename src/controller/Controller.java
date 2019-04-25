package controller;

import java.io.IOException;

import service.EmailService;

public class Controller {

	public static void setAdressAndDoor(String adress, int door) throws IOException {
		EmailService.EmailServic(adress, door);
	}

	public static String response() throws IOException {
		return EmailService.readOneLine();

	}

	public static void login(String email, String password) {

	}

	public static void checkEmail(String email) {
		
		
		
	}

	public static void checkPassword(String password) {

	}

}
