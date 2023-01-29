package hu.bearmaster.springtutorial.theory.ioc.sl;

import hu.bearmaster.springtutorial.theory.ioc.sl.order.Order;
import hu.bearmaster.springtutorial.theory.ioc.sl.order.OrderService;
import hu.bearmaster.springtutorial.theory.ioc.sl.order.OrderServiceImpl;

/**
 * Service locator mint Inversion of control (IoC) megoldás
 * - egy külső objektumtól lehet beszerezni a külső függőségeket
 * - az egyes service osztályok nem ismerik a konkrét implementációt
 * - egy közös pont van, a service locator
 */
public class Main {

    public static void main(String[] args) {
        OrderService orderService = ServiceLocator.getInstance().lookup(OrderService.class);
        orderService.submitOrder(new Order());
    }

}
