package group730.bookingclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import group730.bookingclient.R;
import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.services.FlightManager;
import group730.bookingclient.core.utils.DateTimeUtils;
import group730.bookingclient.fragments.FlightsFragment;
import group730.bookingclient.misc.FlightsAdapter;

/**
 * Activity to edit flight information.
 */
public class EditFlightActivity extends AppCompatActivity {

    private Flight mFlight;
    private EditText mFlightNumber;
    private EditText mFlightDeparture;
    private EditText mFlightArrival;
    private EditText mAirlineName;
    private EditText mFlightOrigin;
    private EditText mFlightDestination;
    private EditText mFlightCost;
    private EditText mAvailableSeats;

    /**
     * Creates the layout of this activity. Displays what the selected flight
     * information is. Administrator can edit these flights.
     *
     * @param savedInstanceState default android argument.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flight);

        Intent intent = getIntent();
        mFlight = (Flight) intent.getExtras().get("Flight");

        mFlightNumber = (EditText) findViewById(R.id.flightNuma);
        mFlightDeparture = (EditText) findViewById(R.id.flightDepartureDate);
        mFlightArrival = (EditText) findViewById(R.id.flightArrivalDate);
        mAirlineName = (EditText) findViewById(R.id.airlineNamea);
        mFlightOrigin = (EditText) findViewById(R.id.flightOrigina);
        mFlightDestination = (EditText) findViewById(R.id.flightDestinationa);
        mFlightCost = (EditText) findViewById(R.id.flightPricea);
        mAvailableSeats = (EditText) findViewById(R.id.flightAvailableSeatsa);

        //mFlightNumber.setText(mFlight.getFlightNumber());
        mFlightDeparture.setText(DateTimeUtils.convertDateTimeToString(
                mFlight.getDepartureDateTime()));
        mFlightArrival.setText(DateTimeUtils.convertDateTimeToString(
                mFlight.getArrivalDateTime()));
        mAirlineName.setText(mFlight.getAirline());
        mFlightOrigin.setText(mFlight.getOrigin());
        mFlightDestination.setText(mFlight.getDestination());
        mFlightCost.setText(mFlight.getCost() + "");
        //mAvailableSeats.setText(mFlight.getNumSeats());
    }

    /**
     * Goes back to the previous activity before user goes into this one.
     *
     * @param view the view from which this handler is invoked.
     */
    public void goBack(View view) {
        this.finish();
    }

    /**
     * Saves the information inputted by admin to the flight being edited.
     *
     * @param view the view from which this handler is invoked.
     */
    public void save(View view) {
        FlightManager.getFlightsGraph().removeFlight(
                FlightManager.getFlightsGraph().getFlight(
                        mFlight.getFlightNumber()));

        //mFlight.setFlightNumber(Integer.parseInt(
        //        mFlightNumber.getText().toString()));
        mFlight.setDepartureDateTime(DateTimeUtils.convertToDateTime(
                mFlightDeparture.getText().toString()));
        mFlight.setArrivalDateTime(DateTimeUtils.convertToDateTime(
                mFlightArrival.getText().toString()));
        mFlight.setAirline(mAirlineName.getText().toString());
        mFlight.setOrigin(mFlightOrigin.getText().toString());
        mFlight.setDestination(mFlightDestination.getText().toString());
        mFlight.setCost(Double.parseDouble(mFlightCost.getText().toString()));
        //mFlight.setNumSeats(Integer.parseInt(
        //        mAvailableSeats.getText().toString()));
        FlightManager.getFlightsGraph().addFlight(mFlight);

        FlightsFragment.setFlightsAdapter(new FlightsAdapter(view.getContext(),
                new ArrayList<>(FlightManager.getFlightsGraph().getFlights())));

        FlightManager.saveFlights();

        Toast.makeText(this, "Updated flight", Toast.LENGTH_SHORT).show();

        this.finish();
    }

}
