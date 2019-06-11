package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

/* The Data class is designed to parse JSON information from
 * the API request to generate a list of tickets
 */
public class Data {
	
	// Initialize variables for parsing JSON and list creation
	private JSONObject JSONObject;
	private List<Ticket> ticketList;
	
	public Data(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	
	/* Parse JSON Object and create ticket objects 
	 * via information from API query for a single ticket 
	 */
	public void parseDataByIdQuery(StringBuffer response) {
		// Clear ticket list
		if (!ticketList.isEmpty()) {
			ticketList.removeAll(ticketList);
		}
		
		JSONObject = new JSONObject(response.toString());
		String nextPage = JSONObject.get("next_page").toString();
		String previousPage = JSONObject.get("previous_page").toString();
		
		JSONObject = JSONObject.getJSONArray("results").getJSONObject(0);
		String id = JSONObject.get("id").toString();
		String requesterId = JSONObject.get("requester_id").toString();
		String subject = JSONObject.get("subject").toString();
		
		String createdAt = JSONObject.get("created_at").toString();
		createdAt = convertDateTime(createdAt);
		
		String priority = JSONObject.get("priority").toString();
		String status = JSONObject.get("status").toString();
		String type = JSONObject.get("type").toString();
		
		ticketList.add(new Ticket(id, requesterId, subject, createdAt, 
				priority, status, type, nextPage, previousPage));
		
		// HANDLE EMPTY JSON
	}
	
	/* Parse JSON Object and create ticket objects 
	 * via information from API request for multiple tickets
	 */
	public void parseByPage(StringBuffer response) {
		// Clear ticket list
		if (!ticketList.isEmpty()) {
			ticketList.removeAll(ticketList);
		}
		
		JSONObject = new JSONObject(response.toString());
		String nextPage = JSONObject.get("next_page").toString();
		String previousPage = JSONObject.get("previous_page").toString();
		JSONArray JSONArray = JSONObject.getJSONArray("tickets");
		
		for (int length = 0; length < JSONArray.length(); length++) {
			String id = JSONArray.getJSONObject(length).get("id").toString();
			String requesterId = JSONArray.getJSONObject(length).get("requester_id").toString();
			String subject = JSONArray.getJSONObject(length).get("subject").toString();
			
			String createdAt = JSONArray.getJSONObject(length).get("created_at").toString();
			createdAt = convertDateTime(createdAt);
			
			String priority = JSONArray.getJSONObject(length).get("priority").toString();
			String status = JSONArray.getJSONObject(length).get("status").toString();
			String type = JSONArray.getJSONObject(length).get("type").toString();

			ticketList.add(new Ticket(id, requesterId, subject, createdAt, 
					priority, status, type, nextPage, previousPage));
		}
	}
	
	// Format date into readable text
	private String convertDateTime(String createdAt) {
		DateTimeFormatter input = 
				DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:SS'Z'", Locale.ENGLISH);
		
		DateTimeFormatter output = DateTimeFormatter.ofPattern("EEE dd MMMM yyyy", Locale.ENGLISH);
		String formatDate = null;
		try {
			LocalDate date = LocalDate.parse(createdAt, input);
			formatDate = output.format(date);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid Format for parsing DateTime");
			e.printStackTrace();
		}
		return formatDate;
	}
}