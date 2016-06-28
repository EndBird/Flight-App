package group730.bookingclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import group730.bookingclient.R;
import group730.bookingclient.core.entities.Client;
import group730.bookingclient.core.entities.Itinerary;
import group730.bookingclient.core.services.UserManager;
import group730.bookingclient.misc.BookingAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingsFragment extends Fragment {

    private ListView mBookingsList;

    /**
     * Creates and returns a new instance of this fragment.
     *
     * @return A new instance of fragment AdminToolsFragment.
     */
    public static BookingsFragment newInstance() {
        BookingsFragment fragment = new BookingsFragment();

        return fragment;
    }

    public BookingsFragment() {
        // Required empty public constructor
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
     * Displays the bookings for the selected client
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
        View view = inflater.inflate(R.layout.fragment_bookings, container,
                false);

        TextView mNotEditable;
        mNotEditable = (TextView) view.findViewById(R.id.label_not_editable);
        TextView mBookingsForHeader;
        mBookingsForHeader = (TextView) view.findViewById(R.id.booking_for);
        mBookingsList = (ListView) view.findViewById(R.id.bookings_list);

        if (UserDetailsFragment.getEditableUser().getUserType() ==
                UserManager.USER_TYPE.CLIENT) {
            Client currentUser = (Client) UserDetailsFragment.getEditableUser();

            mBookingsForHeader.setText(getText(R.string.label_bookings_for) + " " +
                    currentUser.getFirstName() + " "
                    + currentUser.getLastName());


            ArrayList<Itinerary> itineraries;
            itineraries = (ArrayList) currentUser.getBookedItineraries();

            BookingAdapter bookedTrips;
            bookedTrips = new BookingAdapter(getActivity()
                    .getApplicationContext(), itineraries);

            mBookingsList.setAdapter(bookedTrips);

            registerForContextMenu(mBookingsList);

            mNotEditable.setVisibility(View.GONE);
            mBookingsForHeader.setVisibility(View.VISIBLE);
            mBookingsList.setVisibility(mBookingsForHeader.getVisibility());
        } else {
            mNotEditable.setVisibility(View.VISIBLE);
            mBookingsForHeader.setVisibility(View.VISIBLE);
            mBookingsList.setVisibility(mBookingsForHeader.getVisibility());
        }
        return view;
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
        if (v.getId() == R.id.bookings_list) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.context_menu_booking_list, menu);
        }
    }

    /**
     * Removes booking from selected client file
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.delete) {
            ArrayAdapter<Itinerary> itineraries;
            itineraries = (ArrayAdapter) mBookingsList.getAdapter();
            itineraries.remove(itineraries.getItem(info.position));

            UserManager.getInstance().saveUsers();

            Toast.makeText(getActivity(), "Removed booking.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
