package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.HTTP.HeaderGenerator;
import com.user.entity.User;
import com.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/register")
public class RegisterController {
	 @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private HeaderGenerator headerGenerator;
	    
	    @PostMapping(value = "/registration")
	    public ResponseEntity<User> addUser(@RequestBody User user, HttpServletRequest request){
	    	if(user != null)
	    		try {
	    			userService.saveUser(user);
	    			return new ResponseEntity<User>(
	    					user,
	    					headerGenerator.getHeadersForSuccessPostMethod(request, user.getUserId()),
	    					HttpStatus.CREATED);
	    		}catch (Exception e) {
	    			e.printStackTrace();
	    			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	    }

}
