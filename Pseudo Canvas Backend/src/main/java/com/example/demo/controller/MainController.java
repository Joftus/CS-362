package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MainController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	// add basic users
	@PostMapping(path = "/reset")
	public @ResponseBody String resetDatabase() throws InterruptedException {
		User noah = new User("noah@iastate.edu", "a", "student");
		User tyler = new User("tyler@iastate.edu", "a", "admin");
		User yuan = new User("yuan@iastate.edu", "a", "teacher");
		//Class comS = new Class("ComS");
		//Thread.sleep(10);
		Class math = new Class("Math");
		//Thread.sleep(10);
		//Class sof = new Class("SoftE");
		School school = new School("Iowa_State");
		
		schoolRepository.save(school);
		
		noah.classes.add(math);
		tyler.classes.add(math);
		yuan.classes.add(math);
		/*
		noah.classes.add(comS);
		noah.classes.add(sof);
		tyler.classes.add(comS);
		tyler.classes.add(sof);
		yuan.classes.add(comS);
		yuan.classes.add(sof);
		*/
		noah.name = "Noah";
		tyler.name = "Tyler";
		yuan.name = "Yuan";
		
		//classRepository.save(comS);
		classRepository.save(math);
		//classRepository.save(sof);
		userRepository.save(noah);
		userRepository.save(tyler);
		userRepository.save(yuan);
		
		/*
		comS.cInfo = "MWF ! 5 ! BuildingF ! 712";
		math.cInfo = "WF ! 9 ! BuildingF ! 345";
		sof.cInfo = "MF ! 8 ! BuildingF ! 143";
		span.cInfo = "MWF ! 11 ! BuildingF ! 214";
		
		comS.rInfo = "TF ! 10 ! BuildingA ! 894";
		math.rInfo = "TR ! 4 ! BuildingA ! 315";
		sof.rInfo = "TW ! 2 ! BuildingA ! 315";
		span.rInfo = "TR ! 1 ! BuildingA ! 151";
		
		comS.lInfo = "TWR ! 12 ! BuildingL ! 802";
		math.lInfo = "R ! 3 ! BuildingL ! 345";
		sof.lInfo = "TR ! 1 ! BuildingL ! 1505";
		span.lInfo = "T ! 11 ! BuildingL ! 117";
		*/
		
		//classRepository.save(comS);
		classRepository.save(math);
		//classRepository.save(sof);
		/*
		comS.users.add(noah);
		comS.users.add(tyler);
		comS.users.add(yuan);
		sof.users.add(noah);
		sof.users.add(tyler);
		sof.users.add(yuan);
		*/
		math.users.add(noah);
		math.users.add(tyler);
		math.users.add(yuan);
		
		
		//classRepository.save(comS);
		classRepository.save(math);
		//classRepository.save(sof);
		userRepository.save(noah);
		userRepository.save(tyler);
		userRepository.save(yuan);
		schoolRepository.save(school);
		
		math.school = school.name;
		//comS.school = school.name;
		//sof.school = school.name;
		
		//classRepository.save(comS);
		classRepository.save(math);
		//classRepository.save(sof);
		
		school.classes.add(math);
		//school.classes.add(comS);
		//school.classes.add(sof);
		
		schoolRepository.save(school);
		
		//String d1 = "Embedded C programming. Interrupt handling. Memory mapped I/O in the context of an application. Elementary embedded design flow/methodology. Timers, scheduling, resource allocation, optimization, and state based controllers.";
		String d3 = "Solution methods for ordinary differential equations. First order equations, linear equations, constant coefficient equations. Eigenvalue methods for systems of first order linear equations.";
		//String d3 = "A practical introduction to methods for managing software development. Process models, requirements analysis, structured and object-oriented design, coding, testing, maintenance, cost and schedule estimation, metrics.";
		
		// subject to change
		int strLen = 15;
		//comS.description = d1.substring(0, strLen); 
		math.description = d3.substring(0, strLen); 
		//sof.description = d3.substring(0, strLen);
		
		//classRepository.save(comS);
		classRepository.save(math);
		//classRepository.save(sof);
		schoolRepository.save(school);
		
		// Noah
		Assignment a = new Assignment("noah@iastate.edu", "Math", 97, "hw_1");
		a.viewable = true;
		Assignment b = new Assignment("noah@iastate.edu", "Math", 92, "hw_2");
		b.viewable = true;
		Assignment c = new Assignment("noah@iastate.edu", "Math", 81, "exam");
		c.viewable = true;
		Assignment d = new Assignment("noah@iastate.edu", "Math", 54, "hw_3");
		d.viewable = true;
		Assignment e = new Assignment("noah@iastate.edu", "Math", 86, "final");
		e.viewable = true;
		assignmentRepository.save(a);
		assignmentRepository.save(b);
		assignmentRepository.save(c);
		assignmentRepository.save(d);
		assignmentRepository.save(e);
		noah.assignments.add(a);
		noah.assignments.add(b);
		noah.assignments.add(c);
		noah.assignments.add(d);
		noah.assignments.add(e);
		Message f = new Message("Forgot your notebook", "noah@iastate.edu", "jtloftus@iastate.edu");
		Message g = new Message("Missed class", "noah@iastate.edu", "jtloftus@iastate.edu");
		Message h = new Message("Review tomorrow", "noah@iastate.edu", "jtloftus@iastate.edu");
		messageRepository.save(f);
		messageRepository.save(g);
		messageRepository.save(h);
		noah.messages.add(f);
		noah.messages.add(g);
		noah.messages.add(h);
		noah.school = school;
		noah.pri = false;
		userRepository.save(noah);
		
		// Yuan
		Assignment a1 = new Assignment("yuan@iastate.edu", "Math", 0, "breaker");
		a1.viewable = true;
		assignmentRepository.save(a1);
		yuan.assignments.add(a1);
		Message f1 = new Message("Forgot your notebook", "yuan@iastate.edu", "jtloftus@iastate.edu");
		Message g1 = new Message("Missed class", "yuan@iastate.edu", "jtloftus@iastate.edu");
		Message h1 = new Message("Review tomorrow", "yuan@iastate.edu", "jtloftus@iastate.edu");
		messageRepository.save(f1);
		messageRepository.save(g1);
		messageRepository.save(h1);
		yuan.messages.add(f1);
		yuan.messages.add(g1);
		yuan.messages.add(h1);
		yuan.school = school;
		yuan.pri = false;
		userRepository.save(yuan);
		
		// Tyler
		Assignment a2 = new Assignment("tyler@iastate.edu", "Math", 58, "hw_1");
		a2.viewable = true;
		Assignment b2 = new Assignment("tyler@iastate.edu", "Math", 92, "hw_2");
		b2.viewable = true;
		Assignment c2 = new Assignment("tyler@iastate.edu", "Math", 83, "exam");
		c2.viewable = true;
		Assignment d2 = new Assignment("tyler@iastate.edu", "Math", 67, "hw_3");
		d2.viewable = true;
		Assignment e2 = new Assignment("tyler@iastate.edu", "Math", 99, "final");
		e2.viewable = true;
		assignmentRepository.save(a2);
		assignmentRepository.save(b2);
		assignmentRepository.save(c2);
		assignmentRepository.save(d2);
		assignmentRepository.save(e2);
		tyler.assignments.add(a2);
		tyler.assignments.add(b2);
		tyler.assignments.add(c2);
		tyler.assignments.add(d2);
		tyler.assignments.add(e2);
		Message f2 = new Message("Forgot your notebook", "tyler@iastate.edu", "jtloftus@iastate.edu");
		Message g2 = new Message("Missed class", "tyler@iastate.edu", "jtloftus@iastate.edu");
		Message h2 = new Message("Review tomorrow", "tyler@iastate.edu", "jtloftus@iastate.edu");
		messageRepository.save(f2);
		messageRepository.save(g2);
		messageRepository.save(h2);
		tyler.messages.add(f2);
		tyler.messages.add(g2);
		tyler.messages.add(h2);
		tyler.school = school;
		tyler.pri = false;
		userRepository.save(tyler);
		
		return "complete";
	}
	
	
	
	// clears database
	@PostMapping(path = "/clear")
	public @ResponseBody String clearDatabase() {
		if (userRepository.findAll() != null) userRepository.deleteAll();
		if (classRepository.findAll() != null) classRepository.deleteAll();
		if (assignmentRepository.findAll() != null) assignmentRepository.deleteAll();
		if (schoolRepository.findAll() != null) schoolRepository.deleteAll();
		if (messageRepository.findAll() != null) messageRepository.deleteAll();
		return "complete";
	}
}
