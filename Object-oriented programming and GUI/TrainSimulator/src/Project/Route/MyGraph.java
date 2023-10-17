package Project.Route;

import Project.Models.Station;

import java.util.ArrayList;

public class MyGraph {
    public ArrayList<Station> searchGraph(Station start, Station destination)
    {
        return searchGraph(start, null, destination);
    }

    private ArrayList<Station> searchGraph(Station start, ArrayList<Station> checked, Station destination)
    {
        if(checked == null)
        {
            checked = new ArrayList<>();
        }
        checked.add(start);
        System.out.println(start.getStationName());
        boolean f = false;
        ArrayList<Station> ret = new ArrayList<>();
        for (int i = 0; i < start.getConnectedStations().size(); i++)
        {
            if (!checked.contains(start.getConnectedStations().get(i)))
            {
                ArrayList<Station> destinations = searchGraph(start.getConnectedStations().get(i), checked, destination);
                if (destinations != null) {
                    f = true;
                    ret.addAll(destinations);
                }
            }
        }

        if (start == destination || f)
        {
            ret.add(start);
            return ret;
        }
        return null;
    }

}
