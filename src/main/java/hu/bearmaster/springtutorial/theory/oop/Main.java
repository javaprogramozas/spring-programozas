package hu.bearmaster.springtutorial.theory.oop;

import hu.bearmaster.springtutorial.theory.oop.order.Order;
import hu.bearmaster.springtutorial.theory.oop.order.OrderService;

/**
 * Klasszikus megoldás, minden objektum saját maga felelős a "függőségeiért"
 */
public class Main {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.submitOrder(new Order());
    }

}
