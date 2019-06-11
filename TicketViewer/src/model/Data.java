package model;

import org.json.JSONObject;

public class Data {
	private JSONObject JSONObject;
	
	public Data() {}
	
	public void setJSONObject(StringBuffer response) {
		JSONObject = new JSONObject(response.toString());
	}
	
	private void parseByIdQuery() {
	}
	
	private void parseByPage() {
		
	}
}
