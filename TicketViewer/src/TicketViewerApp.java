import java.util.ArrayList;
import java.util.List;

import model.APIConnect;
import model.CredentialsReader;
import model.Data;
import model.Ticket;
import view.Menu;

public class TicketViewerApp {

	private static List<Ticket> ticketList = new ArrayList<Ticket>();
	
	public static void main(String[] args) {
		CredentialsReader credentialsReader = new CredentialsReader();
		Data data = new Data(ticketList);
		APIConnect apiConnect = new APIConnect(credentialsReader.getAuthorization());
		Menu menu = new Menu(apiConnect, data, ticketList);
		menu.menu();
		
		
	}

}
