package com.javaexpress.cart.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {
	
	private Long id;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private ProductResponceDTO product;
	
	private UserDTO user;
	
	
	

}
