package hu.bearmaster.springtutorial.theory.oop.order;

import hu.bearmaster.springtutorial.theory.oop.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final PaymentService paymentService;

    public OrderService() {
        this.paymentService = new PaymentService();
    }

    public void submitOrder(Order order) {
        LOGGER.info("Initiating payment for order {}", order);

        boolean collectionSuccessful = paymentService.collectPayment(order.getPaymentDetails(), order.getTotalPrice());

        if (collectionSuccessful) {
            LOGGER.info("Payment successful");
            // a megrendelés feldolgozása
        } else {
            throw new IllegalStateException("Payment failed!");
        }

    }
}
