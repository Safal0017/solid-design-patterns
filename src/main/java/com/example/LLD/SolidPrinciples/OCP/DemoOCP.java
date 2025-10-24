package com.example.LLD.SolidPrinciples.OCP;

public class DemoOCP {
    public static void main(String[] args) {
        System.out.println("=== Violating OCP Example ===");
        BadInvoice badInvoice = new BadInvoice(new Marker("Blue Marker", "Blue", 10, 2022), 5);
        badInvoice.calculateTotal();
        BadInvoiceDao badDao = new BadInvoiceDao(badInvoice);
        badDao.saveToDB();
        badDao.saveToFile();

        System.out.println("\n=== Following OCP Example ===");
        Invoice invoice = new Invoice(new Marker("Red Marker", "Red", 15, 2021), 10);
        invoice.calculateTotal();

        InvoiceDao dbDao = new DatabaseInvoiceDao(invoice);
        InvoiceDao fileDao = new FileInvoiceDao(invoice);

        dbDao.save();
        fileDao.save();
    }
}

// ======= COMMON SUPPORT CLASS =======
class Marker {
    String name;
    String color;
    int price;
    int year;

    public Marker(String name, String color, int price, int year) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.year = year;
    }
}

// ======= VIOLATING OCP =======
class BadInvoice {
    Marker marker;
    int quantity;
    int total;

    public BadInvoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public void calculateTotal() {
        total = marker.price * quantity;
        System.out.println("Total (violating OCP): " + total);
    }
}

class BadInvoiceDao {
    BadInvoice invoice;

    public BadInvoiceDao(BadInvoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        System.out.println("Saving to DB...");
    }

    public void saveToFile() {
        System.out.println("Saving to File...");
    }
}

// ======= FOLLOWING OCP =======
class Invoice {
    Marker marker;
    int quantity;
    int total;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public void calculateTotal() {
        total = marker.price * quantity;
        System.out.println("Total (following OCP): " + total);
    }
}

// OCP Compliant Interface
interface InvoiceDao {
    void save();
}

// Concrete Implementations — easily extendable
class DatabaseInvoiceDao implements InvoiceDao {
    Invoice invoice;

    public DatabaseInvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void save() {
        System.out.println("Saving to DB...");
    }
}

class FileInvoiceDao implements InvoiceDao {
    Invoice invoice;

    public FileInvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void save() {
        System.out.println("Saving to File...");
    }
}
