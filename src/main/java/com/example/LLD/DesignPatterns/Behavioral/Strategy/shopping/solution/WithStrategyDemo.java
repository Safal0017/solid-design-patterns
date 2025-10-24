package com.example.LLD.DesignPatterns.Behavioral.Strategy.shopping.solution;

// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}

class UPIPayment implements PaymentStrategy {
    private final String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI ID: " + upiId);
    }
}

// Context class
class ShoppingCart {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(double amount) {
        if (strategy == null) throw new IllegalStateException("No payment strategy selected!");
        System.out.print(strategy.getClass().getSimpleName() + " -> ");
        strategy.pay(amount);
    }
}

// Demo
public class WithStrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Example: Shopping Cart Payments ===");

        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234567890123456"));
        cart.checkout(100.0);

        cart.setPaymentStrategy(new PayPalPayment("johndoe@example.com"));
        cart.checkout(200.0);

        cart.setPaymentStrategy(new UPIPayment("9988776655@ybl"));
        cart.checkout(300.0);
    }
}

//The strategy pattern version separates each payment method (credit card, PayPal, UPI, etc.)
// into its own class. The shopping cart then just uses these payment classes —
// making the design cleaner, easier to extend, and free from repeated logic.