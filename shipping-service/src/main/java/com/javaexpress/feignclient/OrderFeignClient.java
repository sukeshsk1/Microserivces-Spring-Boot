package com.javaexpress.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.OrderResponseDTO;

@FeignClient(name="order-service",path="/orders")
public interface OrderFeignClient {
	
	@GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Long orderId);

}
