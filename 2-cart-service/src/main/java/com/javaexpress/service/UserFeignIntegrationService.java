package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.UserDto;
import com.javaexpress.feignClient.UserFeignClients;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserFeignIntegrationService {
	@Autowired
	private UserFeignClients userFeignClients;
	
	public UserDto fetchUser(Long userId) {
		return userFeignClients.fetchUser(userId);
	}

	
}
