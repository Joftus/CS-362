package com.example.demo.controller.function;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Assignment;
import com.example.demo.model.Class;
import com.example.demo.model.Message;
import com.example.demo.model.School;
import com.example.demo.model.User;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class UpdateController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	
	
	@PostMapping(path = "/update/user/{id}/{email}/{password}/{name}/{type}/{pri}")
	public @ResponseBody User updateUser(@PathVariable("id") Integer id, @PathVariable("email") String email, @PathVariable("password") String password , @PathVariable("name") String name, 
			@PathVariable("type") String type, @PathVariable("pri") Boolean pri) {
		User user = null;
		try {
			user = userRepository.findById(id).get();
			user.email = email;
			user.password = password;
			user.name = name;
			user.type = type;
			user.pri = pri;
			userRepository.save(user);
		}
		catch (NullPointerException e) {
			System.out.println("Error in UpdateController.User");
		}
		return user;
	}
	
	@PostMapping(path = "/update/class/{id}/{name}/{section}/{description}/{info}/{school}")
	public @ResponseBody Class updateClass(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("section") Integer section, @PathVariable("description") String description, @PathVariable("info") String info, @PathVariable("school") String school) {
		Class c = null;
		try {
			c = classRepository.findById(id).get();
			c.name = name;
			c.section = section;
			c.description = description;
			c.info = info;
			c.school = school;
			classRepository.save(c);
		}
		catch (NullPointerException e) {
			System.out.println("Error in UpdateController.User");
		}
		return c;
	}
	
	@PostMapping(path = "/update/school/{id}/{name}")
	public @ResponseBody School updateSchool(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		School school = null;
		try {
			school = schoolRepository.findById(id).get();
			school.name = name;
			schoolRepository.save(school);
		}
		catch (NullPointerException e) {
			System.out.println("Error in UpdateController.User");
		}
		return school;
	}
	
	@PostMapping(path = "/update/grade/{id}/{assignment}/{grade}/{userEmail}/{className}")
	public @ResponseBody Assignment updateAssignment(@PathVariable("id") Integer id, @PathVariable("assignment") String assignment, @PathVariable("grade") Integer score, @PathVariable("userEmail") String userEmail, @PathVariable("className") String className) {
		Assignment grade = null;
		try {
			grade = assignmentRepository.findById(id).get();
			grade.assignment = assignment;
			grade.grade = score;
			grade.userEmail = userEmail;
			grade.className = className;
			assignmentRepository.save(grade);
		}
		catch (NullPointerException e) {
			System.out.println("Error in UpdateController.User");
		}
		return grade;
	}
	
	@PostMapping(path = "/update/message/{id}/{msg}/{date}/{sent}/{notification}/{to}/{from}")
	public @ResponseBody Message updateMessage(@PathVariable("id") Integer id, @PathVariable("msg") String note, @PathVariable("date") Calendar date, @PathVariable("sent") Boolean sent, @PathVariable("notificaiton") Boolean notification, @PathVariable("to") String to, @PathVariable("from") String from) {
		Message msg = null;
		try {
			msg = messageRepository.findById(id).get();
			msg.msg = note;
			msg.date = date;
			msg.sent = sent;
			msg.notification = notification;
			msg.toEmail = to;
			msg.fromEmail = from;
			messageRepository.save(msg);
		}
		catch (NullPointerException e) {
			System.out.println("Error in UpdateController.User");
		}
		return msg;
	}
}
