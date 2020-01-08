package com.example.demo.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Message {

// ---------- Variables ----------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String msg;
	public Calendar date;
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
	
	public Message(String msg, String to, String from, boolean sent, boolean notification) {
		init();
		this.msg = msg;
		this.toEmail = to;
		this.fromEmail = from;
		this.sent = sent;
		this.notification = notification;
	}
	

	
// ---------- Custom Methods ----------
	private void init() {
		this.notification = false;
		this.sent = false;
		this.date = Calendar.getInstance();
	}
		
	
	
// ---------- To String ----------	
	@Override
    public String toString() {
        return new ToStringCreator(this)
        		.append("ID", this.id)
        		.append("Date", this.date)
        		.append("Sent", this.sent)
        		.append("Notification", this.notification)
        		.append("To", this.toEmail)
        		.append("From", this.fromEmail)
        		.append("Message", this.msg)
        		.toString();
	}
}
