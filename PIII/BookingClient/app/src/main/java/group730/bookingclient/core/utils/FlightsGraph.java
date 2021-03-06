package group730.bookingclient.core.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import group730.bookingclient.core.entities.Flight;

/**
 * A modified directed graph data object containing a Collection of flights and
 * flights that are connecting to other destinations from the given flights
 * destination.
 */
public class FlightsGraph {

    /**
     * A Map, where each key is a Node and each value is a Set of Nodes
     * adjacent to it.
     */
    public HashMap<Flight, Set<Flight>> flightsMap;

    /**
     * Creates a new Graph.
     */
    public FlightsGraph(List<Flight> flights) {
        flightsMap = new HashMap<>();

        for (Flight flight : flights) {
            addFlight(flight);
        }

        for (Flight key : flightsMap.keySet()) {
            for (Flight key1 : flightsMap.keySet()) {
                if (key != key1) {
                    if (key.getDestination() == key1.getOrigin()) {
                        this.addConnectingFlight(key, key1);
                    }
                }
            }
        }
    }

    /**
     * Returns a Set of Nodes in this FlightsGraph.
     *
     * @return a Set of Nodes in this FlightsGraph.
     */
    public Set<Flight> getFlights() {
        return this.flightsMap.keySet();
    }

    /**
     * Returns a flight from the graph given a matching id.
     *
     * @param flightNumber the flight number of the airline.
     * @return the unique Flight object with the aforementioned flight number.
     */
    public Flight getFlight(int flightNumber) {
        for (Flight flight : this.getFlights()) {
            if (flight.getFlightNumber() == flightNumber)
                return flight;
        }
        return null;
    }

    /**
     * Returns the Node from this FlightsGraph with the given ID.
     *
     * @param origin the origin of the Flight.
     * @return the Node from this FlightsGraph with the given ID.
     */
    public Flight getFlightByOrigin(String origin) {
        for (Flight flight : this.getFlights()) {
            if (flight.getOrigin().equals(origin)) {
                return flight;
            }
        }

        return null;
    }

    /**
     * Returns a Set of neighbours of the given Node.
     *
     * @param flight the Node whose neighbours are returned.
     * @return a Set of neighbours of flight.
     */
    public Set<Flight> getConnectingFlights(Flight flight) {
        return this.flightsMap.get(flight);
    }

    /**
     * Returns the number of flightSetHashMap in this FlightsGraph.
     *
     * @return The number of flightSetHashMap in this FlightsGraph.
     */
    public int getNumFlights() {
        return getFlights().size();
    }

    /**
     * Adds a new flight to this FlightsGraph.
     *
     * @param flight the flight that is being added.
     */
    public void addFlight(Flight flight) {
        this.flightsMap.put(flight, new HashSet<Flight>());

        refreshGraph();
    }

    /**
     * Adds a collection of flights to this FlightsGraph.
     *
     * @param flights the collection of flights that are being added.
     */
    public void addFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            this.addFlight(flight);
        }
    }

    /**
     * Removes a flight from this FlightsGraph.
     *
     * @param flight the flight that is being removed.
     */
    public void removeFlight(Flight flight) {
        this.flightsMap.remove(flight);
        Log.d("TEST", this.flightsMap.containsKey(flight) ? "EXISTS" : "NOPE");
        refreshGraph();
    }

    /**
     * Adds flight2 as a connecting flight from flight1's destination.
     * If flight2 has already been defined as a connecting flight it operation
     * is ignored.
     *
     * @param flight1 The flight that has a connecting flight.
     * @param flight2 The flight that is the connecting flight.
     */
    public void addConnectingFlight(Flight flight1, Flight flight2) {

        if (flight1 != flight2) {
            //By definition, HashSets do not insert duplicate values.
            this.flightsMap.get(flight1).add(flight2);
        }
    }

    // Update the graph to include and new flights that have been added/removed.
    private void refreshGraph() {
        for (Flight key : this.flightsMap.keySet()) {
            for (Flight key1 : this.flightsMap.keySet()) {
                if (key != key1) {
                    if (key.getDestination().equals(key1.getOrigin())) {
                        this.addConnectingFlight(key, key1);
                    }
                }
            }
        }
    }

    @Override
    /**
     * Returns a string representation of this FlightGraph object.
     *
     * @return String representing the interconnection of flights in this Graph.
     */
    public String toString() {

        String result = "";

        for (Flight node : getFlights()) {
            result += node + " is adjacent to: ";
            for (Flight flight : getConnectingFlights(node)) {
                result += flight + " ";
            }
            result += "\n";
        }
        return result;
    }
}