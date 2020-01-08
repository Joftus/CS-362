package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{

	/*
	 * These are the different ways to search the SQL database for message objects
	 */
	
	Optional<Message> findById(Integer id);
	Optional<Message> findByMsg(String msg);
	List<Message> findByToEmailAndFromEmail(String to, String from);
	List<Message> findByToEmailOrFromEmail(String to, String from);
}
