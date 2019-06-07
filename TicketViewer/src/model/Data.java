package model;

import org.json.JSONObject;

public class Data {
	private JSONObject JSONObject;
	
	public Data() {}
	
	public void setJSONObject(StringBuffer response) {
		JSONObject = new JSONObject(response.toString());
		System.out.println(JSONObject);
	}
	
	private void parseJSON() {
		
	}
}
