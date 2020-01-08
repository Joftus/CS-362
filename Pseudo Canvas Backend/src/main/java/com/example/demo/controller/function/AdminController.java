package com.example.demo.controller.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class AdminController {
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	UserRepository userRepository;
	
	// add class info
	@PostMapping(path = "/1/{email}/{className}/{days}/{time}/{location}/{roomNum}")
	public @ResponseBody String editCInfo(@PathVariable("email") String email, @PathVariable("className") String className, @PathVariable("days") String days, @PathVariable("time") String time, @PathVariable("location") String location, @PathVariable("roomNum") String roomNum) {
		try {
			Class c = classRepository.findByName(className).get();
			if (!userRepository.findByEmail(email).get().type.equals("admin")) return null;
			days = days.toUpperCase();
			c.info = days + " ! " + time + " ! " + location + " ! " + roomNum;
			classRepository.save(c);
			return c.info;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
}
