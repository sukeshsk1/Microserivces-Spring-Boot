package com.javaExpress.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaExpress.dto.OrderResponseDTO;

@FeignClient(name="order-service",path="/orders")
public interface OrderFeignClient {
	
	@GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Long orderId);

}
