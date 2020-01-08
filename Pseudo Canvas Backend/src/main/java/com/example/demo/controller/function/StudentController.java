package com.example.demo.controller.function;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class StudentController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	// check messages
	@GetMapping(path = "/5/{email}")
	public @ResponseBody List<Message> checkMessages(@PathVariable("email") String email) {
		try {
			User user = userRepository.findByEmail(email).get();
			return user.messages;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete message
	@PostMapping(path = "/6/{email}/{messages}")
	public @ResponseBody Message deleteMessages(@PathVariable("email") String email, @PathVariable("messages") List<Message> messages){
		List<Message> list = new ArrayList<Message>();
		try {
			User user = userRepository.findByEmail(email).get();
			list = messageRepository.findByToEmailOrFromEmail(user.email, user.email);
			user.messages = messages;
			list = messages;
			userRepository.save(user);
			messageRepository.saveAll(list);
			return messageRepository.findByMsg("success").get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
}
