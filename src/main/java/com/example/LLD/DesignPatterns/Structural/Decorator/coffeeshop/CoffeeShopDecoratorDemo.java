package com.example.LLD.DesignPatterns.Structural.Decorator.coffeeshop;

public class CoffeeShopDecoratorDemo {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern - Coffee Shop Example ===");

        Coffee blackCoffee = new BlackCoffee();
        System.out.println(blackCoffee.getDescription() + " = Rs." + blackCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(new BlackCoffee());
        System.out.println(milkCoffee.getDescription() + " = Rs." + milkCoffee.getCost());

        Coffee milkSugarCoffee = new SugarDecorator(new MilkDecorator(new BlackCoffee()));
        System.out.println(milkSugarCoffee.getDescription() + " = Rs." + milkSugarCoffee.getCost());
    }
}

// ===== Component Interface =====
interface Coffee {
    String getDescription();
    double getCost();
}

// ===== Concrete Component =====
class BlackCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Black Coffee";
    }

    @Override
    public double getCost() {
        return 50.0;
    }
}

// ===== Abstract Decorator =====
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// ===== Concrete Decorators =====
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 10.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Sugar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 5.0;
    }
}
