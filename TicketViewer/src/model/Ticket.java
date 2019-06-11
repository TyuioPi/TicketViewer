package model;

import java.util.ArrayList;

public class Ticket {

	private String id, subject, created_at, priority, status, type, next_page;
	private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	
	public Ticket(String id, String subject, String created_at, String priority,
			String status, String type, String next_page) {
		this.id = id;
		this.subject = subject;
		this.created_at = created_at;
		this.priority = priority;
		this.status = status;
		this.type = type;
		this.next_page = next_page;
	}
	
	public void addTicket(Ticket ticket) {
		ticketList.add(ticket);
	}
	
	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}
}
