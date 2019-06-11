package model;

public class Ticket {

	// Initialize variables of a ticket
	private String id;
	private String requesterId;
	private String subject;
	private String created_at;
	private String priority;
	private String status;
	private String type;
	private String next_page;
	
	public Ticket(String id, String requesterId, String subject, String created_at, 
			String priority, String status, String type, String next_page) {
		this.id = id;
		this.requesterId = requesterId;
		this.subject = subject;
		this.created_at = created_at;
		this.priority = priority;
		this.status = status;
		this.type = type;
		this.next_page = next_page;
	}
	
	// Get ticket id
	public String getId() {
		return id;
	}

	// Get id of requester for a ticket
	public String getRequesterId() {
		return requesterId;
	}
	
	// Get subject of a ticket
	public String getSubject() {
		return subject;
	}
	
	// Get date ticket is created
	public String getCreatedAt() {
		return created_at;
	}
	
	// Get priority of a ticket
	public String getPriority() {
		return priority;
	}
	
	// Get the ticket status
	public String getStatus() {
		return status;
	}
	
	// Get the type of ticket
	public String getType() {
		return type;
	}
	
	// Get next page URL
	public String getNextPage() {
		return next_page;
	}
}
