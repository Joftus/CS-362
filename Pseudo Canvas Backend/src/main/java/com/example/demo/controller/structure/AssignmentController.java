package com.example.demo.controller.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Assignment;
import com.example.demo.model.User;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.UserRepository;

@RestController
public class AssignmentController {
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClassRepository classRepository;
	
//-------------- Get Mapping -------------------------------------------------------------------------------
	// get all grades
	@GetMapping(path = "/38")
	public @ResponseBody List<Assignment> getAllAssignments(){
		try {
			return (List<Assignment>) assignmentRepository.findAll();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get a users grades
	@GetMapping(path = "/39/{email}")
	public @ResponseBody List<Assignment> getAssignment(@PathVariable("email") String email){
		try {
			return assignmentRepository.findByUserEmail(userRepository.findByEmail(email).get().email);
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get a classes grades
	@GetMapping(path = "/58/{className}")
	public @ResponseBody List<Assignment> getClassesAssignmetns(@PathVariable("className") String className){
		try {
			return assignmentRepository.findByClassName(className);
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get a classes grades on an assignment
	@GetMapping(path = "/59/{className}/{assignName}")
	public @ResponseBody List<Assignment> getClassGradesOnAssignment(@PathVariable("className") String className, @PathVariable("assignName") String assignName){ 
		try {
			return assignmentRepository.findByClassNameAndAssignment(className, assignName);
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	
	
//-------------- Post Mapping ------------------------------------------------------------------------------

	// curve an assignment
	@PostMapping(path = "/65/{className}/{assName}/{curve}")
	public @ResponseBody void curveAssignmentScale(@PathVariable("className") String className, @PathVariable("assName") String assName, @PathVariable("curve") Integer curve) {
		try {
			List<Assignment> aList = assignmentRepository.findByClassNameAndAssignment(className, assName);
			for (int i = 0; i < aList.size(); i++) {
				User user = userRepository.findByEmail(aList.get(i).userEmail).get();
				Assignment a = aList.get(i);
				user.assignments.remove(a);
				if (a.grade + curve < 100) a.grade += curve;
				else a.grade = 100;
				assignmentRepository.save(a);
				user.assignments.add(a);
				userRepository.save(user);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
