package hu.bearmaster.springtutorial.theory.ioc.di;

import hu.bearmaster.springtutorial.theory.ioc.di.order.OrderService;
import hu.bearmaster.springtutorial.theory.ioc.di.order.OrderServiceImpl;
import hu.bearmaster.springtutorial.theory.ioc.di.payment.DummyPaymentServiceImpl;
import hu.bearmaster.springtutorial.theory.ioc.di.payment.PaymentService;

public class Context {

    private final PaymentService paymentService = new DummyPaymentServiceImpl();
    private final OrderService orderService = new OrderServiceImpl(paymentService);

    private static final Context context = new Context();

    private Context() {
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public static Context getInstance() {
        return context;
    }
}
