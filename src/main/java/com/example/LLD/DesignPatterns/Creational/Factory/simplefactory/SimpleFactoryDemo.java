package com.example.LLD.DesignPatterns.Creational.Factory.simplefactory;

public class SimpleFactoryDemo {

    public static void main(String[] args) {
        System.out.println("======= Simple Factory Design Pattern ======");
        ShapeType shapeType = ShapeType.SQUARE;
        Shape shape = ShapeFactory.createShapeInstance(shapeType);
        shape.draw();
        shape.computeArea();
    }
}

// Product interface
interface Shape {
    void computeArea();
    void draw();
}

// Concrete Products
class Circle implements Shape {
    @Override
    public void computeArea() {
        System.out.println("Inside Circle::computeArea() method.");
    }
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

class Rectangle implements Shape {
    @Override
    public void computeArea() {
        System.out.println("Inside Rectangle::computeArea() method.");
    }
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape {
    @Override
    public void computeArea() {
        System.out.println("Inside Square::computeArea() method.");
    }
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

// ShapeType enum
enum ShapeType {
    CIRCLE, RECTANGLE, SQUARE
}

// Simple Factory
class ShapeFactory {
    public static Shape createShapeInstance(ShapeType shapeType) {
        if (shapeType == null) {
            return null;
        }
        return switch (shapeType) {
            case CIRCLE -> new Circle();
            case RECTANGLE -> new Rectangle();
            case SQUARE -> new Square();
            default -> throw new IllegalStateException("ShapeType doesn't exist!");
        };
    }
}

//Simple Factory uses a static method with conditional logic to create instances.
//It’s easy but requires modifying factory method to add new products, violating Open/Closed principle.