package com.example.LLD.DesignPatterns.Behavioral.Strategy.vehicle.violation;

class Vehicle {
    public void drive() {
        System.out.print("\n" + this.getClass().getSimpleName() + ": ");
        System.out.println("Driving Capability: Normal");
    }
}
//Code Duplication
class SportsVehicle extends Vehicle {
    @Override
    public void drive() {
        System.out.print("\n" + this.getClass().getSimpleName() + ": ");
        System.out.println("Driving Capability: Sports");
    }
}
//Code Duplication
class OffRoadVehicle extends Vehicle {
    @Override
    public void drive() {
        System.out.print("\n" + this.getClass().getSimpleName() + ": ");
        System.out.println("Driving Capability: Sports");
    }
}

class PassengerVehicle extends Vehicle {
    // Uses default "Normal" drive from Vehicle
}

public class WithoutStrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== Vehicle Drive Modes (Without Strategy Pattern) ===");

        Vehicle vehicle;

        vehicle = new SportsVehicle();
        vehicle.drive();

        vehicle = new OffRoadVehicle();
        vehicle.drive();

        vehicle = new PassengerVehicle();
        vehicle.drive();
    }
}

//The vehicle example without the strategy pattern repeats the drive code
// in every subclass, causing duplicated code and making it hard to add or
// change drive behaviors.