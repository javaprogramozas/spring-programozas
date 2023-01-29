package hu.bearmaster.springtutorial.theory.ioc.di;

import hu.bearmaster.springtutorial.theory.ioc.di.order.Order;
import hu.bearmaster.springtutorial.theory.ioc.di.order.OrderService;

/**
 * Dependency injection mint Inversion of control (IoC) megoldás
 * - egy külső objektum rakja össze az objektumgráfot (context)
 * - az egyes service osztályok nem ismerik a konkrét implementációt
 * - constructor vs setter injection
 */
public class Main {

    public static void main(String[] args) {
        Context context = Context.getInstance();
        OrderService orderService = context.getOrderService();
        orderService.submitOrder(new Order());
    }
}
