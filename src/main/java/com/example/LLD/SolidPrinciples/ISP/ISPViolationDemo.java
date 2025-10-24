package com.example.LLD.SolidPrinciples.ISP;

public class ISPViolationDemo {
    public static void main(String[] args) {
        System.out.println("=== Violating ISP Example ===");

        Waiter waiter = new Waiter();

        // The waiter can perform his real tasks fine
        waiter.takeOrder();
        waiter.serveFoodAndDrinks();

        System.out.println();
        // These force the waiter to act outside his role
        waiter.prepareFood();      // throws exception
        waiter.decideMenu();       // throws exception
        waiter.cleanTheKitchen();  // throws exception
    }
}

// A single fat interface containing unrelated responsibilities
interface RestaurantEmployee {
    void prepareFood();
    void decideMenu();
    void serveFoodAndDrinks();
    void takeOrder();
    void cleanTheKitchen();
}

// Violates ISP: Waiter forced to implement methods that don't make sense
class Waiter implements RestaurantEmployee {

    @Override
    public void takeOrder() {
        System.out.println("Taking order...");
    }

    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Serving food and drinks...");
    }

    @Override
    public void prepareFood() {
        throw new AssertionError("Waiter cannot prepare food!");
    }

    @Override
    public void decideMenu() {
        throw new AssertionError("Waiter cannot decide the menu!");
    }

    @Override
    public void cleanTheKitchen() {
        throw new AssertionError("Waiter cannot clean the kitchen!");
    }
}

