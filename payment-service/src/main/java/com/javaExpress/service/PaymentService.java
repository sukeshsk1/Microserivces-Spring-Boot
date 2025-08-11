package com.javaExpress.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaExpress.dto.PaymentRequestDTO;
import com.javaExpress.dto.PaymentResponseDTO;
import com.javaExpress.model.Payment;
import com.javaExpress.respository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
		Payment payment= new Payment();
		BeanUtils.copyProperties(dto, payment);
		
		payment.setStatus("SUCCESS");
		
		
		Payment dbPayment= paymentRepository.save(payment);
		return maptoDTO(dbPayment);
		
		
		
	
	}

	private PaymentResponseDTO maptoDTO(Payment dbPayment) {
		
		PaymentResponseDTO response= new PaymentResponseDTO();
		BeanUtils.copyProperties(dbPayment, response);
		
		return response;
	}

}
