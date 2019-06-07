import model.APIConnect;
import model.CredentialsReader;
import model.Data;
import view.Menu;

public class TicketViewerApp {

	public static void main(String[] args) {
		CredentialsReader credentialsReader = new CredentialsReader();
		Data data = new Data();
		APIConnect apiConnect = new APIConnect(credentialsReader.getAuthorization());
		Menu menu = new Menu(apiConnect, data);
		menu.menu();
		
		
	}

}
