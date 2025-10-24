package com.example.LLD.SolidPrinciples.DIP.violation;

// High-level class directly depends on low-level details. Violates DIP.
public class DIPViolationDemo {
    public static void main(String[] args) {
        WiredKeyboard wiredKeyboard = new WiredKeyboard("USB", "Dell", "F602", "Grey");
        WiredMouse wiredMouse = new WiredMouse("USB", "Dell", "F602", "Grey");

        BluetoothKeyboard bluetoothKeyboard = new BluetoothKeyboard("Bluetooth", "Logitech", "G102", "Black");
        BluetoothMouse bluetoothMouse = new BluetoothMouse("Bluetooth", "Logitech", "G102", "Black");

        System.out.println("=== Violating DIP ===");

        // MacBook can only use wired keyboard and mouse
        MacBook macBookWithWiredParts = new MacBook(wiredKeyboard, wiredMouse);
        macBookWithWiredParts.getKeyboard().getSpecifications();
        macBookWithWiredParts.getMouse().getSpecifications();

        System.out.println();

        // Cannot use Bluetooth versions directly — tight coupling
        // MacBook macBookWithBluetooth = new MacBook(bluetoothKeyboard, bluetoothMouse); // not allowed
        System.out.println("Cannot use Bluetooth peripherals without rewriting MacBook class.");
    }
}

// Concrete devices
interface Keyboard {
    void getSpecifications();
}

interface Mouse {
    void getSpecifications();
}

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

// High-level module tightly coupled to specific implementations
class MacBook {
    private final WiredKeyboard keyboard;
    private final WiredMouse mouse;

    public MacBook(WiredKeyboard keyboard, WiredMouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public WiredKeyboard getKeyboard() {
        return keyboard;
    }

    public WiredMouse getMouse() {
        return mouse;
    }
}

