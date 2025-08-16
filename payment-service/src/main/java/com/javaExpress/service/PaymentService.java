package com.javaExpress.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaExpress.dto.OrderResponseDTO;
import com.javaExpress.dto.PaymentRequestDTO;
import com.javaExpress.dto.PaymentResponseDTO;
import com.javaExpress.exception.ResourceNotFoundException;
import com.javaExpress.model.Payment;
import com.javaExpress.respository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderIntegrationService orderIntegrationService;

	@Autowired
	private UserFeignIntegrationService userFeignIntegrationService;

	public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
		OrderResponseDTO orderDetals = orderIntegrationService.getOrder(dto.getOrderId());
		if (orderDetals != null) {
			Payment payment = new Payment();
			BeanUtils.copyProperties(dto, payment);

			payment.setStatus("SUCCESS");

			Payment dbPayment = paymentRepository.save(payment);
			return maptoDTO(dbPayment, orderDetals);

		} else {
			throw new RuntimeException("Order is not found in db");
		}

	}

	private PaymentResponseDTO maptoDTO(Payment dbPayment, OrderResponseDTO orderDetals) {

		//UserDTO userDTO = userFeignIntegrationService.fetchUser(dbPayment.getUserId());

		PaymentResponseDTO response = new PaymentResponseDTO();
		BeanUtils.copyProperties(dbPayment, response);
		response.setPaymentId(dbPayment.getId());
		///response.setUserDTO(userDTO);
		response.setOrderResponseDTO(orderDetals);

		return response;
	}

	public PaymentResponseDTO getPaymentDetails(Long paymentId) {

		Optional<Payment> payment = paymentRepository.findById(paymentId);

		if (payment.isPresent()) {
			Payment dbPayment = payment.get();
			OrderResponseDTO orderDetals = orderIntegrationService.getOrder(dbPayment.getOrderId());
			return maptoDTO(dbPayment, orderDetals);

		} else {
			throw new ResourceNotFoundException("Payment id not found in db");
		}
	}

}
