package com.javaexpress.cart.dto;

import lombok.Data;

@Data
public class CartItemRequestDTO {
	private Long userId;
	private long productId;
	private Integer quantity;

}
