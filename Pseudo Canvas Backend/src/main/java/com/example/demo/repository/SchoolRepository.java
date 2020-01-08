package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.School;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer> {
	
	/*
	 * These are the different ways to search the SQL database for school objects
	 */
	
	Optional<School> findById(Integer id);
	Optional<School> findByName(String Name);
}
