package group730.bookingclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import group730.bookingclient.R;
import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.entities.User;
import group730.bookingclient.core.services.DataManager;
import group730.bookingclient.core.services.FlightManager;
import group730.bookingclient.core.services.UserManager;

/**
 * Fragment that populates the view with relevant administrator widgets that
 * allow for privileged function execution.
 */
public class AdminToolsFragment extends Fragment {

    private TextView mUsersFilePath;
    private TextView mFlightsFilePath;

    /**
     * Constructs the AdminToolsFragment object.
     */
    public AdminToolsFragment() {
        // Required empty public constructor
    }

    /**
     * Creates and returns a new instance of this fragment.
     *
     * @return A new instance of fragment AdminToolsFragment.
     */
    public static AdminToolsFragment newInstance() {
        return new AdminToolsFragment();
    }

    /**
     * See the parent class {@link Fragment} for onCreate();
     *
     * @param savedInstanceState The
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates buttons for uploading users and flights as an administrator,
     * and adds users/flights from the file passed in, to DataManager/
     * UserManager/ FlightManager.
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
        View view = inflater.inflate(R.layout.fragment_admin_tools,
                container, false);

        mUsersFilePath = (TextView) view.findViewById(R.id.usersFilePath);
        Button uploadUsersButton = (Button) view.findViewById(R.id.uploadUsers);
        uploadUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<User> users = DataManager.getInstance().parseUsers(
                            mUsersFilePath.getText().toString());

                    UserManager.getInstance().addUsers(users);

                    UserManager.getInstance().saveUsers();

                    Toast.makeText(getActivity(),
                            "Added/updated " + users.size() + " users.",
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    mUsersFilePath.setError("Unable to find requested file.");
                }
            }
        });

        mFlightsFilePath = (TextView) view.findViewById(R.id.flightsFilePath);
        Button uploadFlights = (Button) view.findViewById(R.id.uploadFlights);
        uploadFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Flight> flights;
                    flights = DataManager.getInstance().parseFlights(
                            mFlightsFilePath.getText().toString());

                    FlightManager.getFlightsGraph().addFlights(flights);

                    FlightManager.saveFlights();

                    Toast.makeText(getActivity(),
                            "Added/updated " + flights.size() + " flights.",
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    mFlightsFilePath.setError("Unable to find requested file.");
                }
            }
        });
        return view;
    }
}
