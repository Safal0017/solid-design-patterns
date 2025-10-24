package com.example.LLD.DesignPatterns.Behavioral.Strategy.vehicle.solution;

// Strategy interface
interface DriveStrategy {
    void drive();
}

// Concrete strategies
class NormalDrive implements DriveStrategy {
    public void drive() {
        System.out.println("Driving Capability: Normal");
    }
}

class SportsDrive implements DriveStrategy {
    public void drive() {
        System.out.println("Driving Capability: Sports");
    }
}

class EVDrive implements DriveStrategy {
    public void drive() {
        System.out.println("Driving Capability: Electric");
    }
}

// Context class
class Vehicle {
    private final DriveStrategy driveStrategy;

    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        System.out.print("\n" + this.getClass().getSimpleName() + ": ");
        driveStrategy.drive();
    }
}

// Concrete vehicle types
class SportsVehicle extends Vehicle {
    public SportsVehicle(DriveStrategy strategy) {
        super(strategy);
    }
}

class OffRoadVehicle extends Vehicle {
    public OffRoadVehicle(DriveStrategy strategy) {
        super(strategy);
    }
}

class HybridVehicle extends Vehicle {
    public HybridVehicle(DriveStrategy strategy) {
        super(strategy);
    }
}

class GoodsVehicle extends Vehicle {
    public GoodsVehicle(DriveStrategy strategy) {
        super(strategy);
    }
}

// Demo
public class WithStrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Example: Vehicle Drive Modes ===");

        Vehicle sports = new SportsVehicle(new SportsDrive());
        sports.drive();

        Vehicle goods = new GoodsVehicle(new NormalDrive());
        goods.drive();

        Vehicle hybrid = new HybridVehicle(new EVDrive());
        hybrid.drive();
    }
}

//The improved version with the strategy pattern puts each drive mode
// into its own separate class, so vehicle classes just use these classes
// to do the actual driving. This makes the code cleaner, easier to update,
// and avoids repeating the same logic in many places.