package com.example.LLD.DesignPatterns.Creational.Factory.factorymethod;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        System.out.println("======= Factory Method Design Pattern ======");

        ShapeFactory shapeFactory = new SquareCreator();
        Shape shape = shapeFactory.createShape();
        shape.draw();
        shape.computeArea();

        shapeFactory = new CircleCreator();
        shape = shapeFactory.createShape();
        shape.draw();
        shape.computeArea();

        shapeFactory = new RectangleCreator();
        shape = shapeFactory.createShape();
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

// Abstract Creator
abstract class ShapeFactory {
    public abstract Shape createShape();
}

// Concrete Creators
class CircleCreator extends ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}

class RectangleCreator extends ShapeFactory {
    @Override
    public Shape createShape() {
        return new Rectangle();
    }
}

class SquareCreator extends ShapeFactory {
    @Override
    public Shape createShape() {
        return new Square();
    }
}

//Factory Method uses inheritance and polymorphism to delegate creation to subclasses.
//This fits Open/Closed principle better as new products just add new creator classes without modifying existing code.