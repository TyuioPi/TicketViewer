package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Base64;

/* The APIConnect class is designed to handle connecting to the API
 * by generating the appropriate URL to retrieve information
 * in JSON format
 */
public class APIConnect {

	private static String URL_SINGLE = "https://tyuiop.zendesk.com/api/v2/search.json?query=";
	private static String URL_LIST = "https://tyuiop.zendesk.com/api/v2/tickets.json?per_page=25";
	private String authorization;
	private StringBuffer response;
	
	public APIConnect(String authorization) {
		this.authorization = authorization;
	}
	
	// Connect to the API and retrieve ticket data
	public StringBuffer HttpRequestJSON(String apiUrl) {
	
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
			
			// Verify connection response code and retrieve data
			int statusCode = connection.getResponseCode();
			if (statusCode != HttpURLConnection.HTTP_OK) {
				System.out.println("Response Code: " + statusCode);
			} else {
				InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
				String line;
				response = new StringBuffer();
				while ((line = bufferedReader.readLine()) != null) {
					response.append(line);
		      	}
				inputStreamReader.close();
				bufferedReader.close();
			}
		} catch (SocketException e) {
			System.out.println("Network could not be found");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.out.println("Unable to connect to host");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL provided: " + apiUrl);
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return response;
	}
	
	// Generate URL for single ticket query by ID
	public String generateURLQueryById(String ticketId) {
		String url = URL_SINGLE + ticketId;
		return url;
	}
	
	// Generate initial URL for viewing ticket list
	public String generateURLQueryByList() {
		String url = URL_LIST;
		return url;
	}
}
