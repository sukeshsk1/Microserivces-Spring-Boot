package com.java.express.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.express.dto.CartItemResponseDTO;
import com.java.express.feignclients.CartFeignClient;

@Service
public class CartIntegrationService {
	
	@Autowired
	private CartFeignClient cartFeignClient;
	
	public List<CartItemResponseDTO> getCart(Long userId) {
		return cartFeignClient.getCartByUser(userId);
	}
	
	
	

}
