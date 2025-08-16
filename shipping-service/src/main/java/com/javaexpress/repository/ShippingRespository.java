package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.model.Shipping;
@Repository
public interface ShippingRespository  extends JpaRepository<Shipping, Long>{

}
