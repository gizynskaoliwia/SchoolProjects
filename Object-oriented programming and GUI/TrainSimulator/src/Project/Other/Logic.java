package Project.Other;

import Project.Cars.Car;
import Project.Models.Station;
import Project.Models.Train;
import Project.Route.Route;

import java.util.ArrayList;

public class Logic {
    private ArrayList<Train> trains = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();
    public Logic() {

    }

    public Station getStationByName(String name) {
        return stations.stream().filter(e -> e.getStationName().equals(name)).findFirst().get();
    }

    public ArrayList<Train> getTrains()
    {
        return trains;
    }
    public void showTrains()
    {
        ArrayList<Train> trains = getTrains();
        System.out.println("TRAIN LIST -> ");
        for (Train train : trains)
        {
            System.out.println(train.toString());
        }
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void showStations() {
        System.out.println("Stations -> ");
        for (Station s : getStations())
        {
            System.out.println(s);
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void showCars() {
        System.out.println("Cars -> ");
        for (Car car : getCars()) {
            System.out.println(car);
        }
    }
    public void addTrain(Train train)
    {
        trains.add(train);
    }

    public void addCar(Car car)
    {
        cars.add(car);
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public void useCar(int trainId, int carId)
    {
        Train train = trains.stream().filter(e -> e.getUniqueNumber() == (trainId)).findFirst().get();
        Car car = cars.stream().filter(e -> e.getId() == carId).findFirst().get();
        cars.remove(car);
        train.addCar(car);
    }

    public void run(ArrayList<Route> routes) {
        for (Route t : routes)
            new Thread(t).start();
    }
}


