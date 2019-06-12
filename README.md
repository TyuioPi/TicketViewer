# TicketViewer

## Setup Guide
**Installation**  
1. Download Eclipse 2019-03 IDE via https://www.eclipse.org/downloads/  
2. Follow installation setup  

**Import Project**  
3. Open Eclipse IDE
4. File --> Import --> Git --> Projects from Git --> Clone URI
5. In URI enter: "https://<span></span>github.com/TyuioPi/TicketViewer.git"
6. In branch selection tick 'master'
7. In Local Destination, enter desired directory details
8. Select 'Import existing Eclipse projects' as our Wizard
9. Finish

**Credentials**
Put the auth.txt file provided into the TicketViewer directory 'TicketViewer/TicketViewer'
(It should reside where the folders 'bin' and 'src' exist)

**Other Packages**
In the case TicketViewer has issues with JSON objects please download the
following JSON package via https://mvnrepository.com/artifact/org.json/json/20180813

To install it to our project:
In Eclipse, right-click TicketViewer --> Properties --> Java build path --> 
			--> Select Libraries tab --> Add external jars --> Select the JSON package downloaded

## User Manual

**Run Application**  
1. In Eclipse, expand TicketViewer --> src --> default package --> Select TicketViewerApp --> Run