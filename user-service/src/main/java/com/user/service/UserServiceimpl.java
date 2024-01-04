package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.entity.UserRole;
import com.user.repository.UserRepository;
import com.user.repository.UserRoleRpository;
@Service
public class UserServiceimpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRpository userRoleRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

	@Override
	public User saveUser(User user) {
		 user.setActive(1);
	        UserRole role = userRoleRepository.findUserRoleByRoleName("ROLE_USER");
	        user.setRole(role);
	        return userRepository.save(user);
		
	}

	@Override
	public List<Object[]> getUserNameById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findUsernameByUserId(id);
	}

	

	

}
