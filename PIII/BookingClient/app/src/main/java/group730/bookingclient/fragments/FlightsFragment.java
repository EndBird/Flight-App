package group730.bookingclient.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import group730.bookingclient.R;
import group730.bookingclient.activities.EditFlightActivity;
import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.services.FlightManager;
import group730.bookingclient.misc.FlightsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightsFragment extends Fragment {

    private static FlightsAdapter mFlightsAdapter;
    private static ListView mFlightsList;

    public FlightsFragment() {
        // Required empty public constructor
    }

    /**
     * Creates and returns a new instance of this fragment.
     *
     * @return A new instance of fragment AdminToolsFragment.
     */
    public static FlightsFragment newInstance() {
        FlightsFragment fragment = new FlightsFragment();
        return fragment;
    }

    /**
     * todo:
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * todo:
     *
     * @param inflater           Represents XML inflater class.
     * @param container          Represents the container from which this view
     *                           is being generated.
     * @param savedInstanceState A bundle object represents the previous state
     *                           of this view.
     * @return The updated view object.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_flights, container, false);

        mFlightsList = (ListView) view.findViewById(R.id.flights);

        mFlightsAdapter = new FlightsAdapter(view.getContext(),
                new ArrayList<>(FlightManager.getFlightsGraph().getFlights()));

        mFlightsList.setAdapter(mFlightsAdapter);

        registerForContextMenu(mFlightsList);

        return view;
    }

    /**
     * Handles the appropriate inflation of a context menu based on the
     * widget that has invoked this method.
     *
     * @param menu Object representing the context menu.
     * @param v The view from which the context menu was invoked
     * @param menuInfo Any indormation about the given context menu.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.flights) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.context_menu_flights_list, menu);
        }
    }

    /**
     * Handles the edit and delete functions of an item within the adapter.
     *
     * @param item the item from the context menu that was selected.
     * @return the result of any operation as a concequence of the selection.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.edit) {
            Flight flight = mFlightsAdapter.getItem(info.position);

            Intent intent = new Intent(getContext(), EditFlightActivity.class);
            intent.putExtra("Flight", flight);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.delete) {
            Flight flight = mFlightsAdapter.getItem(info.position);

            mFlightsAdapter.remove(flight);
            FlightManager.getFlightsGraph().removeFlight(flight);
        }
        return false;
    }

    /**
     * Returns the instance of the FlightAdapter of this fragment.
     *
     * @return The FlightAdapter instance.
     */
    public static FlightsAdapter getFlightAdapter() {
        return mFlightsAdapter;
    }

    /**
     * Sets the instance of the FlightAdapter of this fragment.
     *
     * @param adapter A FlightAdapter object that is being used.
     */
    public static void setFlightsAdapter(FlightsAdapter adapter) {
        mFlightsAdapter = adapter;
        mFlightsList.setAdapter(adapter);
    }
}
