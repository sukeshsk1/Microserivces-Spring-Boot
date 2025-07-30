package com.zosh.service;

import java.util.List;

import com.zosh.model.User;

import com.zosh.exception.UserException;

public interface UserService {
	
	User createUser(User user);///
	User getUserById(Long id) throws UserException;///
	
	List<User> getAllUsers();///
	void deleteUser(Long id) throws UserException;
	User updateUser(Long id, User user) throws UserException;
	
	
	User getUserByEmail(String email) throws UserException;//
	
	///User getUserFromJwtToken(String jwt) throws Exception;
	
	
	
}
