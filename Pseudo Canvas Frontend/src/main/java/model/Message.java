package model;

public class Message {

// ---------- Variables ----------
	public Integer id;
	public String msg;
	public String date;
	public boolean sent;
	public boolean notification;
	public String toEmail;
	public String fromEmail;
	
	
	
// ---------- Constructors ----------	
	public Message() {
		init();
	}
	
	public Message(String msg) {
		this.msg = msg;
	}
	
	public Message(String msg, String to, String from) {
		init();
		this.msg = msg;
		this.toEmail = to;
		this.fromEmail = from;
	}
	
	public Message(String msg, String to, String from, boolean sent) {
		init();
		this.msg = msg;
		this.toEmail = to;
		this.fromEmail = from;
		this.sent = sent;
	}
	
	public Message(Integer id, String msg, String date, boolean sent, boolean notification, String to, String from) {
		init();
		this.id = id;
		this.msg = msg;
		this.date = date;
		this.sent = sent;
		this.notification = notification;
		this.toEmail = to;
		this.fromEmail = from;
	}
	

	
// ---------- Custom Methods ----------
	private void init() {
	}
		
	
	
// ---------- To String ----------	
	@Override
    public String toString() {
        String out = 
        		"\n		From: " + this.fromEmail +
        		"\n		To: " + this.toEmail +
        		"\n		ID: " + this.id +
        		"\n		Date: " + this.date +
        		"\n		Sent: " + this.sent +
        		"\n		Notification: " + this.notification +
        		"\n		Message: " + this.msg + "\n";
        return out;
	}
}
