package hu.bearmaster.springtutorial.theory.ioc.sl.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class DummyPaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyPaymentServiceImpl.class);

    @Override
    public boolean collectPayment(String paymentDetails, BigDecimal amount) {
        LOGGER.info("Dummy payment is successful!");
        return true;
    }
}
