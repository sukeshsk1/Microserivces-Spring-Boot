package com.javaexpress.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemRequestDTO;
import com.javaexpress.dto.CartItemResponseDTO;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.model.CartItem;
import com.javaexpress.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	//@Autowired
	//private UserRestIntegrationService userRestIntegrationService;
	
	//@Autowired
	//private ProductRestIntegrationService productRestIntegrationService;
	
	@Autowired
	private ProductFeignIntegrationService productFeignIntegrationService;
	
	@Autowired
	private UserFeignIntegrationService userFeignIntegrationService;
	
	public CartItemResponseDTO addToCart(CartItemRequestDTO request) {
		Long userId = request.getUserId();
		Long productId = request.getProductId();
		
		UserDto userDto = userFeignIntegrationService.fetchUser(userId);
		ProductResponseDTO product = productFeignIntegrationService.fetchProduct(productId);
		// TODO : students - PRODUCT 
		
		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(request, cartItem);
		CartItem dbCartItem = cartItemRepository.save(cartItem);
		
		return mapToDto(dbCartItem, userDto,product);
	}
	
	public CartItemResponseDTO mapToDto(CartItem dbCartItem,UserDto user,ProductResponseDTO product) {
		CartItemResponseDTO response = new CartItemResponseDTO();
		BeanUtils.copyProperties(dbCartItem, response);
		response.setUser(user);
		response.setProduct(product);
		return response;
	}
	
	public CartItemResponseDTO updateQuantity(CartItemRequestDTO request) {
		UserDto userDto = userFeignIntegrationService.fetchUser(request.getUserId());
		ProductResponseDTO product = productFeignIntegrationService.fetchProduct(request.getProductId());
		
		CartItem cartItem = cartItemRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId());
		cartItem.setQuantity(request.getQuantity());
		CartItem dbCartItem = cartItemRepository.save(cartItem);
		return mapToDto(dbCartItem,userDto,product);
	}
	
	public List<CartItemResponseDTO> getUserCart(Long userId) {
		UserDto userDto = userFeignIntegrationService.fetchUser(userId);
		return cartItemRepository.findByUserId(userId).stream()
					.map(cart -> {
						ProductResponseDTO product = productFeignIntegrationService.fetchProduct(cart.getProductId());
						return mapToDto(cart,userDto,product);
					})
					.collect(Collectors.toList());
	}
	
	@Transactional
	public void removeItem(Long userId,Long productId) {
		cartItemRepository.deleteByUserIdAndProductId(userId, productId);
	}
	
}