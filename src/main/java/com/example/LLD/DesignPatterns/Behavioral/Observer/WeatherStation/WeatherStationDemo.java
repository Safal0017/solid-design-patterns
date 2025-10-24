package com.example.LLD.DesignPatterns.Behavioral.Observer.WeatherStation;

import java.util.ArrayList;
import java.util.List;

public class WeatherStationDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Example: Weather Station ===");

        WeatherObservable station = new WeatherStation();

        // Create and register observers
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(station);
        ForecastDisplay forecastDisplay = new ForecastDisplay(station);

        System.out.println("\n--- First Update ---");
        station.setWeatherReadings(80, 65, 30.4f);

        System.out.println("\n--- Second Update ---");
        station.setWeatherReadings(82, 70, 29.2f);

        // Remove forecast
        station.removeObserver(forecastDisplay);

        System.out.println("\n--- Third Update ---");
        station.setWeatherReadings(70, 21, 29.2f);
    }
}

// ===== SUBJECT (Observable Interface) =====
interface WeatherObservable {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
    void setWeatherReadings(float temperature, float humidity, float pressure);
}

// ===== CONCRETE SUBJECT =====
class WeatherStation implements WeatherObservable {
    private final List<WeatherObserver> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
        System.out.println("[+] Observer added: " + observer.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
        System.out.println("[-] Observer removed: " + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver o : observers) o.update();
    }

    @Override
    public void setWeatherReadings(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "WeatherStation{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}

// ===== OBSERVER INTERFACE =====
interface WeatherObserver {
    void update();
}

// ===== CONCRETE OBSERVER 1 =====
class CurrentConditionsDisplay implements WeatherObserver {
    private final WeatherObservable weatherStation;

    public CurrentConditionsDisplay(WeatherObservable station) {
        this.weatherStation = station;
        station.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Updating Current Conditions Display...");
        System.out.println("Current Conditions: " + weatherStation);
    }
}

// ===== CONCRETE OBSERVER 2 =====
class ForecastDisplay implements WeatherObserver {
    private final WeatherObservable weatherStation;

    public ForecastDisplay(WeatherObservable station) {
        this.weatherStation = station;
        station.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Updating Forecast Display...");
        System.out.println("Forecast: Analyzing pressure and humidity changes.");
    }
}

// Without Observer Pattern: Every display would need a manual update → repeated code.
// With Observer Pattern: WeatherStation auto notifies all displays → clean and reusable design.
