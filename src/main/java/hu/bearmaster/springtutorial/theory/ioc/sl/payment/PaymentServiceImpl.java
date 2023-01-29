package hu.bearmaster.springtutorial.theory.ioc.sl.payment;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean collectPayment(String paymentDetails, BigDecimal amount) {
        return false;
    }
}
