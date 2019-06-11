package view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.APIConnect;
import model.Data;
import model.Ticket;

/*The Menu class is designed to handle the UI for users using the
 * Ticket Viewer application
 */
public class Menu {
	
	// Initialize variables to handling menu functionality
	private Scanner scanner = new Scanner(System.in);
	private boolean running = true;
	private APIConnect apiConnect;
	private Data data;
	private List<Ticket> ticketList;
	
	public Menu(APIConnect apiConnect, Data data, List<Ticket> ticketList) {
		this.apiConnect = apiConnect;
		this.data = data;
		this.ticketList = ticketList;
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
				boolean viewingSingleTicket = true;
				
				// View single tickets until user returns to menu
				while (viewingSingleTicket) {
					System.out.println("Please enter a ticket ID: \n" + 
									   "(Type 'return' to go back to menu)");
					
					userInput = scanner.nextLine();
					
					// Return to menu
					if ("return".equals(userInput)) {
						viewingSingleTicket = false;
						
						
					// Retrieve data from API and display it
					} else if (validateEnteredTicketId(userInput)) {
						
						// Generate URL for id query
						String url = apiConnect.generateURLQueryById(userInput);
						
						// Get response from URL
						StringBuffer response = apiConnect.HttpRequestJSON(url);
						
						// Parse response from URL
						data.parseDataByIdQuery(response);
						
						// Display ticket
						displayTicketInfo();
					}
				}
				break;
			case "2":
				boolean viewingTicketList = true;
				String nextPageURL = null;
				boolean viewNextPage = false;
				
				do {
					StringBuffer response;
					
					// Generate URL for ticket list and get response from URL
					if (nextPageURL != null && viewNextPage) {
						response = apiConnect.HttpRequestJSON(nextPageURL);
					} else {
						String url = apiConnect.generateURLQueryByList();
						response = apiConnect.HttpRequestJSON(url);
					}
					
					// Parse response from URL
					data.parseByPage(response);
					
					// Display ticket
					displayTicketInfo();
					nextPageURL = getNextPageURL();
					viewNextPage = false;
					
					while (viewNextPage == false && viewingTicketList) {
						userInput = scanner.nextLine();
						if (userInput.equals("return")){
							viewingTicketList = false;
						} else if (userInput.equals("next")) {
							viewNextPage = true;
						} else {
							System.out.println("Invalid Option Selected");
						}
					} 
					
				} while (viewingTicketList);
				break;
			case "help":
				// Display help options
				displayOptions();
				break;
			case "quit":
				// Exit Ticket Viewer program
				running = false;
				System.out.println("Thank you for using Ticket Viewer");
				break;
			default:
				// Notify user of invalid option selected
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
	
	private void displayTicketInfo() {
		Iterator<Ticket> iterator = ticketList.iterator();
		
		while(iterator.hasNext()) {
			Ticket ticket = iterator.next();
			System.out.println(ticket.getId());
			System.out.println(ticket.getRequesterId());
			System.out.println(ticket.getSubject());
			System.out.println(ticket.getCreatedAt());
			System.out.println(ticket.getPriority());
			System.out.println(ticket.getStatus());
			System.out.println(ticket.getType() + "\n");
		}
	}
	
	private String getNextPageURL() {
		String nextPageURL = null;
		boolean hasNextPage = false;
		if (!ticketList.isEmpty()) {
			if (!ticketList.get(ticketList.size() - 1).getNextPage().equals("null")) {
				hasNextPage = true;
				nextPageURL = ticketList.get(ticketList.size() - 1).getNextPage();
			}
		}
		
		if (hasNextPage) {
			System.out.println("Type 'next' to view next page or 'return'"
					+ " to go back to menu\n");
		}
		
		return nextPageURL;
	}
}
