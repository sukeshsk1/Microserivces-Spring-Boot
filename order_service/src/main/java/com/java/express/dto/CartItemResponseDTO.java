package com.java.express.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDTO {

	private Long id;
	private Long userId;
	private Long productId;
	private Integer quantity;
	private ProductResponceDTO product;
	private UserDTO user;
}
