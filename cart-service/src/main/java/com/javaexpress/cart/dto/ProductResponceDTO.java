package com.javaexpress.cart.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponceDTO {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
	private String status;
	private String brand;


}
