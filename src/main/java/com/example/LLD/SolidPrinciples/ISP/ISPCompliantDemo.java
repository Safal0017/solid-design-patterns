package com.example.LLD.SolidPrinciples.ISP;

public class ISPCompliantDemo {
    public static void main(String[] args) {
        System.out.println("=== Following ISP Example ===");

        Chef chef = new Chef();
        WaiterISP waiter = new WaiterISP();

        // Chef operations
        chef.prepareFood();
        chef.decideMenu();

        System.out.println();
        // Waiter operations
        waiter.takeOrder();
        waiter.serveFoodAndDrinks();
    }
}

// Focused, small interfaces following ISP
interface ChefTasks {
    void prepareFood();
    void decideMenu();
}

interface WaiterTasks {
    void serveFoodAndDrinks();
    void takeOrder();
}

interface MaintenanceTasks {
    void cleanTheKitchen();
    void reStockGroceries();
}

// Chef only implements relevant interface
class Chef implements ChefTasks {

    @Override
    public void prepareFood() {
        System.out.println("Preparing food...");
    }

    @Override
    public void decideMenu() {
        System.out.println("Deciding menu...");
    }
}

// Waiter only implements relevant interface
class WaiterISP implements WaiterTasks {

    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Serving food and drinks...");
    }

    @Override
    public void takeOrder() {
        System.out.println("Taking order...");
    }
}

