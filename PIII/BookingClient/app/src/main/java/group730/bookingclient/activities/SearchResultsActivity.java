package group730.bookingclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group730.bookingclient.R;
import group730.bookingclient.core.entities.Client;
import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.entities.Itinerary;
import group730.bookingclient.core.services.FlightManager;
import group730.bookingclient.core.services.ItineraryManager;
import group730.bookingclient.core.services.UserManager;
import group730.bookingclient.core.utils.ItineraryFactory;
import group730.bookingclient.fragments.UserDetailsFragment;
import group730.bookingclient.misc.BookingAdapter;
import group730.bookingclient.misc.FlightsAdapter;

/**
 * Class to create the search results activity.
 */
public class SearchResultsActivity extends AppCompatActivity {

    private String mSearchType;

    private BookingAdapter mItineraries;
    private FlightsAdapter mFlightsAdapter;

    /**
     * Creates the search results screen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ListView mFlightsList;
        mFlightsList = (ListView) this.findViewById(R.id.flightsQueryList);

        ListView mItinerariesList;
        mItinerariesList = (ListView) this.findViewById(R.id.tripsQueryList);

        Intent intent = getIntent();
        String departureDate = intent.getExtras().get("Departure").toString();
        String origin = intent.getExtras().get("Origin").toString();
        String arrival = intent.getExtras().get("Arrival").toString();
        mSearchType = intent.getExtras().get("SearchType").toString();

        if (mSearchType.equals("Flights")) {
            mFlightsList.setVisibility(View.VISIBLE);
            mItinerariesList.setVisibility(View.GONE);
            ArrayList<Flight> flights;
            flights = (ArrayList<Flight>) FlightManager.getFlightsFromTo(
                    departureDate,
                    origin,
                    arrival);

            mFlightsAdapter = new FlightsAdapter(this, flights);

            mFlightsList.setAdapter(mFlightsAdapter);

            registerForContextMenu(mFlightsList);
        } else {
            mFlightsList.setVisibility(View.GONE);
            mItinerariesList.setVisibility(View.VISIBLE);
            ArrayList<Itinerary> itineraries;
            itineraries = (ArrayList<Itinerary>)
                    ItineraryFactory.generateItineraries(departureDate,
                            origin, arrival);

            mItineraries = new BookingAdapter(this, itineraries);

            mItinerariesList.setAdapter(mItineraries);

            registerForContextMenu(mItinerariesList);
        }
    }

    /**
     * todo:
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.flightsQueryList) {
            getMenuInflater().inflate(R.menu.context_menu_flights_query, menu);
        } else if (v.getId() == R.id.tripsQueryList) {
            getMenuInflater().inflate(R.menu.context_menu_trip_query, menu);
        }
    }

    /**
     * todo:
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.addItinerary) {
            Itinerary itinerary = mItineraries.getItem(info.position);

            addItinerary(itinerary);

            return true;
        } else if (item.getItemId() == R.id.addFlight) {
            Flight flight = mFlightsAdapter.getItem(info.position);
            List<Flight> flights = new ArrayList<>();

            flights.add(flight);

            addItinerary(new Itinerary(flights));
        }
        return false;
    }

    private void addItinerary(Itinerary itinerary) {
        if (UserDetailsFragment.getEditableUser().getUserType() ==
                UserManager.USER_TYPE.CLIENT) {
            Client client = (Client) UserDetailsFragment.getEditableUser();

            for (Flight flight : itinerary.getConnectingFlights()) {
                if (mSearchType.equals("Flights"))
                    mFlightsAdapter.remove(flight);
                FlightManager.getFlightsGraph().removeFlight(flight);
                flight.setNumSeats(flight.getNumSeats() - 1);
                FlightManager.getFlightsGraph().addFlight(flight);
                if (mSearchType.equals("Flights"))
                    mFlightsAdapter.add(flight);
            }

            client.bookItinerary(itinerary);

            UserManager.getInstance().saveUsers();

            Toast.makeText(this,
                    "Added booking.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,
                    "User is an administrator, cannot add booking.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * todo:
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

    /**
     * Creates buttons for the user to be able to sort itineraries/flights
     * by either cost or travel time
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_filter_cost_low) {
            if (mSearchType.equals("Flights")) {
                FlightManager.sortCostLow(mFlightsAdapter.getItems());
            } else {
                ItineraryManager.sortCostLow(mItineraries.getItems());
            }
        } else if (id == R.id.action_filter_cost_high) {
            if (mSearchType.equals("Flights")) {
                FlightManager.sortCostHigh(mFlightsAdapter.getItems());
            } else {
                ItineraryManager.sortCostHigh(mItineraries.getItems());
            }
        } else if (id == R.id.action_filter_time_low) {
            if (mSearchType.equals("Flights")) {
                FlightManager.sortTimeLow(mFlightsAdapter.getItems());
            } else {
                ItineraryManager.sortTimeLow(mItineraries.getItems());
            }
        } else if (id == R.id.action_filter_time_high) {
            if (mSearchType.equals("Flights")) {
                FlightManager.sortTimeHigh(mFlightsAdapter.getItems());
            } else {
                ItineraryManager.sortTimeHigh(mItineraries.getItems());
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
