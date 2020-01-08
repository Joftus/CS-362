package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Assignment {

// ---------- Variables ----------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String assignment;
	public Integer grade;
	public String userEmail;
	public String className;
	public boolean viewable;

	
	
// ---------- Constructors ----------
	public Assignment() {
		init();
	}
	
	public Assignment(String userEmail, String className, Integer grade, String assignment) {
		init();
		this.userEmail = userEmail;
		this.className = className;
		this.grade = grade;
		this.assignment = assignment;
	}

	

// ---------- Custom Methods ----------
	private void init() {
		viewable = false;
	}
			
		
		
// ---------- To String ------------
	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("id", this.id)
				.append("grade", this.grade)
				.append("user", this.userEmail)
				.append("class", this.className)
				.toString();
	}
}
