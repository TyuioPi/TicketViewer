package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import model.APIConnect;
import model.CredentialsReader;

class APIConnectTest {
	
	private CredentialsReader credentials = new CredentialsReader();
	private String authorization = credentials.getAuthorization();
	private APIConnect apiConnect = new APIConnect(authorization);
	
	private final String TEST_URL_SINGLE = "https://tyuiop.zendesk.com/api/v2/search.json?query=";
	private final String TEST_URL_LIST = "https://tyuiop.zendesk.com/api/v2/tickets.json?per_page=25";

	@Test
	void generateURLQueryByIdTest() {
		String[] testIdQuery = {"1", "2", "3", "aasd", "!"};
		
		for (int length = 0; length < testIdQuery.length; length++) {
			String testURL = apiConnect.generateURLQueryById(testIdQuery[length]);
			assertEquals(TEST_URL_SINGLE + testIdQuery[length], testURL);
		}
	}
	
	@Test
	void generateURLQueryByListTest() {
		String testURL = apiConnect.generateURLQueryByList();
		assertEquals(TEST_URL_LIST, testURL);
	}
	
	@Test
	void HTTPConnectionTest() {
		
		String[] idQuery = {"1", "2", "3", "aasd", "!"};
		
		for (int length = 0; length < idQuery.length; length++) {
			String single = TEST_URL_SINGLE + idQuery[length];
			StringBuffer response = apiConnect.HttpRequestJSON(single);
			assertNotNull(response);
		}
		
	}

}
