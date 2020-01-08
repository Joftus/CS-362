package com.example.demo.controller.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;

@RestController
public class ClassController {

	@Autowired
	ClassRepository classRepository;

	
	
//-------------- Get Mapping -------------------------------------------------------------------------------
	
	// get all classes
	@GetMapping(path = "/32")
	public @ResponseBody List<Class> getAllClasses() {
		try {	
			return (List<Class>) classRepository.findAll();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get a class
	@GetMapping(path = "/33/{id}")
	public @ResponseBody Class getAClassByID(@PathVariable("id") Integer id) {
		try {	
			return classRepository.findById(id).get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// get a class
	@GetMapping(path = "/34/{name}")
	public @ResponseBody Class getClassByName(@PathVariable("name") String name) {
		try {
			return classRepository.findByName(name).get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	
	
//-------------- Post Mapping ------------------------------------------------------------------------------
	
	// create new class
	@PostMapping(path = "/35/{name}")
	public @ResponseBody Class createNewClass(@PathVariable("name") String name) {
		try {
			return classRepository.save(new Class(name));
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete a class
	@PostMapping(path = "/36/{id}")
	public @ResponseBody Class deleteAClass(@PathVariable("classId") Integer id) {
		try {	
			Class remove = classRepository.findById(id).get();
			classRepository.delete(remove);
			return remove;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete all classes
	@PostMapping(path = "/37")
	public @ResponseBody String deleteAllClasses() {
		try {	
			classRepository.deleteAll();
			return "All Classes Were Deleted!";
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	
	
//-------------- Variable Manipulation ---------------------------------------------------------------------
	
}