package com.example.LLD.SolidPrinciples.DIP.solution;

// High-level module depends on abstractions, not concretions. Follows DIP.
public class DIPCompliantDemo {
    public static void main(String[] args) {
        WiredKeyboard wiredKeyboard = new WiredKeyboard("USB", "Dell", "F602", "Grey");
        WiredMouse wiredMouse = new WiredMouse("USB", "Dell", "F602", "Grey");

        BluetoothKeyboard bluetoothKeyboard = new BluetoothKeyboard("Bluetooth", "Logitech", "G102", "Black");
        BluetoothMouse bluetoothMouse = new BluetoothMouse("Bluetooth", "Logitech", "G102", "Black");

        System.out.println("=== Following DIP ===");

        // Create MacBook with wired peripherals
        MacBookDIP macWithWired = new MacBookDIP(wiredMouse, wiredKeyboard);
        macWithWired.getKeyboard().getSpecifications();
        macWithWired.getMouse().getSpecifications();

        System.out.println();

        // Create MacBook with Bluetooth peripherals — works without code modification
        MacBookDIP macWithBluetooth = new MacBookDIP(bluetoothMouse, bluetoothKeyboard);
        macWithBluetooth.getKeyboard().getSpecifications();
        macWithBluetooth.getMouse().getSpecifications();
    }
}

// Base abstractions
interface Keyboard {
    void getSpecifications();
}

interface Mouse {
    void getSpecifications();
}

// Concrete Implementations
class WiredKeyboard implements Keyboard {
    private final String connectionType, company, modelVersion, color;

    public WiredKeyboard(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Wired Keyboard");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

class WiredMouse implements Mouse {
    private final String connectionType, company, modelVersion, color;

    public WiredMouse(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Wired Mouse");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

class BluetoothKeyboard implements Keyboard {
    private final String connectionType, company, modelVersion, color;

    public BluetoothKeyboard(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Bluetooth Keyboard");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

class BluetoothMouse implements Mouse {
    private final String connectionType, company, modelVersion, color;

    public BluetoothMouse(String connectionType, String company, String modelVersion, String color) {
        this.connectionType = connectionType;
        this.company = company;
        this.modelVersion = modelVersion;
        this.color = color;
    }

    public void getSpecifications() {
        System.out.println("===> Bluetooth Mouse");
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Company: " + company);
        System.out.println("Model: " + modelVersion);
        System.out.println("Color: " + color);
    }
}

// High-level module depends only on abstractions → DIP-compliant
class MacBookDIP {
    private final Mouse mouse;
    private final Keyboard keyboard;

    public MacBookDIP(Mouse mouse, Keyboard keyboard) {
        this.mouse = mouse;
        this.keyboard = keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }
}

