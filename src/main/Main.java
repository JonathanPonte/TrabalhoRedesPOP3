package main;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintWriter;

import constants.Constants;
import enums.Commands;
import gui.GetAdressAndDoor;
import gui.GetEmailAndPassword;
import service.EmailService;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws IOException {

		EmailService.EmailServic("pop.gmail.com", 995);

		System.out.println("Menssagen bem bonitinha que deu certo: ");
		String response = EmailService.readOneLine();
		PrintWriter output = EmailService.getOutPut();

		// Username
		output.println(Commands.USER + " " + Constants.getUsername());
		response = EmailService.readOneLine();
		System.out.println(response);

		// Password
		output.println("PASS " + " " + Constants.getPassword());
		response = EmailService.readOneLine();
		System.out.println(response);

		System.out.println();

		String base64 = "";

		String name = "";
		
		output.println("RETR 4");
		response = EmailService.readOneLine();
		while (!response.equals(".")) {
			response = EmailService.readOneLine();

			if (response.contains("filename")) {
				String[] fileName = response.split("\"");

				name = fileName[1];

			}

		}
		
		System.out.println(name);
		
		// System.out.println(response);

//		output.println("RETR 2");
//        while (!response.equals(".")) {
//            response = EmailService.readOneLine();
//            System.out.println(response);
//        }

		// EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GetAdressAndDoor frame = new GetAdressAndDoor();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

	}

}
