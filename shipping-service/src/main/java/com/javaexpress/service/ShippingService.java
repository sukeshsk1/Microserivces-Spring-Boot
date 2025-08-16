package com.javaexpress.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaexpress.dto.OrderResponseDTO;
import com.javaexpress.dto.ShippingRequestDTO;
import com.javaexpress.dto.ShippingResposeDTO;
import com.javaexpress.model.Shipping;
import com.javaexpress.repository.ShippingRespository;

import feign.Response;
@Service
public class ShippingService {
	@Autowired
	private ShippingRespository shippingRespository;
	
	@Autowired
	private OrderIntegrationService orderIntegrationService;
	
	public ShippingResposeDTO shipOrder(@RequestBody ShippingRequestDTO request) {
	   // OrderResponseDTO orderResponseDTO = orderIntegrationService.getOrder(request.getOrderId());

	    // Set the orderId from the fetched order response
	 
		
	OrderResponseDTO orderDetails=	orderIntegrationService.getOrder(request.getOrderId());
	Shipping shipping= new Shipping();
	BeanUtils.copyProperties(request, orderDetails);
	shipping.setStatus("SHIPPED");
	shipping.setShippedAt(LocalDateTime.now());
	shipping.setShippingMethod(request.getShippingMethod());
	shipping.setCarrier(request.getCarrier());
	shipping.setOrderId(request.getOrderId());
	shipping.setDeliveryDate(LocalDateTime.now());//delivery date head coding here 
	Shipping dbShipping=shippingRespository.save(shipping);
	
	return mapToDTO(dbShipping,orderDetails);
	
	
	}

	private ShippingResposeDTO mapToDTO(Shipping dbShipping,OrderResponseDTO orderResponse) {
	
		


		ShippingResposeDTO reponse=new ShippingResposeDTO();
		BeanUtils.copyProperties(dbShipping, reponse);
		
		reponse.setOrderResponseDTO(orderResponse);
		return reponse;
	}

}
