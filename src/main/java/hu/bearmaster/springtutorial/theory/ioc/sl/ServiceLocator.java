package hu.bearmaster.springtutorial.theory.ioc.sl;

import hu.bearmaster.springtutorial.theory.ioc.sl.order.OrderService;
import hu.bearmaster.springtutorial.theory.ioc.sl.order.OrderServiceImpl;
import hu.bearmaster.springtutorial.theory.ioc.sl.payment.DummyPaymentServiceImpl;
import hu.bearmaster.springtutorial.theory.ioc.sl.payment.PaymentService;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private static final ServiceLocator instance = new ServiceLocator();

    private final Map<Class<?>, Object> serviceCache = new HashMap<>();

    private ServiceLocator() {}

    public <T> T lookup(Class<T> clazz) {
        if (!serviceCache.containsKey(clazz)) {
            T service = createService(clazz);
            serviceCache.put(clazz, service);
        }

        return (T) serviceCache.get(clazz);
    }

    private <T> T createService(Class<T> clazz) {
        if (clazz == OrderService.class) {
            return (T) new OrderServiceImpl();
        }
        if (clazz == PaymentService.class) {
            return (T) new DummyPaymentServiceImpl();
        }
        throw new IllegalArgumentException("Unknown service class: " + clazz.getName());
    }

    public static ServiceLocator getInstance() {
        return instance;
    }
}
