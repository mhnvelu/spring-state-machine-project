package com.spring.microservices.payment.creditcard.statemachine.services;

import com.spring.microservices.payment.creditcard.statemachine.domain.Payment;
import com.spring.microservices.payment.creditcard.statemachine.domain.PaymentEvent;
import com.spring.microservices.payment.creditcard.statemachine.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {

    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> declinePaymentAuthorization(Long paymentId);

}
