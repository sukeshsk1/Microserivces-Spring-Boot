package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.ShippingRequestDTO;
import com.javaexpress.dto.ShippingResposeDTO;
import com.javaexpress.service.ShippingService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/shipping")
@Slf4j
public class ShippingController {
	
	@Autowired
	private ShippingService shippingService;
	@GetMapping
	public ShippingResposeDTO shipOrder(@RequestBody ShippingRequestDTO request) {
		return shippingService.shipOrder(request);
	}

}
