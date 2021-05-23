package com.spring.microservices.payment.creditcard.statemachine.repository;

import com.spring.microservices.payment.creditcard.statemachine.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
