package com.cts.fp.methodreferences;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@FunctionalInterface
interface InventoryProcessor {
    String process(boolean isStockAvailable);
}

class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}

class OrderService {
    public boolean isStockAvailable() {
        return true;
    }
}

class InventoryService {

    public void updateInventor(InventoryProcessor processor) {
        System.out.println(processor.process(true));
    }
}

public class BuiltinFunctionalInterface {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> adder = Calculator::add;
        System.out.println(adder.apply(10, 10));

        Calculator calculator = new Calculator();

        BiFunction<Integer, Integer, Integer> subtracter = (a, b) -> calculator.subtract(a, b);
        System.out.println(subtracter.apply(10, 2));
        BiFunction<Integer, Integer, Integer> subtracterMethodRef = calculator::subtract;
        System.out.println(subtracterMethodRef.apply(10, 2));

        OrderService orderService = new OrderService();
        Supplier<Boolean> orderSupplier = null;
        orderSupplier = () -> orderService.isStockAvailable();
        System.out.println(orderSupplier.get());
        orderSupplier = orderService::isStockAvailable;
        System.out.println(orderSupplier.get());

        InventoryService inventoryService = new InventoryService();
        inventoryService.updateInventor(res -> {
            return "Inventory processed" + res;
        });
        inventoryService.updateInventor(BuiltinFunctionalInterface::process);
    }
    private static String process(boolean res) {
        return "Inventory processed" + res;
    }
}
