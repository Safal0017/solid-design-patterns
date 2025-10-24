package com.example.LLD.DesignPatterns.Behavioral.Strategy.shopping.violation;

class PaymentProcessor {

    public void processPayment(String type, double amount) {
        switch (type) {
            case "credit_card" -> System.out.println("Paid $" + amount + " using credit card");
            case "paypal" -> System.out.println("Paid $" + amount + " using PayPal");
            case "net_banking" -> System.out.println("Paid $" + amount + " using net banking");
            case "cash" -> System.out.println("Paid $" + amount + " using cash");
            default -> throw new IllegalStateException("Unexpected payment type: " + type);
        }
    }
}

public class WithoutStrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== Payment Processor (Without Strategy Pattern) ===");
        PaymentProcessor processor = new PaymentProcessor();

        processor.processPayment("credit_card", 100);
        processor.processPayment("paypal", 200);
        processor.processPayment("net_banking", 300);
        processor.processPayment("cash", 400);
    }
}

//The shopping example without the strategy pattern mixes all payment logic
// inside one big class, causing repeated code, tight coupling, and making
// it harder to add new payment types like crypto or coupons without changing existing code.