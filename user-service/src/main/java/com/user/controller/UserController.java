package com.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.HTTP.HeaderGenerator;
import com.user.entity.User;
import com.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private HeaderGenerator headerGenerator;

	@Autowired
	private UserService userService;

	@GetMapping("/findAllUser")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (!users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);

	}

	@GetMapping("/getByUsername/{userName}")
	public ResponseEntity<User> getUserByName(@PathVariable("userName") String userName) {
		User user = userService.getUserByName(userName);
		if (user != null) {
			return new ResponseEntity<User>(user, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/userId/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<User>(user, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/saveUser")
	public ResponseEntity<User> addUser(@RequestBody User user, HttpServletRequest request) {
		if (user != null)
			try {
				userService.saveUser(user);
				return new ResponseEntity<User>(user,
						headerGenerator.getHeadersForSuccessPostMethod(request, user.getUserId()), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/username/{id}")
	public ResponseEntity<List<Object[]>> getUserNameById(@PathVariable("id") Long id) {
		List<Object[]> userNameById = userService.getUserNameById(id);
		if (userNameById != null) {
			return new ResponseEntity<List<Object[]>>(userNameById, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<List<Object[]>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}
	
	
	

}
