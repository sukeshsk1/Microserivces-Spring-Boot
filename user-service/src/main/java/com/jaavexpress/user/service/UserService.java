package com.jaavexpress.user.service;

import java.util.List;

import com.jaavexpress.user.dto.UserDTO;

public interface UserService {
	
	UserDTO save(UserDTO userDto);
	UserDTO findBy(Long userId);
	List<UserDTO> findAll();
	UserDTO update(Long userId,UserDTO userDto);
	UserDTO findByUsername(String username);


}
