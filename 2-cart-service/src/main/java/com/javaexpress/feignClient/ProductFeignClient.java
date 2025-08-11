package com.javaexpress.feignClient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.ProductResponseDTO;
@FeignClient(name="product-service",path="/products") // http://localhost:8063//api/users/{userId},http://localhost:8065,http://localhost:8069
@LoadBalancerClient
public interface ProductFeignClient {
	
	@GetMapping("/{productId}")
	public ProductResponseDTO getProduct(@PathVariable Long productId);

}
