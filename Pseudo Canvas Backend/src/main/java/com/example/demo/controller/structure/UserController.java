package com.example.demo.controller.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.School;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.SchoolRepository;


@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	
	
//-------------- Get Mapping -------------------------------------------------------------------------------
	
	// get all users
	@GetMapping(path = "/46")
	public @ResponseBody List<User> getAllUsers() {
		try {
			List<User> list = (List<User>) userRepository.findAll();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).email.equals("failure") || list.get(i).email.equals("success")) list.remove(i);
			}
			return list;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get user
	@GetMapping(path = "/47/{id}")
	public @ResponseBody User getUserByID(@PathVariable("id") Integer id) {
		try {
			return userRepository.findById(id).get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	// get user
	@GetMapping(path = "/48/{name}")
	public @ResponseBody List<User> GetUserByName(@PathVariable("name") String name) {
		try {
			return userRepository.findByName(name);
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get user
	@GetMapping(path = "/49/{email}")
	public @ResponseBody User getUserByEmail(@PathVariable("email") String email) {
		try {	
			return userRepository.findByEmail(email).get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	
	
//-------------- Post Mapping ------------------------------------------------------------------------------

	// create a user
	@PostMapping(path = "/50/{email}/{password}/{type}")
	public @ResponseBody User createUser(@PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("type") String type) {
		try {	
			if (userRepository.findByEmail(email).get() != null || email == null || password == null || type == null) return null;
			return userRepository.save(new User(email, password, type));
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// create a user
	@PostMapping(path = "/54/{email}/{password}/{name}/{type}/{school}")
	public @ResponseBody User createUserExtra(@PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("name") String name, @PathVariable("type") String type, @PathVariable("school") String schoolName) {
		try {
			School school = null;
			if (!schoolRepository.findByName(schoolName).isPresent()) {
				school = new School(schoolName);
				schoolRepository.save(school);
			}
			else school = schoolRepository.findByName(schoolName).get();
			User user = null;
			if (type.equals("student") || type.equals("teacher assistant") || type.equals("teacher") || type.equals("admin")) {
				user = new User(email, password, name, type, school);
			}
			else {
				user = new User(email, password, name, "student", school);
			}
			userRepository.save(user);
			return user;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete a user
	@PostMapping(path = "/51/{id}")
	public @ResponseBody User deleteUser(@PathVariable("id") Integer id) {
		try {	
			User remove = userRepository.findById(id).get();
			userRepository.delete(userRepository.findById(id).get());
			return remove;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete all users
	@PostMapping(path = "/52")
	public @ResponseBody String deleteAllUsers() {
		try {	
			userRepository.deleteAll();
			return "All Users Were Deleted!";
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	
	
//-------------- Variable Manipulation ---------------------------------------------------------------------
	
	// change type of user
	@PostMapping(path = "/user/post/change/type/{email}/{type}")
	public @ResponseBody User changeType(@PathVariable("email") String email, @PathVariable("type") String type) {
		try {	
			if (type.equals("student") || type.equals("admin") || type.equals("teacher") || type.equals("teacherAssistant")) {
				userRepository.findByEmail(email).get().type = type;}
			userRepository.save(userRepository.findByEmail(email).get());
			return userRepository.findByEmail(email).get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
}