package group730.bookingclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import group730.bookingclient.R;
import group730.bookingclient.core.entities.Client;
import group730.bookingclient.core.entities.User;
import group730.bookingclient.core.services.UserManager;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsFragment extends Fragment {

    private boolean mEditable;
    private Spinner mUserDropdown;
    private EditText mFirstNameView;
    private EditText mLastNameView;
    private EditText mEmailView;
    private TextView mAddressTitle;
    private EditText mAddressView;
    private TextView mPasswordTitle;
    private EditText mPasswordView;
    private TextView mCCardTitle;
    private EditText mCCardView;
    private Button mCancel;
    private Button mToggleEditable;

    private static boolean sSelectorInitialized;
    private static User sCurrentUser;
    private static ArrayList<String> sUsers;

    /**
     * Creates and returns a new instance of this fragment.
     *
     * @return A new instance of fragment AdminToolsFragment.
     */
    public static UserDetailsFragment newInstance() {
        UserDetailsFragment fragment = new UserDetailsFragment();
        return fragment;
    }

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Gets the current user for edit.
     *
     * @return User the current user.
     */
    public static User getEditableUser() {
        if (sCurrentUser == null)
            sCurrentUser = UserManager.getInstance().getCurrentUser();
        return sCurrentUser;
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
     * allow for user input with regard to a users personal and billing
     * information.
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
        View view = inflater.inflate(
                R.layout.fragment_user_details,
                container,
                false);

        mUserDropdown = (Spinner) view.findViewById(R.id.usersDropdown);
        mFirstNameView = (EditText) view.findViewById(R.id.firstNameField);
        mLastNameView = (EditText) view.findViewById(R.id.lastNameField);
        mEmailView = (EditText) view.findViewById(R.id.emailField);
        mPasswordTitle = (TextView) view.findViewById(R.id.passwordTitle);
        mPasswordView = (EditText) view.findViewById(R.id.passwordField);
        mAddressTitle = (TextView) view.findViewById(R.id.addressTitle);
        mAddressView = (EditText) view.findViewById(R.id.addressField);
        mCCardTitle = (TextView) view.findViewById(R.id.creditCardTitle);
        mCCardView = (EditText) view.findViewById(R.id.creditCardField);

        mCancel = (Button) view.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditable = !mEditable;

                toggleTextEditable();
                updateFields();
            }
        });

        mToggleEditable = (Button) view.findViewById(R.id.toggleEditable);
        mToggleEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditable = !mEditable;

                toggleTextEditable();

                if (mToggleEditable.getText().toString() == getString(R.string.button_edit))
                    saveUser();
            }
        });

        if (UserManager.getInstance().getCurrentUser().getUserType() ==
                UserManager.USER_TYPE.ADMINISTRATOR) {
            mUserDropdown.setVisibility(View.VISIBLE);

            sUsers = new ArrayList<>(UserManager.getInstance()
                    .getUsers().keySet());

            ArrayAdapter<String> users;
            users = new ArrayAdapter<>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    sUsers);

            mUserDropdown.setAdapter(users);
            mUserDropdown.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent,
                                                   View view, int position, long id) {
                            if (!sSelectorInitialized) {
                                int p = ((ArrayAdapter) mUserDropdown.getAdapter())
                                        .getPosition(getEditableUser().getEmail());

                                mUserDropdown.setSelection(p);

                                sSelectorInitialized = true;
                                return;
                            }

                            String email = mUserDropdown.getAdapter().getItem(
                                    position).toString();
                            sCurrentUser = UserManager.getInstance()
                                    .getUsers().get(email);
                            updateFields();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
        } else {
            mUserDropdown.setVisibility(View.GONE);
        }

        updateFields();
        return view;
    }

    private void toggleTextEditable() {
        mUserDropdown.setEnabled(!mEditable);
        mFirstNameView.setEnabled(mEditable);
        mLastNameView.setEnabled(mEditable);
        mEmailView.setEnabled(mEditable);
        mPasswordTitle.setVisibility(mEditable ? View.VISIBLE : View.GONE);
        mPasswordView.setVisibility(mPasswordTitle.getVisibility());
        mAddressView.setEnabled(mEditable);
        mCCardView.setEnabled(mEditable);
        mToggleEditable.setText(mEditable ? R.string.button_save : R.string.button_edit);
        mCancel.setVisibility(mEditable ? View.VISIBLE : View.INVISIBLE);
    }

    private void updateFields() {
        mFirstNameView.setText(getEditableUser().getFirstName());
        mLastNameView.setText(getEditableUser().getLastName());
        mEmailView.setText(getEditableUser().getEmail());
        if (getEditableUser().getUserType() == UserManager.USER_TYPE.CLIENT) {
            Client client = (Client) getEditableUser();

            mAddressTitle.setVisibility(View.VISIBLE);
            mAddressView.setVisibility(mAddressTitle.getVisibility());
            mAddressView.setText(client.getAddress());

            mCCardTitle.setVisibility(View.VISIBLE);
            mCCardView.setVisibility(mCCardTitle.getVisibility());
            mCCardView.setText(client.getCreditCardNumber());
        } else {
            mAddressTitle.setVisibility(View.GONE);
            mAddressView.setVisibility(mAddressTitle.getVisibility());

            mCCardTitle.setVisibility(View.GONE);
            mCCardView.setVisibility(mCCardTitle.getVisibility());
        }
    }

    private void saveUser() {
        try {
            UserManager.getInstance().getUsers().remove(
                    getEditableUser().getEmail());
            getEditableUser().setFirstName(mFirstNameView.getText().toString());
            getEditableUser().setLastName(mLastNameView.getText().toString());
            getEditableUser().setEmail(mEmailView.getText().toString());
            if (getEditableUser().getUserType() ==
                    UserManager.USER_TYPE.CLIENT) {
                Client client = (Client) getEditableUser();
                client.setAddress(mAddressView.getText().toString());
                client.setCreditCardNumber(mCCardView.getText().toString());
            }
            getEditableUser().setPassword(mPasswordView.getText().toString());
            UserManager.getInstance().getUsers().put(
                    getEditableUser().getEmail(),
                    getEditableUser());

            if (UserManager.getInstance().getCurrentUser().getUserType() ==
                    UserManager.USER_TYPE.ADMINISTRATOR) {
                sUsers = new ArrayList<>(UserManager.getInstance()
                        .getUsers().keySet());

                ArrayAdapter<String> users;
                users = new ArrayAdapter<>(getActivity()
                        .getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        sUsers);

                mUserDropdown.setAdapter(users);
            }

            UserManager.getInstance().saveUsers();

            Toast.makeText(getActivity(),
                    "Saved user.",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(),
                    "Failed to save user.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
