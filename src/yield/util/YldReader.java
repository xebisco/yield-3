package yield.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class YldReader {
	
	public static YldReader reader;

	public static String readFile(String filename) throws IOException {
		String line = "";

		String singleLine = "yieldREADER";
		InputStream source = reader.getClass().getResourceAsStream(filename);
		InputStream inputStream = new BufferedInputStream(source);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		while ((singleLine = reader.readLine()) != null) {
			if (!singleLine.equals("")) {
				line += singleLine;
				line += System.getProperty("line.separator");
			}

		}

		return line;
	}

	public static String readFile(String filename, String separator) throws IOException {
		String line = "";

		String singleLine = "yieldREADER";
		InputStream source = reader.getClass().getResourceAsStream(filename);
		InputStream inputStream = new BufferedInputStream(source);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		while ((singleLine = reader.readLine()) != null) {
			if (!singleLine.equals("")) {
				line += singleLine;
				line += separator;
			}

		}
		return line;
	}

}
