package util.file.txt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import basic.Yield;

public class Reader {
	
	public static String readFile(String filename) {
		String line = "";

			String singleLine = "a";
			InputStream source = Yield.getMainClass().getClass().getResourceAsStream(filename);
			InputStream inputStream = new BufferedInputStream(source);
			try {
				 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((singleLine = reader.readLine()) != null) {
					if(!singleLine.equals("")) {
						line += singleLine;
						line += System.getProperty("line.separator");
					}

				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return line;
	}
	public static String readFile(String filename, String separator) {
		String line = "";

			String singleLine = "a";
			InputStream source = Yield.getMainClass().getClass().getResourceAsStream(filename);
			InputStream inputStream = new BufferedInputStream(source);
			try {
				 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((singleLine = reader.readLine()) != null) {
					if(!singleLine.equals("")) {
						line += singleLine;
						line += separator;
					}

				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return line;
	}
	
}
