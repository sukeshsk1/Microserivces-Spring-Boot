package com.javaexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRequestDTO {
	
	private Long orderId;
	private String shippingMethod;
	private String carrier;

}
