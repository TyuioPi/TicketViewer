package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

public class APIConnect {

	private static String FILE_NAME = "auth.txt";
//	private static String URL_SINGLE = "https://tyuiop.zendesk.com/api/v2/tickets.json?query=1";
//	private static String URL_LIST = "https://tyuiop.zendesk.com/api/v2/tickets.json?per_page=25";
	
	public APIConnect() {}
	
	public void HttpRequestJSON(String apiUrl) {
		
		String authorization = getAuthorization();
		String encoding = Base64.getEncoder().encodeToString((authorization).getBytes());
		HttpURLConnection connection;
		
		try {
			URL url = new URL(apiUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			
			int statusCode = connection.getResponseCode();
			if (statusCode != HttpURLConnection.HTTP_OK) {
				System.out.println("Response Code: " + statusCode);
			} else {
				InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
				String line;
				StringBuffer response = new StringBuffer();
				while ((line = bufferedReader.readLine()) != null) {
					response.append(line);
		      	}
				inputStreamReader.close();
				bufferedReader.close();
				System.out.println(response);
			}
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL provided: " + apiUrl);
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
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
			System.out.println(FILE_NAME + " could not be found");
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
