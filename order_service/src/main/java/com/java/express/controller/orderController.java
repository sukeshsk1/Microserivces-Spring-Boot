package com.java.express.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.express.dto.OrderResponseDTO;
import com.java.express.dto.PlaceOrderRequestDTO;
import com.java.express.service.OrderService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/orders")
@Slf4j
public class orderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO request) {
		return orderService.placeOrder(request);
	}
	
	@GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Long orderId) {
    	log.info("OrderController getOrder");
        return orderService.getOrderById(orderId);
    }

}
