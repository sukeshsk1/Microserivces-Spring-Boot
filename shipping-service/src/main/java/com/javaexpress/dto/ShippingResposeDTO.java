package com.javaexpress.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ShippingResposeDTO {
	private Long orderId;
	private String shippingMethod ;
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
	private String status;
	private String carrier;
	
	private OrderResponseDTO orderResponseDTO;

	

}
