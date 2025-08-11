package com.java.express.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.express.dto.CartItemResponseDTO;
import com.java.express.dto.OrderItemResponceDTO;
import com.java.express.dto.OrderResponseDTO;
import com.java.express.dto.PlaceOrderRequestDTO;
import com.java.express.dto.ProductResponceDTO;
import com.java.express.dto.UserDTO;
import com.java.express.exception.ResourceNotFoundException;
import com.java.express.model.Order;
import com.java.express.model.OrderItem;
import com.java.express.repository.OrderRepository;
@Service
public class OrderService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	 @Autowired
	 private CartIntegrationService cartIntegrationService;
	 @Autowired
	 private UserFeignIntegrationService userFeignIntegrationService;
	 @Autowired
	 private ProductFeignIntegrationService productFeignIntegrationService;
	 
	 public OrderResponseDTO placeOrder(PlaceOrderRequestDTO request) {
		 List<CartItemResponseDTO> cartItems = cartIntegrationService.getCart(request.getUserId());
		 
		 if(cartItems.isEmpty()) {
			 throw new ResourceNotFoundException("CartItems are empty");
		 }
		 
		BigDecimal totalPrice= calculateTotalPrice(cartItems);
		
		List<OrderItem> orderItems = buildOrderItems(cartItems);
		Order order = createOrder(request.getUserId(),totalPrice,orderItems);
		Order dbOrder = orderRepository.save(order);
		return mapToOrderDTO(dbOrder);
		//createOrder(request.getUserId(),totalPrice)
		
	 }
	 
	 private OrderResponseDTO mapToOrderDTO(Order dbOrder) {OrderResponseDTO response  = new OrderResponseDTO();
		BeanUtils.copyProperties(dbOrder, response,"items"); // Exclude items to map manually
		response.setOrderId(dbOrder.getId());
		UserDTO fetchUser = userFeignIntegrationService.fetchUser(dbOrder.getUserId());
		response.setUser(fetchUser);
		List<OrderItemResponceDTO> orderItemsResponses = dbOrder.getItems().stream().map(item -> {
			OrderItemResponceDTO orderItemResponseDto = new OrderItemResponceDTO();
			BeanUtils.copyProperties(item, orderItemResponseDto);
			ProductResponceDTO product = productFeignIntegrationService.fetchProduct(item.getProductId());
			orderItemResponseDto.setProduct(product);
			return orderItemResponseDto;
			
		}).collect(Collectors.toList());
		
		response.setItems(orderItemsResponses);
	
		return response;
	}
	 private Order createOrder(Long userId, BigDecimal totalPrice, List<OrderItem> orderItems) {
		 Order order = new Order();
			order.setUserId(userId);
			order.setTotalPrice(totalPrice);
			order.setStatus("PLACED");
			
			for(OrderItem item : orderItems) {
				item.setOrder(order); // Bi-directional relationship
			}
			order.setItems(orderItems);
			return order;
	}
	 private List<OrderItem> buildOrderItems(List<CartItemResponseDTO> cartItems) {
			List<OrderItem> orderItems = new ArrayList<>();
			
			for(CartItemResponseDTO item : cartItems) {
				BigDecimal productPrice = item.getProduct().getPrice();
				OrderItem orderItem = new OrderItem();
				orderItem.setProductId(item.getProductId());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(productPrice);
				orderItems.add(orderItem);
			}
			
			return orderItems;
		}

	 private BigDecimal calculateTotalPrice(List<CartItemResponseDTO> cartItems) {
		 
		 BigDecimal total= BigDecimal.ZERO;
		 for(CartItemResponseDTO item:cartItems) {
			 BigDecimal productPrice=item.getProduct().getPrice();
		/*	 
		Integer availbleInvertory=	 item.getProduct().getStock();
		if(availbleInvertory>=item.getQuantity()) {
			
		}*/
			total= total.add(productPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
		 }
		return total;
	 }
	 public OrderResponseDTO getOrderById(Long orderId) {
		 return orderRepository.findById(orderId)
					.map(this::mapToOrderDTO)
					.orElseThrow(() -> new RuntimeException("Order Id not found in db"));
		}

}
