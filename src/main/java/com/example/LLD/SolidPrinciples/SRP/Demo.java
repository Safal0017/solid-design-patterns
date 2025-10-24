package com.example.LLD.SolidPrinciples.SRP;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== Violating SRP Example ===");
        BadInvoice badInvoice = new BadInvoice(new BadMarker("Blue", "Ink", 10, 2020), 5);
        badInvoice.calculateTotal();
        badInvoice.saveToDB();
        badInvoice.printInvoice();

        System.out.println("\n=== Following SRP Example ===");
        Marker marker = new Marker("Blue", "Ink", 10, 2020);
        Invoice invoice = new Invoice(marker, 5);
        InvoiceDao dao = new InvoiceDao(invoice);
        InvoicePrinter printer = new InvoicePrinter(invoice);

        invoice.calculateTotal();
        dao.saveToDB();
        printer.print();
    }
}

// ======== Violating SRP ========
class BadMarker {
    String name;
    String color;
    int price;
    int year;

    public BadMarker(String name, String color, int price, int year) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.year = year;
    }
}

class BadInvoice {
    private BadMarker marker;
    private int quantity;
    private int total;

    public BadInvoice(BadMarker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public void calculateTotal() {
        System.out.println("Calculating total...");
        this.total = this.marker.price * this.quantity;
    }

    public void saveToDB() {
        System.out.println("Saving to DB...");
    }

    public void printInvoice() {
        System.out.println("Printing Invoice...");
    }
}

// ======== Follows SRP ========
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

class Invoice {
    private Marker marker;
    private int quantity;
    private int total;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public void calculateTotal() {
        System.out.println("Calculating total...");
        this.total = this.marker.price * this.quantity;
    }
}

class InvoiceDao {
    private Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        System.out.println("Saving to DB...");
    }
}

class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void print() {
        System.out.println("Printing Invoice...");
    }
}
