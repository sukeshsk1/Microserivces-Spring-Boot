package com.javaExpress.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PaymentRequestDTO {
	
	private long orderId;
	private Long userId;
	private BigDecimal amount;

}
