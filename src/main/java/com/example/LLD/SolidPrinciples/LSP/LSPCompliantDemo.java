package com.example.LLD.SolidPrinciples.LSP;

public class LSPCompliantDemo {
    public static void main(String[] args) {
        MotorCycleLSP motorCycle = new MotorCycleLSP("HeroHonda", 10);
        BicycleLSP bicycle = new BicycleLSP("Hercules", true, 10);

        System.out.println("=== Following LSP Example ===");

        motorCycle.turnOnEngine();
        motorCycle.accelerate();
        motorCycle.applyBrakes();
        motorCycle.turnOffEngine();

        System.out.println();
        bicycle.accelerate();
        bicycle.applyBrakes();
    }
}

// Base abstraction common to all bikes
abstract class BikeLSP {
    public abstract void accelerate();
    public abstract void applyBrakes();
}

// Separate Engine capability interface
interface Engine {
    void turnOnEngine();
    void turnOffEngine();
}

class MotorCycleLSP extends BikeLSP implements Engine {
    String company;
    boolean isEngineOn;
    int speed;

    public MotorCycleLSP(String company, int speed) {
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

class BicycleLSP extends BikeLSP {
    String brand;
    boolean hasGears;
    int speed;

    public BicycleLSP(String brand, boolean hasGears, int speed) {
        this.brand = brand;
        this.hasGears = hasGears;
        this.speed = speed;
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

