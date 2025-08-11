package com.java.express.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.express.dto.ProductResponceDTO;
import com.java.express.feignclients.ProductFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductFeignIntegrationService {
	
	@Autowired
	private ProductFeignClient productFeignClient;
	public ProductResponceDTO fetchProduct(Long productId) {
		return productFeignClient.getProduct(productId);
	}

	/*
	 * @Autowired private RestTemplate restTempalte;
	 * 
	 * private static final String PRODUCT_SERVICE_URL =
	 * "http://localhost:8082/products/{productId}";
	 * 
	 * public ProductResponceDTO fetchProduct(Long productId) { try {
	 * ResponseEntity<ProductResponceDTO> responseEntity =
	 * restTempalte.getForEntity(PRODUCT_SERVICE_URL,
	 * ProductResponceDTO.class,productId);
	 * 
	 * if(responseEntity.getStatusCode().is2xxSuccessful()) { ProductResponceDTO
	 * response = responseEntity.getBody(); return response; } else { throw new
	 * ResourceNotFoundException("Failed to fetch user Information"); }
	 * }catch(Exception ex) {
	 * log.error("Error occurred while fetching user from external service",ex);
	 * throw new ResourceNotFoundException(ex.getMessage()); } }
	 */
}
