package com.example.LLD.DesignPatterns.Structural.Decorator.pizzashop;

public class PizzaShopDecoratorDemo {
    public static void main(String[] args) {
        System.out.println("===== Decorator Design Pattern - Pizza Shop =====");

        // Plain pizza
        BasePizza pizza1 = new PlainPizza();
        System.out.println("Order 1: " + pizza1.getDescription() + " = Rs." + pizza1.getCost());

        // Plain pizza + Extra Cheese
        BasePizza pizza2 = new ExtraCheeseTopping(new PlainPizza());
        System.out.println("Order 2: " + pizza2.getDescription() + " = Rs." + pizza2.getCost());

        // Plain pizza + Extra Cheese + Veggies
        BasePizza pizza3 = new VeggiesTopping(new ExtraCheeseTopping(new PlainPizza()));
        System.out.println("Order 3: " + pizza3.getDescription() + " = Rs." + pizza3.getCost());

        // Plain pizza + Extra Cheese + Pepperoni
        BasePizza pizza4 = new PepperoniTopping(new ExtraCheeseTopping(new PlainPizza()));
        System.out.println("Order 4: " + pizza4.getDescription() + " = Rs." + pizza4.getCost());

        // Plain pizza + Extra Cheese + Mushroom + Pepperoni
        BasePizza pizza5 = new MushroomTopping(new PepperoniTopping(new ExtraCheeseTopping(new PlainPizza())));
        System.out.println("Order 5: " + pizza5.getDescription() + " = Rs." + pizza5.getCost());

        // Farmhouse base pizza
        BasePizza pizza6 = new Farmhouse();
        System.out.println("Order 6: " + pizza6.getDescription() + " = Rs." + pizza6.getCost());

        // Farmhouse with Extra Cheese + Mushroom
        BasePizza pizza7 = new MushroomTopping(new ExtraCheeseTopping(new Farmhouse()));
        System.out.println("Order 7: " + pizza7.getDescription() + " = Rs." + pizza7.getCost());

        // Paneer Delight
        BasePizza pizza8 = new TandooriPaneerDelight();
        System.out.println("Order 8: " + pizza8.getDescription() + " = Rs." + pizza8.getCost());

        // Chicken Dominator
        BasePizza pizza9 = new ChickenDominator();
        System.out.println("Order 9: " + pizza9.getDescription() + " = Rs." + pizza9.getCost());

        // Chicken Dominator + Mushroom
        BasePizza pizza10 = new MushroomTopping(new ChickenDominator());
        System.out.println("Order 10: " + pizza10.getDescription() + " = Rs." + pizza10.getCost());
    }
}

// ===== Component Interface =====
interface BasePizza {
    String getDescription();
    double getCost();
}

// ===== Concrete Components (Base Pizza Variants) =====
class PlainPizza implements BasePizza {
    public String getDescription() { return "Plain Pizza"; }
    public double getCost() { return 200.00; }
}

class Farmhouse implements BasePizza {
    public String getDescription() { return "Farmhouse Pizza"; }
    public double getCost() { return 300.00; }
}

class TandooriPaneerDelight implements BasePizza {
    public String getDescription() { return "Tandoori Paneer Delight Pizza"; }
    public double getCost() { return 400.00; }
}

class ChickenDominator implements BasePizza {
    public String getDescription() { return "Chicken Dominator Pizza"; }
    public double getCost() { return 500.00; }
}

// ===== Abstract Decorator =====
abstract class ToppingDecorator implements BasePizza {
    protected BasePizza pizza;
    public ToppingDecorator(BasePizza pizza) {
        this.pizza = pizza;
    }
}

// ===== Concrete Decorators (Toppings) =====
class ExtraCheeseTopping extends ToppingDecorator {
    public ExtraCheeseTopping(BasePizza pizza) { super(pizza); }
    public String getDescription() { return pizza.getDescription() + " + Extra Cheese"; }
    public double getCost() { return pizza.getCost() + 20; }
}

class VeggiesTopping extends ToppingDecorator {
    public VeggiesTopping(BasePizza pizza) { super(pizza); }
    public String getDescription() { return pizza.getDescription() + " + Veggies"; }
    public double getCost() { return pizza.getCost() + 30; }
}

class MushroomTopping extends ToppingDecorator {
    public MushroomTopping(BasePizza pizza) { super(pizza); }
    public String getDescription() { return pizza.getDescription() + " + Mushroom"; }
    public double getCost() { return pizza.getCost() + 40; }
}

class PepperoniTopping extends ToppingDecorator {
    public PepperoniTopping(BasePizza pizza) { super(pizza); }
    public String getDescription() { return pizza.getDescription() + " + Pepperoni"; }
    public double getCost() { return pizza.getCost() + 50; }
}

// Problem (Before Decorator): Each new pizza-topping combination required a new subclass – lots of code repetition.
// Solution (After Decorator): Adds toppings dynamically using wrapping (composition) → flexible, reusable, and clean design.
