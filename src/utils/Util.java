package utils;

public class Util {
	public static String fetchEmailFrom(String responseRow) {
		return responseRow.substring(responseRow.indexOf("<") + 1, responseRow.indexOf(">"));
	}
	
	public static String fetchSubject(String responseRow) {
		return responseRow.substring(responseRow.indexOf(":") + 1, responseRow.length());
	}
}
