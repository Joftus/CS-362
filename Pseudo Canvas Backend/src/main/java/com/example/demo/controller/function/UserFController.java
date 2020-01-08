package com.example.demo.controller.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Assignment;
import com.example.demo.model.Class;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class UserFController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	/*
	 * Need: 
	 */
		
//-------------- Get Mapping -------------------------------------------------------------------------------
	
	// get all users in a class
	@GetMapping(path = "/15/{classId}")
	public @ResponseBody List<User> getUsersInClass(@PathVariable("classId") Integer classId) {
		try {
			Class c = classRepository.findById(classId).get();
			List<User> users = (List<User>) userRepository.findAll();
			List<User> inClass = new ArrayList<User>();
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).classes.contains(c)) inClass.add(users.get(i));
			}
			return inClass; 
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// user sign in
	@GetMapping(path = "/17/{email}/{password}")
	public @ResponseBody User getSignIn(@PathVariable("email") String email, @PathVariable("password") String password) {
		try {	
			if (userRepository.findByEmailAndPassword(email, password).isPresent() == false) return null; 
			User user = userRepository.findByEmailAndPassword(email, password).get();
			return user;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get class info
	@GetMapping (path = "/18/{className}")
	public @ResponseBody String getCInfo(@PathVariable("className") String className) {
		try {
			Class c = classRepository.findByName(className).get();
			return c.info;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get user grades
	@GetMapping (path = "/22/{email}")
	public List<Assignment> getGrades(@PathVariable("email") String email) {
		List<Assignment> userGrades = new ArrayList<Assignment>();
		try {
			User user = userRepository.findByEmail(email).get();
			List<Class> classes = user.classes;
			for (int i = 0; i < classes.size(); i++) {
				List<Assignment> gradebook = classes.get(i).gradebook;
				for (int n = 0; n < gradebook.size(); n++) {
					if (gradebook.get(n).userEmail.equals(user.name)) {
						userGrades.add(gradebook.get(n));
					}
				}
			}
			return userGrades;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	

//-------------- Post Mapping ------------------------------------------------------------------------------
	
	// add a class
	@PostMapping(path = "/23/{email}/{className}")
	public @ResponseBody User addClass(@PathVariable("email") String email, @PathVariable("className") String className){
		try {	
			User user = userRepository.findByEmail(email).get();
			Class c = classRepository.findByName(className).get();
			if (user.classes.contains(c)) return null;
			user.classes.add(c);
			c.users.add(user);
			userRepository.save(user); 
			classRepository.save(c);
			return user;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
		
	// remove a class
	@PostMapping(path = "/24/{email}/{className}")
	public @ResponseBody User RemovedClass(@PathVariable("email") String email, @PathVariable("className") String className){
		try {	
			User user = userRepository.findByEmail(email).get();
			Class c = classRepository.findByName(className).get();
			if (!user.classes.contains(c)) return null;
			user.classes.remove(c);
			c.users.remove(user);
			classRepository.save(c);
			userRepository.save(user);
			return user;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// send message
	@PostMapping (path = "/25/{email}/{senderEmail}/{txt}")
	public Message sendMsg(@PathVariable("txt") String txt, @PathVariable("email") String email, @PathVariable("senderEmail") String senderEmail) {
		try {
			User user = userRepository.findByEmail(email).get();
			if (user.pri) return null;
			Message msg = new Message(txt, user.email, senderEmail);
			messageRepository.save(msg);
			user.messages.add(msg);
			userRepository.save(user);
			return msg;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get all msgs
	@GetMapping(path = "/26/{email}")
	public @ResponseBody List<Message> getAllMsgs(@PathVariable("email") String email){
		try{
			User user = userRepository.findByEmail(email).get();
			return user.messages;
		}
		catch(NullPointerException e) {
			return null;
		}
	}
	
	// create a new group chat channel
	@PostMapping(path = "/61/{email}")
	public @ResponseBody void createGCChannel(@PathVariable("email") String email) {
		try {
			User user = userRepository.findByEmail(email).get();
			int channelNum = 0;
			Random rand = new Random(System.currentTimeMillis());
			while (channelNum < 1000 && channelNum > 10000) {
				channelNum = rand.nextInt();
			}
			user.cGroup = channelNum;
			userRepository.save(user);
		}
		catch (NullPointerException e) {
		}
	}
	
	// enter a group chat channel
	@PostMapping(path = "/62/{email}/{channelNum}")
	public @ResponseBody void joinGCChannel(@PathVariable("email") String email, @PathVariable("channelNum") Integer channelNum) {
		try {
			User user = userRepository.findByEmail(email).get();
			user.cGroup = channelNum;
			userRepository.save(user);
		}
		catch (NullPointerException e) {
		}
	}
	
	// send a msg in a group chat channel
	@PostMapping(path = "/63/{email}/{msg}")
	public @ResponseBody void sendGroupMsg(@PathVariable("email") String email, @PathVariable("msg") String msg) {
		try {
			User user = userRepository.findByEmail(email).get();
			List<User> group = userRepository.findByCGroup(user.cGroup);
			for (int i = 0; i < group.size(); i++) {
				Message m = new Message(msg, group.get(i).email, email, false, false);
				messageRepository.save(m);
				group.get(i).messages.add(m);
				userRepository.save(group.get(i));
			}
		}
		catch (NullPointerException e) {
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
