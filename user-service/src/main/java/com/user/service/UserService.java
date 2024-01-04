package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(Long id);

	User getUserByName(String userName);

	User saveUser(User user);
	
	List<Object[]> getUserNameById(Long id);
	
	
	
	
}
