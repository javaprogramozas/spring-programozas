package hu.bearmaster.springtutorial.theory.ioc.di.payment;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean collectPayment(String paymentDetails, BigDecimal amount) {
        return false;
    }
}
