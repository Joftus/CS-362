package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Class;

@Repository
public interface ClassRepository extends CrudRepository<Class, Integer>{
	
	/*
	 * These are the different ways to search the SQL database for class objects
	 */
	
	Optional<Class> findById(Integer id);
	Optional<Class> findByName(String name);
	Optional<Class> findByNameAndSection(String name, Integer section);
}
