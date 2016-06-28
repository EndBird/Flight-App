package group730.bookingclient.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import group730.bookingclient.R;
import group730.bookingclient.activities.SearchResultsActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private TextView mDepartureDateField;
    private TextView mOriginField;
    private TextView mArrivalField;
    private RadioGroup mSearchType;

    /**
     * Creates and returns a new instance of this fragment.
     *
     * @return A new instance of fragment AdminToolsFragment.
     */
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public SearchFragment() {
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
     * Inflates the corresponding XML and generates the required fields to
     * allow for user input with regard to a search operations parameters.
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
        final View view = inflater.inflate(R.layout.fragment_search, container,
                false);

        mDepartureDateField = (TextView) view.findViewById(
                R.id.departure_date_field);
        mOriginField = (TextView) view.findViewById(R.id.origin_field);
        mArrivalField = (TextView) view.findViewById(R.id.arrival_field);
        mSearchType = (RadioGroup) view.findViewById(R.id.search_type);
        Button mSearch = (Button) view.findViewById(R.id.search);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchParams = new Intent(view.getContext(),
                        SearchResultsActivity.class);
                searchParams.putExtra("Departure",
                        mDepartureDateField.getText());
                searchParams.putExtra("Origin", mOriginField.getText());
                searchParams.putExtra("Arrival", mArrivalField.getText());
                searchParams.putExtra("SearchType",
                        mSearchType.getCheckedRadioButtonId() ==
                                R.id.flights ? "Flights" : "Trips");
                startActivity(searchParams);
            }
        });

        return view;
    }
}
