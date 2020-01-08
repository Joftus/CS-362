package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/*
	 * These are the different ways to search the SQL database for user objects
	 */
	
	Optional<User> findById(Integer id);
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);
	Optional<User> findByEmailAndType(String email, String Type);
	List<User> findByName(String name);
	List<User> findByGGroup(Integer gGroup);
	List<User> findByCGroup(Integer cGroup);
}
