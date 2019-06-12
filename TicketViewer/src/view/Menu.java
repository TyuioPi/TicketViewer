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
	private boolean hasNextPage = false;
	private boolean hasPreviousPage = false;
	
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
							"(Type 'return' to go back to menu)\n");
					
					userInput = scanner.nextLine();
					
					// Return to menu
					if ("return".equals(userInput)) {
						System.out.println("Returned to menu\n"
								+ "Type 'help' to view options\n");
						viewingSingleTicket = false;
						
					// Retrieve data from API and display it
					} else if (validateEnteredTicketId(userInput)) {
						
						// Generate URL for id query
						String url = apiConnect.generateURLQueryById(userInput);
						
						// Get response from URL
						StringBuffer response = apiConnect.HttpRequestJSON(url);
						
						// Parse response from URL
						try {
							data.parseDataByIdQuery(response);
						} catch (NullPointerException e) {
							System.out.println("No data available from provided URL\n"
									+ "Returning to menu\n"
									+ "Type 'help' to view options\n");
							e.printStackTrace();
							break;
						}
						
						// Display ticket
						displayTicketInfo();
					}
				}
				break;
			case "2":
				// Initialize local variables
				boolean viewingTicketList = true;
				boolean viewNextPage = false;
				boolean viewPreviousPage = false;
				String nextPageURL = null;
				String previousPageURL = null;
				int initialize = 0;
				
				do {
					StringBuffer response = null;
					
					// Generate initial URL for displaying ticket list and get response from URL
					if (initialize == 0) {
							String url = apiConnect.generateURLQueryByList();
							response = apiConnect.HttpRequestJSON(url);
							initialize++;
						
					}
					// Generate pagination URL for ticket list and get response from URL
					if (nextPageURL != null && viewNextPage) {
						response = apiConnect.HttpRequestJSON(nextPageURL);
					} else if (previousPageURL != null && viewPreviousPage) {
						response = apiConnect.HttpRequestJSON(previousPageURL);
					}
					
					// Parse response from URL
					try {
						data.parseByPage(response);
					} catch (NullPointerException e) {
						System.out.println("Page does not exist...\n"
								+ "Returning to menu\n"
								+ "Type 'help' to view options\n");
						e.printStackTrace();
						break;
					}
					
					// Display ticket
					displayTicketInfo();
					
					// Initialize pagination
					nextPageURL = getNextURL();
					previousPageURL = getPreviousURL();
					viewNextPage = false;
					viewPreviousPage = false;
					
					// Display pagination message
					paginationMessage();
					
					// Handle ticket list options
					while (viewNextPage == false && viewPreviousPage == false && viewingTicketList) {
						userInput = scanner.nextLine();
						if (userInput.equals("return")) {
							System.out.println("Returned to menu\n"
									+ "Type 'help' to view options\n");
							viewingTicketList = false;
						} else if (userInput.equals("next")) {
							viewNextPage = true;
						} else if (userInput.equals("prev")) {
							viewPreviousPage = true;
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
	
	// Display ticket information
	private void displayTicketInfo() {
		Iterator<Ticket> iterator = ticketList.iterator();
		
		while(iterator.hasNext()) {
			Ticket ticket = iterator.next();
			System.out.println(String.format(
					"ID: %s\nRequester ID: %s\nStatus: %s\nType: %s\n"
					+ "Priority: %s\nCreated At: %s\nSubject: %s\n", 
					ticket.getId(), ticket.getRequesterId(), ticket.getStatus(), 
					ticket.getType(), ticket.getPriority(), ticket.getCreatedAt(), 
					ticket.getSubject()));
		}
	}
	
	// Get next page URL for pagination
	private String getNextURL() {
		String nextPageURL = null;
		
		hasNextPage = false;
		
		if (!ticketList.isEmpty()) {
			if (!ticketList.get(ticketList.size() - 1).getNextPage().equals("null")) {
				hasNextPage = true;
				nextPageURL = ticketList.get(ticketList.size() - 1).getNextPage();
			}
		}
		return nextPageURL;
	}
	
	// Get previous page URL for pagination
	private String getPreviousURL() {
		String previousPageURL = null;
		hasPreviousPage = false;
		
		if (!ticketList.isEmpty()) {
			if (!ticketList.get(ticketList.size() - 1).getPreviousPage().equals("null")) {
				hasPreviousPage = true;
				previousPageURL = ticketList.get(ticketList.size() - 1).getPreviousPage();
			}
		}
		return previousPageURL;
	}
	
	// Display pagination options to user
	private void paginationMessage() {
		if (hasNextPage && hasPreviousPage) {
			System.out.println("Type:\n"
					+ "\t'next' to view next page\n"
					+ "\t'prev' to view previous page or;\n"
					+ "\t'return' to go back to menu\n");
		} else if (hasNextPage) {
			System.out.println("Type:\n"
					+ "\t'next' to view next page or;\n"
					+ "\t'return'to go back to menu\n");
		} else if (hasPreviousPage) {
			System.out.println("Type:\n"
					+ "\t'prev' to view next page or;\n"
					+ "\t'return'to go back to menu\n");
		}
	}
}
