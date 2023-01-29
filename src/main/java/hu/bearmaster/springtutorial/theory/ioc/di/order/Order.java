package hu.bearmaster.springtutorial.theory.ioc.di.order;

import java.math.BigDecimal;

public class Order {

    private String paymentDetails;

    private BigDecimal totalPrice;

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
