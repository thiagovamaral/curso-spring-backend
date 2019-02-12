package com.thiago.helpdesk.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.thiago.helpdesk.entity.User;

@Component
public interface UserService {

	User findByEmail(String email);
	
	User createOrUpdate(User user);
	
	User findById(String id);
	
	void delete(String id);
	
	Page<User> findAll(int page, int count);
	
}
