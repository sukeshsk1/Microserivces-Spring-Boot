package com.zosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.exception.UserException;
import com.zosh.model.User;
import com.zosh.service.UserService;

import jakarta.validation.Valid;


@RestController

public class userController {
	@Autowired
	private  UserService userService;
	
	
	
	
	@GetMapping("/api/users")
	public ResponseEntity<List<User>> getUsers(
	) throws UserException {
		List<User> users = userService.getAllUsers();

		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	@PostMapping("/api/users")
	public  ResponseEntity<User> createUser(@RequestBody @Valid User user){
		User createdUser= userService.createUser(user);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		
		
	}
	@GetMapping("/api/users/{userEmail}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("userEmail") String email)throws Exception{
		User user=userService.getUserByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/api/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long id)throws Exception{
		User user=userService.getUserById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	@PutMapping("/api/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id)throws Exception{
		User updatedUser= userService.updateUser(id, user);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	@DeleteMapping("api/users/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id)throws Exception{
		
		userService.deleteUser(id);
		return new ResponseEntity<>("User Deleted",HttpStatus.ACCEPTED);
		
	}
	
	
}
