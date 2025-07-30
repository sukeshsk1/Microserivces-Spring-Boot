package com.zosh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.exception.UserException;
import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {

		return userRepository.save(user);
	}
	/*
	 * @Override public User getUserById(Long id) throws UserException { return
	 * userRepository.findById(id).orElse(null); }
	 */

	@Override
	public User getUserById(Long id) throws UserException {

		Optional<User> otp = userRepository.findById(id);
		if (otp.isPresent()) {
			return otp.get();
		}

		throw new UserException("User not foumd");
	}

	@Override
	public void deleteUser(Long id) throws UserException {
		Optional<User> otp = userRepository.findById(id);
		if (otp.isEmpty()) {
			throw new UserException("user not exist with id" + id);
		}
		userRepository.deleteById(id);

	}

	@Override
	public User updateUser(Long id, User user) throws UserException {
		Optional<User> otp = userRepository.findById(id);
		if (otp.isEmpty()) {
			throw new UserException("user not exist with id" + id);
		}
		User existingUser = otp.get();
		existingUser.setUsername(user.getUsername());
		existingUser.setEmail(user.getEmail());
		existingUser.setRole(user.getRole());

		return userRepository.save(existingUser);

	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("User not found with email: " + email);
		}
		return user;
	}

}
