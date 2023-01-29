package hu.bearmaster.springtutorial.theory.ioc.di.order;

import hu.bearmaster.springtutorial.theory.ioc.di.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final PaymentService paymentService;

    public OrderServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void submitOrder(Order order) {
        LOGGER.info("Initiating payment for order {}", order);

        boolean collectionSuccessful = paymentService.collectPayment(order.getPaymentDetails(), order.getTotalPrice());

        if (collectionSuccessful) {
            LOGGER.info("Processing order...");
            // a megrendelés feldolgozása
        } else {
            throw new IllegalStateException("Payment failed!");
        }

    }
}
