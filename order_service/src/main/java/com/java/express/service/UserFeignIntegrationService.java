package com.java.express.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.express.dto.UserDTO;
import com.java.express.feignclients.UserFeignClients;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserFeignIntegrationService {
	
	@Autowired
	private UserFeignClients userFeignClients;
	
	public UserDTO fetchUser(Long userId) {
		return userFeignClients.getUserById(userId);
	}

  
	/*
	 * @Autowired private RestTemplate restTempalte;
	 * 
	 * private static final String USER_SERVICE_URL =
	 * "http://localhost:8081/api/users/{userId}";
	 * 
	 * public UserDTO fetchUser(Long userId) { try { ResponseEntity<UserDTO>
	 * responseEntity = restTempalte.getForEntity(USER_SERVICE_URL,
	 * UserDTO.class,userId);
	 * 
	 * if(responseEntity.getStatusCode().is2xxSuccessful()) { UserDTO
	 * responseUserDto = responseEntity.getBody();
	 * log.info("userDto {}",responseUserDto.getEmail()); return responseUserDto; }
	 * else { throw new
	 * ResourceNotFoundException("Failed to fetch user Information"); }
	 * }catch(Exception ex) {
	 * log.error("Error occurred while fetching user from external service",ex);
	 * throw new ResourceNotFoundException(ex.getMessage()); } }
	 */
}
