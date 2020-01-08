package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Class {
	
// ---------- Variables ----------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public Integer section;
	public String name;
	public String description;
	public String info;	// "{days} / {time} / {location} / {room number}"
	public String school;

	@ManyToMany
	@JsonBackReference
	public List<User> users;
	@OneToMany
	@JsonBackReference
	public List<Assignment> gradebook;
	

// ---------- Constructors ----------
	public Class() {
		init();
	}
	
	public Class(String name) {
		init();
		this.name = name;
	}
	
	public Class(String name, User user) {
		init();
		this.name = name;
		this.users.add(user);
	}
	
	
	
// ---------- Custom Methods ----------
	
	
	private void init() {
		this.info = "info";
		this.gradebook = new ArrayList<Assignment>();
		this.users = new ArrayList<User>();
		section = 1;
	}
	
	
	
// ---------- To String ----------
    @Override
    public String toString() {
    		return new ToStringCreator(this)
    				.append("ID", this.id)
    				.append("Name", this.name)
    				.append("Description", this.description)
    				.append("Class Info", this.info)
    				.append("Sections", this.section)
    				.append("School", this.school)
    				.append("Users", this.users)
    				.append("Gradebook", this.gradebook)
    				.toString();
    }
}
