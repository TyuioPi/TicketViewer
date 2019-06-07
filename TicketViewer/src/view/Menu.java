package view;

import java.util.Scanner;

import model.APIConnect;
import model.Data;

/*The Menu class is designed to handle the UI for users using the
 * Ticket Viewer application
 */

public class Menu {
	
	// Initialize variables to handling menu functionality
	private Scanner scanner = new Scanner(System.in);
	private boolean running = true;
	private APIConnect apiConnect;
	private Data data;
	
	public Menu(APIConnect apiConnect, Data data) {
		this.apiConnect = apiConnect;
		this.data = data;
	}
	
	public void menu() {

		// Initialize string variable for user input
		String userInput;
		
		System.out.println("Welcome to Ticket Viewer\n" +
							"Type 'help' to view options\n");
		
		// Switch case statement to handle selected options by user
		while (running) {
			userInput = scanner.nextLine();
			
			switch (userInput) {
			case "1":
				System.out.println("Please enter a ticket ID");
				userInput = scanner.nextLine();
				if (validateEnteredTicketId(userInput)) {
					String url = apiConnect.generateURLQueryById(userInput);
					StringBuffer response = apiConnect.HttpRequestJSON(url);
					data.setJSONObject(response);
				}
				break;
			case "2":
				// Get next page url from json
				break;
			case "help":
				displayOptions();
				break;
			case "quit":
				running = false;
				System.out.println("Exiting Ticket Viewer");
				break;
			default:
				System.out.println("Invalid Option Selected");
				break;
			}
		}
	}
	
	// Display options available in Ticket Viewer
	private void displayOptions() {
		System.out.println("Ticket Viewer Options:\n" + 
							"* Press 1 to view a ticket\n" +
							"* Press 2 to view a list of tickets\n" +
							"* Type 'quit' to exit the program\n");
	}
	
	// Validate the entered format of the ticket ID
	private boolean validateEnteredTicketId(String userInput) {
		
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			System.out.println("User has entered invalid ticket ID format");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
