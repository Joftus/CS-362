package model;

import java.util.ArrayList;
import java.util.List;

public class User {

// ---------- Variables ----------
	public Integer id;
	public String email;
	public String password;
	public String name;
	public String type;
	public boolean pri;
	public Integer cGroup;
	public Integer gGroup;
	
	public School school;
	public List<Class> classes;
	public List<Message> messages;
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
	
	// for JSONObjects
	public User(Integer id, String email, String password, String name, String type, Boolean pri, Integer cGroup, Integer gGroup, School school, List<Class> classes, List<Message> messages, List<Assignment> grades) {
		init();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.type = type;
		this.pri = pri;
		this.cGroup = cGroup;
		this.gGroup = gGroup;
		this.school = school;
		this.classes = classes;
		this.messages = messages;
		this.assignments = grades;
	}
	
	
	
// ---------- Custom Methods ----------
	private void init() {
		this.pri = true;
		this.classes = new ArrayList<Class>();
		this.messages = new ArrayList<Message>();
		this.assignments = new ArrayList<Assignment>();
	}
	
	
	
// ---------- To String ----------
	@Override
    public String toString() {
        String out =  
        			"Name: " + this.name +
                "\n	Email: " + this.email +
                "\n	Password: " + this.password +
                "\n	ID: " + this.id +
                "\n	Type: " + this.type +
                "\n	Chat Group: " + this.cGroup +
                "\n	Grade Group: " + this.gGroup +
                "\n	School: " + this.school.toString() +
                "\n	Classes: " + this.classes.toString() +
                "\n	Assignments: " + this.assignments.toString() +
                "\n	Messages: " + this.messages.toString();
        return out;
	}
}