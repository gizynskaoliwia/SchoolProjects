package Project.Models;

import java.util.ArrayList;

public class Station {
    private final String stationName;
    private final ArrayList<Station> connectedStations;
    public Station(String stationName) {
        this.stationName = stationName;
        this.connectedStations = new ArrayList<>();
    }

    public void addStation(Station station) {
        connectedStations.add(station);
    }

    public String getStationName() {
        return stationName;
    }

    public ArrayList<Station> getConnectedStations() {
        return connectedStations;
    }

    @Override
    public String toString() {
        return "Station -> " +
                "stationName='" + stationName;
    }
}

