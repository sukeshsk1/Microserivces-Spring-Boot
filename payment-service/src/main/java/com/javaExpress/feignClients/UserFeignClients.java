package com.javaExpress.feignClients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaExpress.dto.UserDTO;
@FeignClient(name="user-service",path="/api/users") // http://localhost:8063//api/users/{userId},http://localhost:8065,http://localhost:8069
@LoadBalancerClient
public interface UserFeignClients {
	
	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable Long userId);

}
