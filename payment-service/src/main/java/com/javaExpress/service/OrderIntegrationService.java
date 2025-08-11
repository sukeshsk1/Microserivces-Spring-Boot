package com.javaExpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaExpress.dto.OrderResponseDTO;
import com.javaExpress.feignClients.OrderFeignClient;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class OrderIntegrationService {
	
	@Autowired
	
	private OrderFeignClient orderFeignClient;
    public OrderResponseDTO getOrder( Long orderId) {
    	return orderFeignClient.getOrder(orderId);
    }
    
    

}
