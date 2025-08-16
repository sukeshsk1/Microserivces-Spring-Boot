package com.javaExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaExpress.dto.PaymentRequestDTO;
import com.javaExpress.dto.PaymentResponseDTO;
import com.javaExpress.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO request) {
		
		log.info("PaymentController makepayment");
		return paymentService.processPayment(request);
	}
	
	@GetMapping("/{paymentId}")
	public PaymentResponseDTO getPayment(@PathVariable Long paymentId) {
		
		log.info("PaymentController getPaymet");
		return paymentService.getPaymentDetails(paymentId);
	}

}
