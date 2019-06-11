package model;

public class Ticket {

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
	
	public String getId() {
		return id;
	}

	public String getRequesterId() {
		return requesterId;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getCreatedAt() {
		return created_at;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getType() {
		return type;
	}
	
	public String getNextPage() {
		return next_page;
	}
}
