package controller;

import java.util.Scanner;

/*The Menu class is designed to handle the UI for users using the
 * Ticket Viewer application
 */

public class Menu {
	
	// Initialize variables to handling menu functionality
	private Scanner scanner = new Scanner(System.in);
	private boolean running = true;
	
	public Menu() {}
	
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
				System.out.println("1");
				break;
			case "2":
				System.out.println("2");
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
}
