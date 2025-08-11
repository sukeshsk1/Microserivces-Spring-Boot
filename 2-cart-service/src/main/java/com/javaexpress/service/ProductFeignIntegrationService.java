package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.feignClient.ProductFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductFeignIntegrationService {
	
	@Autowired
	private ProductFeignClient productFeignClient;
	public ProductResponseDTO fetchProduct(Long productId) {
		return productFeignClient.getProduct(productId);
	}

}
