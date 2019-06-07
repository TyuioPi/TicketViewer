package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class APIConnect {
	
	private static String FILE_NAME = "auth.txt";
	
	public APIConnect() {
		
	}
	
	private String getAuthorization() {
		
		String authorization = null;
		String user = null;
		String password = null;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
			// Append content to string
			String line = null;
			
			while((line = bufferedReader.readLine()) != null) {
				if (user == null) {
					user = line;
				} else if (password == null) {
					password = line;
				}
			}
			
			// Close streams and readers
			fileInputStream.close();
			inputStreamReader.close();
			bufferedReader.close();
			
			// Handle any FileNotFoundException
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NAME + "could not be found");
			e.printStackTrace();
			// Handle any IOExceptions
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Format authorization
		authorization = user + ":" + password;
		return authorization;
	}
}
