package hu.bearmaster.springtutorial.theory.ioc.sl.order;

import hu.bearmaster.springtutorial.theory.ioc.sl.ServiceLocator;
import hu.bearmaster.springtutorial.theory.ioc.sl.payment.PaymentService;
import hu.bearmaster.springtutorial.theory.ioc.sl.payment.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final PaymentService paymentService;

    public OrderServiceImpl() {
        this.paymentService = ServiceLocator.getInstance().lookup(PaymentService.class);
    }

    @Override
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
