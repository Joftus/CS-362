package com.example.demo.controller.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.School;
import com.example.demo.repository.SchoolRepository;

@RestController
public class SchoolController {

	@Autowired
	SchoolRepository schoolRepository;

	
	
//-------------- Get Mapping -------------------------------------------------------------------------------
	
	// get all schools
	@GetMapping(path = "/40")
    public @ResponseBody List<School> getAllSchools() {
		try {	
			return (List<School>) schoolRepository.findAll();
		}
		catch (NullPointerException e) {
			return null;
		}
    }
	
	// get school
	@GetMapping(path = "/41/{id}")
    public @ResponseBody School getSchoolByID(@PathVariable("id") Integer id) {
		try {	
			return schoolRepository.findById(id).get();
		}
		catch (NullPointerException e) {
			return null;
		}
    }
	
	// get school
	@GetMapping(path = "/42/{name}") 
	public @ResponseBody School getSchoolByName(@PathVariable("name") String name){
		try {	
			return schoolRepository.findByName(name).get();
		}
		catch (NullPointerException e) {
			return null;
		}
	}
    
	
	
//-------------- Post Mapping ------------------------------------------------------------------------------
	
	// create a school
	@PostMapping(path = "/43/{name}")
	public @ResponseBody School createNewSchool(@PathVariable("name") String name) {
		try {	
			return schoolRepository.save(new School(name));
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete a school
	@PostMapping(path = "/44/{id}")
	public @ResponseBody School deleteSchoolByID(@PathVariable("id") Integer id) {
		try {
			School remove = schoolRepository.findById(id).get();
			schoolRepository.delete(remove);
			return remove;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	// delete all schools
	@PostMapping(path = "/45")
	public @ResponseBody String deleteAllSchools() {
		try {	
			schoolRepository.deleteAll();
			return "All Schools Were Deleted!";
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	
	
//-------------- Variable Manipulation ---------------------------------------------------------------------
	
}