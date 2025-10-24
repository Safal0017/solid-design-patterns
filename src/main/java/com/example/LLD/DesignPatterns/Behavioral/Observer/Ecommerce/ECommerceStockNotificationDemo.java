package com.example.LLD.DesignPatterns.Behavioral.Observer.Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class ECommerceStockNotificationDemo {
    public static void main(String[] args) {
        System.out.println("=== E-commerce Stock Notification System ===");

        StockAvailabilityObservable iphoneProduct = new IphoneProductObservable("ip15", "iPhone 15", 1250, 10);

        StockNotificationObserver johnPush = new PushNotificationObserver("John123", "JohnDeviceP1");
        StockNotificationObserver katyPush = new PushNotificationObserver("Katy678", "KatyDeviceP2");
        StockNotificationObserver janeEmail = new EmailNotificationObserver("Jane783", "jane783@gmail.com");
        StockNotificationObserver georgeEmail = new EmailNotificationObserver("George993", "george993@gmail.com");

        // Sell all initial stock
        iphoneProduct.purchase(10);

        // Failed purchase - no stock, users subscribe for notifications
        if (!iphoneProduct.purchase(1)) {
            iphoneProduct.addStockObserver(johnPush);
            iphoneProduct.addStockObserver(katyPush);
            iphoneProduct.addStockObserver(janeEmail);
            iphoneProduct.addStockObserver(georgeEmail);
        }

        // Restock triggers notification to all subscribers
        iphoneProduct.restock(20);

        // Purchases by notified users
        iphoneProduct.purchase(1); // John
        iphoneProduct.purchase(1); // Katy

        // John and Katy unsubscribe
        iphoneProduct.removeStockObserver(johnPush);
        iphoneProduct.removeStockObserver(katyPush);

        // Final stock sales and restock
        iphoneProduct.purchase(18);
        iphoneProduct.restock(5);

        // Jane and George purchase remaining stock and unsubscribe
        iphoneProduct.purchase(1);
        iphoneProduct.purchase(1);
        iphoneProduct.removeStockObserver(janeEmail);
        iphoneProduct.removeStockObserver(georgeEmail);
    }
}

// Subject Interface
interface StockAvailabilityObservable {
    void addStockObserver(StockNotificationObserver observer);
    void removeStockObserver(StockNotificationObserver observer);
    void notifyStockObservers();
    boolean purchase(int quantity);
    void restock(int quantity);
}

// Concrete Subject
class IphoneProductObservable implements StockAvailabilityObservable {
    private final String productId;
    private final String productName;
    private final double price;
    private final List<StockNotificationObserver> stockObservers;
    private int stockQuantity;

    public IphoneProductObservable(String productId, String productName, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.stockObservers = new ArrayList<>();
    }

    @Override
    public void addStockObserver(StockNotificationObserver observer) {
        stockObservers.add(observer);
        System.out.println("[+]" + observer.getUserId() + " subscribed for notifications on " + productName);
    }

    @Override
    public void removeStockObserver(StockNotificationObserver observer) {
        stockObservers.remove(observer);
        System.out.println("[-]" + observer.getUserId() + " unsubscribed for notifications on " + productName);
    }

    @Override
    public void notifyStockObservers() {
        if (stockQuantity > 0 && !stockObservers.isEmpty()) {
            System.out.println("Notifying " + stockObservers.size() + " subscribers...");
            // Notify a safe copy to avoid concurrent modification
            List<StockNotificationObserver> observersToNotify = new ArrayList<>(stockObservers);
            for (StockNotificationObserver observer : observersToNotify) {
                observer.update();
            }
        }
    }

    @Override
    public void restock(int quantity) {
        boolean wasOutOfStock = (stockQuantity == 0);
        stockQuantity += quantity;
        System.out.println("RESTOCKED: " + productName + " - Added " + quantity + " items | Current stock: " + stockQuantity);
        if (wasOutOfStock && stockQuantity > 0) {
            notifyStockObservers();
        }
    }

    @Override
    public boolean purchase(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
            System.out.println("PURCHASE SUCCESS: " + quantity + " units of " + productName + " | Remaining stock: " + stockQuantity);
            return true;
        } else {
            System.out.println("PURCHASE FAILED: " + productName + " out of stock! | Available Quantity: " + stockQuantity);
            return false;
        }
    }
}

// Observer Interface
interface StockNotificationObserver {
    void update();
    String getNotificationMethod();
    String getUserId();
}

// Concrete Observer for Email
class EmailNotificationObserver implements StockNotificationObserver {
    private final String userId;
    private final String emailAddress;

    public EmailNotificationObserver(String userId, String emailAddress) {
        this.userId = userId;
        this.emailAddress = emailAddress;
    }

    @Override
    public void update() {
        System.out.println("!! EMAIL SENT to: " + emailAddress + " - Product is back in stock! Hurry up!!");
    }

    @Override
    public String getNotificationMethod() {
        return "Email";
    }

    @Override
    public String getUserId() {
        return userId;
    }
}

// Concrete Observer for Push Notification
class PushNotificationObserver implements StockNotificationObserver {
    private final String userId;
    private final String deviceToken;

    public PushNotificationObserver(String userId, String deviceToken) {
        this.userId = userId;
        this.deviceToken = deviceToken;
    }

    @Override
    public void update() {
        System.out.println("!! PUSH NOTIFICATION SENT to: " + deviceToken + " - Product is back in stock! Hurry up!!");
    }

    @Override
    public String getNotificationMethod() {
        return "Push Notification";
    }

    @Override
    public String getUserId() {
        return userId;
    }
}

