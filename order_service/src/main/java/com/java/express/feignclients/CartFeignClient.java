package com.java.express.feignclients;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.express.dto.CartItemResponseDTO;

@FeignClient(name="cart-service",path="/cart") // http://localhost:8063/{userId},http://localhost:8065,http://localhost:8069
@LoadBalancerClient
public interface CartFeignClient {

	@GetMapping("/{userId}")
	public List<CartItemResponseDTO> getCartByUser(@PathVariable Long userId);
}