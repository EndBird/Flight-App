package group730.bookingclient.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import group730.bookingclient.R;
import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.utils.DateTimeUtils;

/**
 * An extension to the {@link ArrayAdapter} allowing a collection to be bound to
 * a {@link ListView} widget. Adapter item collection is of type {@link Flight}.
 * <p/>
 * Allows for the modification and visual control over the style of the items
 * that are displayed by the corresponding {@link ListView}.
 */
public class FlightsAdapter extends ArrayAdapter<Flight> {

    private ArrayList<Flight> mFlightsList;
    private Context context;

    /**
     * Primary constructor for this adapter, assigns values to properties.
     *
     * @param context     the context of this adapter.
     * @param flightsList the collection of items corresponding to this adapter.
     */
    public FlightsAdapter(Context context, ArrayList<Flight> flightsList) {
        super(context, R.layout.row_layout_bookings, flightsList);
        this.mFlightsList = flightsList;
        this.context = context;
    }

    /**
     * Represents the middle man between a list and the container for an item,
     * within the list.
     *
     * @param position Position of the item within the list.
     * @param v The view for the current item.
     * @param parent The view representing the list.
     * @return Returns the updated view.
     */
    @Override
    public View getView(int position, View v, ViewGroup parent) {

        Flight flight = mFlightsList.get(position);

        FlightItemViewModel vm = new FlightItemViewModel();

        if (v == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.row_layout_flights, parent, false);

            vm.mAirline = (TextView) v.findViewById(R.id.mAirline);
            vm.mFlightNumber = (TextView) v.findViewById(R.id.mFlightNumber);
            vm.mTripOverview = (TextView) v.findViewById(R.id.tripOverview);
            vm.mDepartureDate = (TextView) v.findViewById(R.id.departureDate);
            vm.mArrivalDate = (TextView) v.findViewById(R.id.arrivalDate);
            vm.mTravelTime = (TextView) v.findViewById(R.id.travelTime);
            vm.mCost = (TextView) v.findViewById(R.id.cost);
            vm.mSeats = (TextView) v.findViewById(R.id.flightAvailableSeatsa);

            v.setTag(vm);
        } else {
            vm = (FlightItemViewModel) v.getTag();
        }

        vm.mAirline.setText("Airline: " + flight.getAirline());
        vm.mFlightNumber.setText("Flight number: " + flight.getFlightNumber());
        vm.mTripOverview.setText(flight.getOrigin() + " -> "
                + flight.getDestination());
        vm.mDepartureDate.setText("Departure: "
                + DateTimeUtils.convertDateTimeToString(
                flight.getDepartureDateTime()));
        vm.mArrivalDate.setText("Arrival: "
                + DateTimeUtils.convertDateTimeToString(
                flight.getArrivalDateTime()));
        vm.mTravelTime.setText("Travel Time: "
                + DateTimeUtils.convertTimeToString(flight.getTimeInMillis()));
        //vm.mCost.setText(String.format("Cost: %.2f", flight.getCost()));
        vm.mSeats.setText("Seats: " + flight.getNumSeats());

        return v;
    }

    /**
     * Gets the list of flights from mFlightsList.
     *
     * @return ArrayList of Flight
     */
    public ArrayList<Flight> getItems() {
        return this.mFlightsList;
    }
}
