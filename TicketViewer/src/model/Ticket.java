package model;

// The Ticket class is designed to model ticket objects
public class Ticket {

	// Initialize variables of a ticket
	private String id;
	private String requesterId;
	private String subject;
	private String createdAt;
	private String priority;
	private String status;
	private String type;
	private String nextPage;
	private String previousPage;
	
	public Ticket(String id, String requesterId, String subject, String createdAt, 
			String priority, String status, String type, String nextPage,
			String previousPage) {
		this.id = id;
		this.requesterId = requesterId;
		this.subject = subject;
		this.createdAt = createdAt;
		this.priority = priority;
		this.status = status;
		this.type = type;
		this.nextPage = nextPage;
		this.previousPage = previousPage;
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
		return createdAt;
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
		return nextPage;
	}
	
	// Get previous page URL
	public String getPreviousPage() {
		return previousPage;
	}
}
