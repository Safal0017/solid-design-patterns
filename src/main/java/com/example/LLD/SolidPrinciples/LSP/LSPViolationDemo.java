package com.example.LLD.SolidPrinciples.LSP;

public class LSPViolationDemo {
    public static void main(String[] args) {
        MotorCycle motorCycle = new MotorCycle("HeroHonda", 10);
        Bicycle bicycle = new Bicycle("Hercules", true, 10);

        System.out.println("=== Violating LSP Example ===");
        motorCycle.turnOnEngine();
        motorCycle.accelerate();
        motorCycle.applyBrakes();
        motorCycle.turnOffEngine();

        System.out.println();
        // This will fail because Bicycle has no engine
        bicycle.turnOnEngine();
        bicycle.accelerate();
        bicycle.applyBrakes();
        bicycle.turnOffEngine();
    }
}

interface Bike {
    void turnOnEngine();
    void turnOffEngine();
    void accelerate();
    void applyBrakes();
}

class MotorCycle implements Bike {
    String company;
    boolean isEngineOn;
    int speed;

    public MotorCycle(String company, int speed) {
        this.company = company;
        this.speed = speed;
    }

    @Override
    public void turnOnEngine() {
        this.isEngineOn = true;
        System.out.println("Engine is ON!");
    }

    @Override
    public void turnOffEngine() {
        this.isEngineOn = false;
        System.out.println("Engine is OFF!");
    }

    @Override
    public void accelerate() {
        this.speed += 10;
        System.out.println("MotorCycle Speed: " + this.speed);
    }

    @Override
    public void applyBrakes() {
        this.speed -= 5;
        System.out.println("MotorCycle Speed: " + this.speed);
    }
}

class Bicycle implements Bike {
    String brand;
    boolean hasGears;
    int speed;

    public Bicycle(String brand, boolean hasGears, int speed) {
        this.brand = brand;
        this.hasGears = hasGears;
        this.speed = speed;
    }

    // Violation: forcibly implementing engine methods not needed
    @Override
    public void turnOnEngine() {
        throw new AssertionError("Bicycle has no engine!");
    }

    @Override
    public void turnOffEngine() {
        throw new AssertionError("Bicycle has no engine!");
    }

    @Override
    public void accelerate() {
        this.speed += 10;
        System.out.println("Bicycle Speed: " + this.speed);
    }

    @Override
    public void applyBrakes() {
        this.speed -= 5;
        System.out.println("Bicycle Speed: " + this.speed);
    }
}

