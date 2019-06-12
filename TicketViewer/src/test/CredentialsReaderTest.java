package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import model.CredentialsReader;

class CredentialsReaderTest {

	CredentialsReader credentialsReader = new CredentialsReader();

	// Test authorization format from auth.txt
	@Test
	void authorizationFormatTest() {
		String authorization = credentialsReader.getAuthorization();
		
		String regex = "\\A[A-Za-z0-9+_.-]+@(.+):{1}[a-zA-Z0-9_]+\\Z";
		Pattern format = Pattern.compile(regex);
		Matcher matcher = format.matcher(authorization);
		
		assertTrue(matcher.matches());
	}

}
