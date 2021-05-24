package com.spring.microservices.payment.creditcard.statemachine.config.actions;

import com.spring.microservices.payment.creditcard.statemachine.domain.PaymentEvent;
import com.spring.microservices.payment.creditcard.statemachine.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthApprovedAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> context) {
        log.info("Sending Notification of AuthApproved...");
    }
}
