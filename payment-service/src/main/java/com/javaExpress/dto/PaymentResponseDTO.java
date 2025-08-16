package com.javaExpress.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PaymentResponseDTO {
	 private Long paymentId;
	private BigDecimal amount;
	private String status;//Success,Pending,Failed
	private long orderId;
	private Long userId;
	private LocalDateTime payDatetime=LocalDateTime.now();
	private UserDTO userDTO;
	private OrderResponseDTO orderResponseDTO;
	

}
