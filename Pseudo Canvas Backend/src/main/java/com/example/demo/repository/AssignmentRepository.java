package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Assignment;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Integer>{

	/*
	 * These are the different ways to search the SQL database for assignment objects
	 */
	
	Optional<Assignment> findByAssignment(String assignment);
	List<Assignment> findByUserEmail(String userEmail);
	List<Assignment> findByClassName(String className);
	List<Assignment> findByClassNameAndAssignment(String className, String assignment);
	Optional<Assignment> findByAssignmentAndUserEmail(String assignment, String userEmail);
}
