package group730.bookingclient.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.entities.Itinerary;
import group730.bookingclient.core.services.FlightManager;

/**
 * Handles generation of itineraries for flights originating from an origin city
 * and arriving at a given destination.
 */
public class ItineraryFactory {

    /**
     * Generates a list of itineraries that leave an origin city at a given
     * departureDateTime and arrive at a destination city.
     *
     * @param departureDateTime The DateTime at which the first flight in the
     *                          trip leaves.
     * @param origin            The city from which the first flight in the trip
     *                          leaves.
     * @param destination       The city at which the trip is ending.
     * @return A collection of itineraries representing the possible trips
     * leaving the origin at a given departureDateTime and a arriving at a
     * final destination.
     */
    public static List<Itinerary> generateItineraries(String departureDateTime,
                                                      String origin,
                                                      String destination) {

        List<Itinerary> itineraries = new ArrayList<>();

        List<Flight> flights = FlightManager.getFlightsFrom(departureDateTime,
                origin);

        for (Flight flight : flights) {
            searchForItineraries(itineraries,
                    new ArrayList<Flight>(),
                    flight, destination);
        }

        return itineraries;
    }

    // Recursive helper function that generates a collection of itineraries
    // given a collection of itineraries and a collection representing the
    // current path of the connecting flights. Additionally, it takes a current
    // flight and a final destination.
    private static void searchForItineraries(List<Itinerary> itineraries,
                                             List<Flight> workingPath,
                                             Flight flight,
                                             String destination) {

        List<Flight> newPath = new ArrayList<>(workingPath); //Copy path
        newPath.add(flight); // Add new node to it so only the current
        // flight is being tested.

        if (flight.getDestination().equals(destination)) { // Final dest.?
            itineraries.add(new Itinerary(new ArrayList<>(newPath)));
        } else {
            Set<Flight> flights = FlightManager.getFlightsGraph()
                    .getConnectingFlights(flight);

            for (Flight f1 : flights) {
                long totalMillis = f1.getDepartureDateTime().getTime()
                        - flight.getArrivalDateTime().getTime();
                long timeSpan = (int) ((totalMillis / (1000 * 60)) % 60);

                // Check if this node does not already exist in path and
                // the new flights departure time is after the current flights
                // arrival time. Additionally, check to ensure that the stop
                // over is less than the 6 hours specified.
                if (!newPath.contains(f1) &&
                        f1.getDepartureDateTime()
                                .after(flight.getArrivalDateTime()) &&
                        timeSpan < 360 && f1.getNumSeats() > 0) {

                    // Perform recursive call to continue traversing.
                    searchForItineraries(itineraries, newPath, f1, destination);
                }
            }
        }
    }
}
