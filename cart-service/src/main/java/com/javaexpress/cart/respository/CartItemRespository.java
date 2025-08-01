package com.javaexpress.cart.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.cart.model.CartItem;
@Repository
public interface CartItemRespository  extends JpaRepository<CartItem, Long>{
}

