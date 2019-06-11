package model;

import java.util.List;

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
		JSONObject = new JSONObject(response.toString());
		String next_page = JSONObject.get("next_page").toString();
		
		JSONObject = JSONObject.getJSONArray("results").getJSONObject(0);
		String id = JSONObject.get("id").toString();
		String requesterId = JSONObject.get("requester_id").toString();
		String subject = JSONObject.get("subject").toString();
		String created_at = JSONObject.get("created_at").toString();
		String priority = JSONObject.get("priority").toString();
		String status = JSONObject.get("status").toString();
		String type = JSONObject.get("type").toString();
		
		ticketList.add(new Ticket(id, requesterId, subject, created_at, 
				                  priority, status, type, next_page));
		
		// HANDLE EMPTY JSON
	}
	
	/* Parse JSON Object and create ticket objects 
	 * via information from API request for multiple tickets
	 */
	public void parseByPage(StringBuffer response) {
		
	}
}
