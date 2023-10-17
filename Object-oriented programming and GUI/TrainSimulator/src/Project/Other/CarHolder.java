package Project.Other;

import Project.Cars.Car;
import Project.Models.Train;

import java.util.ArrayList;

public class CarHolder {

    public CarHolder(Train train){
        this.train = train;
    }
    private ArrayList<Car> cars = new ArrayList<>();
    private Train train;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
