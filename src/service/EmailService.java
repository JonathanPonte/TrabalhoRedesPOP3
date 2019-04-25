package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import constants.Constants;

@SuppressWarnings("unused")
public class EmailService {

	private static SSLSocket socket;
	private static BufferedReader input;
	private static PrintWriter output;

	public static void EmailServic(String adress, int door) throws UnknownHostException, IOException {

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		socket = (SSLSocket) sslsocketfactory.createSocket(adress, door);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
	}

	public static String readOneLine() throws IOException {
		return input.readLine();
	}

	public static PrintWriter getOutPut() {
		return output;
	}

}
