package Project.Models;

import Project.Cars.Car;
import Project.Other.CarHolder;

import java.util.Random;

public class Train {
    private Station start;
    private Station destination;
    private int maxCars;
    private int maxWeight;
    private String name;
    private int uniqueNumber;
    private static int globalId = 1;
    private int speed;
    private CarHolder carHolder;
    public Train(int maxCars, int speed, int maxWeight, String name) {
        this.name = name;
        this.maxCars = maxCars;

        this.maxWeight = maxWeight;

        carHolder = new CarHolder(this);

        this.speed = speed;
        this.uniqueNumber = globalId++;
    }

    public Station getStart() {
        return start;
    }

    public Station getDestination() {
        return destination;
    }

    public double getSpeed() {
        return speed;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public void addCar(Car car) {
        carHolder.getCars().add(car);
    }

    public void changeSpeed() {
        speed = (int)(speed * (new Random().nextBoolean() ? 1.03 : 0.97));
    }

    public void setStart(Station start) {
        this.start = start;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Train{" +
                "start=" + start +
                ", destination=" + destination +
                ", maxCars=" + maxCars +
                ", maxWeight=" + maxWeight +
                ", name='" + name + '\'' +
                ", uniqueNumber=" + uniqueNumber +
                ", speed=" + speed +
                ", carHolder=" + carHolder +
                '}';
    }
}