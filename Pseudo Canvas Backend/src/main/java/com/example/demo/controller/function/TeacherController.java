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
public class TeacherController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	AssignmentRepository assignmentRepository;

	
	
	// get students in class
	@GetMapping(path = "/7/{className}")
	public @ResponseBody List<User> getStudents(@PathVariable("className") String className) {
		List<User> students = new ArrayList<User>();
		try {
			Class c = classRepository.findByName(className).get();
			for (int i = 0; i < c.users.size(); i++) {
				if (c.users.get(i).type.equals("student")) students.add(c.users.get(i));
			}
			return students;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// create a class
	@PostMapping(path = "/8/{email}/{className}")
	public @ResponseBody Class createClass(@PathVariable("email") String email, @PathVariable("className") String className) {
		try {
			if (!userRepository.findByEmail(email).isPresent() || userRepository.findByEmail(email).get().type != "teacher" || className == null) return null;
			return classRepository.save(new Class(className, userRepository.findByEmail(email).get()));
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// create a section
	@PostMapping(path = "/9/{className}")
	public @ResponseBody Class createSection(@PathVariable("className") String className) {
		try {
			Class c = new Class();
			Class original = classRepository.findByName(className).get();
			int i;
			for (i = 1;; i++) {
				if (!classRepository.findByNameAndSection(className, i).isPresent()) break;
			}
			c.name = original.name;
			c.description = original.description;
			c.name = c.name;
			c.section = i + 1;
			c.school = original.school;
			classRepository.save(c);
			return c;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// add and change class description
	@PostMapping(path = "/13/{email}/{classId}/{description}")
	public @ResponseBody String addDescription(@PathVariable("email") String email, @PathVariable("classId") Integer classId, @PathVariable("description") String description) {
		try {
			User user = userRepository.findByEmail(email).get();
			Class c = classRepository.findById(classId).get();
			if (user == null || !user.type.equals("teacher") || c == null || !c.users.contains(user)) return null;
			if (description.length() > 50) description = description.substring(0, 49);
			c.description = description;
			classRepository.save(c);
			return description;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// add a grade
	@PostMapping(path = "/14/{grade}/{email}/{teacherEmail}/{assignment}/{className}")
	public @ResponseBody User addGrade(@PathVariable("grade") Integer grade, @PathVariable("email") String email, @PathVariable("teacherEmail") String teacherEmail, @PathVariable("assignment") String assignment, @PathVariable("className") String className) {
		try {
			User user = userRepository.findByEmail(email).get();
			User teacher = userRepository.findByEmail(teacherEmail).get();
			Class c = classRepository.findByName(className).get();
			Assignment temp = new Assignment(user.email, c.name, grade, assignment);
			Message msg = new Message("New Grade Added!", user.email, teacherEmail);
			messageRepository.save(msg);
			assignmentRepository.save(temp);
			teacher.assignments.add(temp);
			user.messages.add(msg);
			user.assignments.add(temp);
			userRepository.save(user);
			userRepository.save(teacher);
			return teacher;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// edit a grade
	@PostMapping(path = "/55/{email}/{teacherEmail}/{assignment}/{className}/{score}")
	public @ResponseBody User editGrade(@PathVariable("email") String email, @PathVariable("teacherEmail") String teacherEmail, @PathVariable("assignment") String assignment, @PathVariable("className") String className, @PathVariable("score") Integer score) {
		try {
			User user = userRepository.findByEmail(email).get();
			User teacher = userRepository.findByEmail(teacherEmail).get();
			Assignment a = assignmentRepository.findByAssignmentAndUserEmail(assignment, email).get();
			Message msg = new Message("New Grade Update!", user.email, teacherEmail);
			user.assignments.remove(a);
			userRepository.save(user);
			messageRepository.save(msg);
			a.grade = score;
			a.viewable = true;
			assignmentRepository.save(a);
			user.messages.add(msg);
			user.assignments.add(a);
			userRepository.save(user);
			userRepository.save(teacher);
			return teacher;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get assignments for a class
	@GetMapping(path = "/56/{className}")
	public @ResponseBody List<Assignment> getAssignemnts(@PathVariable("className") String className) {
		return assignmentRepository.findByClassName(className);
	}
	
	// post a new assignment to a class
	@PostMapping(path = "/57/{className}/{ass}")
	public @ResponseBody String t1(@PathVariable("className") String className, @PathVariable("ass") String ass) {
		try {
			List<User> students = new ArrayList<User>();
			Class c = classRepository.findByName(className).get();
			for (int i = 0; i < c.users.size(); i++) {
				if (c.users.get(i).type.equals("student")) students.add(c.users.get(i));
			}
			for (int j = 0; j < students.size(); j++) {
				Assignment a = new Assignment(students.get(j).email, c.name, 0, ass);
				assignmentRepository.save(a);
				students.get(j).assignments.add(a);
				userRepository.save(students.get(j));
			}
		}
		catch (NullPointerException e) {
			return null;
		}
		return "complete";
	}
	
	// post create grading groups
	@PostMapping(path = "/64/{emailA}/{emailB}/{emailC}")
	public @ResponseBody void createGradingGroup(@PathVariable("emailA") String emailA, @PathVariable("emailB") String emailB, @PathVariable("emailC") String emailC) {
		try {
			User a = userRepository.findByEmail(emailA).get();
			User b = userRepository.findByEmail(emailB).get();
			User c = userRepository.findByEmail(emailC).get();
			Random rand = new Random(System.currentTimeMillis());
			int groupNum = 0;
			while (groupNum < 100000 && groupNum > 1000000) {
				groupNum = rand.nextInt();
			}
			a.gGroup = groupNum;
			b.gGroup = groupNum;
			c.gGroup = groupNum;
			userRepository.save(a);
			userRepository.save(b);
			userRepository.save(c);
		}
		catch (NullPointerException e) {
		}
	}
	
	// post grade for grading a group
	@PostMapping(path = "/60/{assName}/{groupNum}/{grade}")
	public @ResponseBody void addGradeForGroup(@PathVariable("assName") String assName, @PathVariable("groupNum") Integer groupNum, @PathVariable("grade") Integer grade) {	
		try {
			List<User> group = userRepository.findByGGroup(groupNum);
			for (int i = 0; i < group.size(); i++) {
				User user = group.get(i);
				Assignment a = assignmentRepository.findByAssignmentAndUserEmail(assName, user.email).get();
				user.assignments.remove(a);
				a.grade = grade;
				assignmentRepository.save(a);
				user.assignments.add(a);
				userRepository.save(user);
			}
		}
		catch (NullPointerException e) {
		}
	}
}
