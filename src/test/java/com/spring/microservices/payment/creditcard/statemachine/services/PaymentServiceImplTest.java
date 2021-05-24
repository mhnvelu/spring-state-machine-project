package com.spring.microservices.payment.creditcard.statemachine.services;

import com.spring.microservices.payment.creditcard.statemachine.domain.Payment;
import com.spring.microservices.payment.creditcard.statemachine.domain.PaymentEvent;
import com.spring.microservices.payment.creditcard.statemachine.domain.PaymentState;
import com.spring.microservices.payment.creditcard.statemachine.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder().amount(new BigDecimal("10.99")).build();
    }

    @Transactional
    @Test
    public void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);
        StateMachine<PaymentState, PaymentEvent> stateMachine = paymentService.preAuth(savedPayment.getId());

        Payment preAuthorizedPayment = paymentRepository.getById(payment.getId());
        log.info("STATE : " + stateMachine.getState().getId());
        log.info("PRE_AUTH : " + preAuthorizedPayment);
    }

    @Transactional
    @Test
    public void preAuthApproved() {
        Payment savedPayment = paymentService.newPayment(payment);
        StateMachine<PaymentState, PaymentEvent> stateMachine = paymentService.preAuthApproved(savedPayment.getId());

        Payment preAuthorizedPayment = paymentRepository.getById(payment.getId());
        log.info("STATE : " + stateMachine.getState().getId());
        log.info("PRE_AUTH : " + preAuthorizedPayment);
    }


    @Transactional
    @Test
    public void authorizePayment() {
        Payment savedPayment = paymentService.newPayment(payment);
        StateMachine<PaymentState, PaymentEvent> stateMachine = paymentService.preAuthApproved(savedPayment.getId());
        stateMachine = paymentService.authorizePayment(savedPayment.getId());

        Payment authorizedPayment = paymentRepository.getById(payment.getId());
        log.info("STATE : " + stateMachine.getState().getId());
        log.info("AUTH : " + authorizedPayment);
    }

}