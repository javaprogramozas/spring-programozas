package hu.bearmaster.springtutorial.theory.ioc.di.payment;

import java.math.BigDecimal;

public interface PaymentService {

    boolean collectPayment(String paymentDetails, BigDecimal amount);

}
