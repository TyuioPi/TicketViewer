# TicketViewer
The project was to design an application that can connect and retrieve information via the Zendesk API and display them under specified requirements.

## Getting Started
**Installation**  
1. Download Eclipse 2019-03 IDE via https://www.eclipse.org/downloads/  
2. Follow installation setup  

**Import Project**  
1. Open Eclipse IDE  
2. File --> Import --> Git --> Projects from Git --> Clone URI  
3. In URI enter: "https://<span></span>github.com/TyuioPi/TicketViewer.git"  
4. In branch selection tick 'master'  
5. In Local Destination, enter desired directory details  
6. Select 'Import existing Eclipse projects' as our Wizard  
7. Finish  

**Credentials**  
Put the auth.txt file provided into the TicketViewer directory 'TicketViewer/TicketViewer'  
(It should reside where the folders 'bin' and 'src' exist)  

**Other Packages**  
In the case TicketViewer has issues with JSON objects please download the following JSON package via https://mvnrepository.com/artifact/org.json/json/20180813    

To install it to our project:  
In Eclipse, right-click TicketViewer --> Properties --> Java build path --> Select Libraries tab --> Add external jars --> Select the JSON package downloaded

## User Manual  
**Running the Application**  
1. In Eclipse, expand TicketViewer --> src --> default package --> Select TicketViewerApp --> Run

**Running the Tests**
1. In Eclipse, expand TicketViewer --> src --> test --> Select class to test --> Run

**Operating Application**  
Via Main Menu:  
* Type '1' to view a single ticket  
	- The user will then be asked to either  
	1. Enter a ticket id which will display the ticket corresponding to the id or;  
	2. Enter 'return' will take the user back to the main menu  
* Type '2' to view the ticket list  
	- The user will then be automatically be shown up to the first 25 tickets available and be asked to either  
	1. Enter 'next' (if available) to view the next page of tickets  
	2. Enter 'prev' (if available) to view the previous page of tickets or;  
	3. Enter 'return' to return to the main menu
* Type 'quit' to exit TicketViewer  
* Type 'help' to view options available in main menu  

## Miscellaneous  
**Design Assumptions**  
In this project, we assume that the ticket id entered are to be of natural numbers in our application for handling single ticket viewing via use of the API for querying tickets with an id

**Design Improvements**
* The menu method in the Menu class could have been further broken down into multiple smaller methods for readability
* The attributes nextPage and previousPage in the Ticket class could have been extracted and stored possibly in the Menu class as tickets do not necessarily have a next page or previous page in this particular case   

