package group730.bookingclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.io.IOException;

import group730.bookingclient.BookingClient;
import group730.bookingclient.R;
import group730.bookingclient.core.entities.User;
import group730.bookingclient.core.services.DataManager;
import group730.bookingclient.core.services.UserManager;

/**
 * A screen that offers login via mEmail/mPassword.
 */
public class LoginActivity extends Activity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    /**
     * Creates the login screen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        BookingClient.GetInstance().initialize(this);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.mEmail);
        mPasswordView = (EditText) findViewById(R.id.mPassword);
    }

    /**
     * Attempts to sign in with the account specified by the login form.
     * If there are form errors (invalid mEmail, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     *
     * @param view the view from which the handler is invoked.
     */
    public void signInClicked(View view) throws IOException {
        boolean cancel = false;
        View focusView = null;

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (!isExistingEmail(email)) {
            mEmailView.setError("This Email does not exist in the database.");
            focusView = mEmailView;
            cancel = true;
        } else {
            //Validate Sign in
            User user = UserManager.getInstance()
                    .validateAndReturnUser(email, password);

            if (user == null) {
                mPasswordView.setError("The entered Password is incorrect.");
                focusView = mPasswordView;
                cancel = true;
            } else {
                UserManager.getInstance().setCurrentUser(user);
                DataManager.getInstance().saveLoggedInUser(user.getEmail());
                startActivity(new Intent(this, HomeActivity.class));
            }
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            this.finish();
        }
    }

    /**
     * Checks to see if the email exists within the database.
     *
     * @param email the email of which the existence will be checked.
     * @return true if email is the email exisits within the database.
     */
    private boolean isExistingEmail(String email) {
        return UserManager.getInstance().getUsers().containsKey(email);
    }
}

