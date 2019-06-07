import controller.Menu;
import model.APIConnect;

public class TicketViewerApp {

	public static void main(String[] args) {
		Menu menu = new Menu();
		APIConnect apiConnect = new APIConnect();
//		apiConnect.HttpRequestJSON();
		menu.menu();
		
		
	}

}
