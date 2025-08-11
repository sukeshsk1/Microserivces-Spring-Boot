package com.java.express.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.express.model.Order;

public interface OrderRepository  extends JpaRepository<Order, Long>{

}
