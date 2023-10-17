package Project.Route;

import Project.Models.Station;
import Project.Models.Train;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Route extends Thread {
    private final Train train;
    public Route(Train train) {
        this.train = train;
    }

    private void startSim() throws InterruptedException {
        MyGraph myGraph = new MyGraph();
        ArrayList<Station> stations = myGraph.searchGraph(train.getStart(), train.getDestination());

        Station st1 = stations.get(0), st2;
        while (stations.get(stations.size() - 1) != st1) {
            st2 = stations.get(stations.indexOf(st1) + 1);
            double length = new Random().nextInt(10, 30);

            System.out.println("Distance between " + st1.getStationName() + " and " + st2.getStationName() + " is equals " + length);

            double left = 0;
            while (left < length) {

                left += (train.getSpeed()) / 60;
                System.out.println(train + " Travel " + " between " + st1.getStationName() + " and " + st2.getStationName() + " " + left + "[J] / " + length + "[J] speed = " + train.getSpeed());
                train.changeSpeed();
                Thread.sleep(1000);
            }

            st1 = st2;
            System.out.println("Train with id =" + train.getUniqueNumber() + " waits 2 seconds at " + st1.getStationName());
            Thread.sleep(2000);
        }

        System.out.println("Train with id =" + train.getUniqueNumber() + " waits 30 seconds at " + st1.getStationName());

        Thread.sleep(30000);

        Collections.reverse(stations);

        while (stations.get(stations.size() - 1) != st1) {
            st2 = stations.get(stations.indexOf(st1) + 1);
            double length = new Random().nextInt(10, 30);

            System.out.println("Distance between " + st1.getStationName() + " and " + st2.getStationName() + " is equals " + length);

            double left = 0;
            while (left < length) {

                left += (train.getSpeed()) / 60;
                System.out.println(train + " Travel " + " between " + st1.getStationName() + " and " + st2.getStationName() + " " + left + "[J] / " + length + "[J] speed = " + train.getSpeed());
                train.changeSpeed();
                Thread.sleep(1000);
            }

            st1 = st2;
            System.out.println("Train with id =" + train.getUniqueNumber() + " waits 2 seconds at " + st1.getStationName());
            Thread.sleep(2000);
        }
        System.out.println("Train with id=" + train.getUniqueNumber() + "ended travel");
    }

    @Override
    public void run() {
        try {
            startSim();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
