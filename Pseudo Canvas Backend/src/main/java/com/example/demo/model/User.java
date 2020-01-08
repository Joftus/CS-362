package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.core.style.ToStringCreator;

@Entity
public class User {

// ---------- Variables ----------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String email;
	public String password;
	public String name;
	public String type;
	public boolean pri;
	public Integer cGroup;
	public Integer gGroup;
	
	@ManyToOne
	public School school;
	@ManyToMany
	public List<Class> classes;
	@OneToMany
	public List<Message> messages;
	@OneToMany
	public List<Assignment> assignments;
	
	
	
// ---------- Constructors ----------
	public User() {
		init();
	}
	
	public User(String email, String password, String type) {
		init();
		this.email = email; 
		this.password = password;
		this.type = type;
	}
	
	public User(String name, String email, String password, String type) {
		init();
		this.email = email;
		this.password = password;
		this.type = type;
		this.name = name;
	}
	
	public User(String name, String email, String password, String type, School school) {
		init();
		this.email = email;
		this.password = password;
		this.type = type;
		this.name = name;
		this.school = school;
	}
	
	
	
// ---------- Custom Methods ----------
	private void init() {
		this.pri = true;
		this.cGroup = 0;
		this.gGroup = 0;
		this.classes = new ArrayList<Class>();
		this.messages = new ArrayList<Message>();
		this.assignments = new ArrayList<Assignment>();
	}
	
	
	
// ---------- To String ----------
	@Override
    public String toString() {
        return new ToStringCreator(this)
                .append("ID", this.id)
                .append("Email", this.email)
                .append("Password", this.password)
                .append("Name", this.name)
                .append("Type", this.type)
                .append("Private", this.pri)
                .append("Chat Group", this.cGroup)
                .append("Grading Group", this.gGroup)
                .append("Messages", this.messages)
                .append("Classes", this.classes)
                .append("Assignments", this.assignments)
                .toString();
	}
}