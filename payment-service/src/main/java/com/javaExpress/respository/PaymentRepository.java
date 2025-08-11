package com.javaExpress.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaExpress.model.Payment;
@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Long>{

	Payment save(Payment payment);

}
