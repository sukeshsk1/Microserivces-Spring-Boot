package com.javaexpress.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="shipping")
public class Shipping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;
	private String shippingMethod ;//standard, next-day, oneday
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
	
	private String status;//SHIPPED,IN_TRANSIT,DELIVERED
	private String carrier;//FEDX,ups,SHAOW FX,DKL
	
	

}
