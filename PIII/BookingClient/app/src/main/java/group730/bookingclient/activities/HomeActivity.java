package group730.bookingclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import group730.bookingclient.R;
import group730.bookingclient.core.services.DataManager;
import group730.bookingclient.core.services.UserManager;
import group730.bookingclient.fragments.AdminToolsFragment;
import group730.bookingclient.fragments.BookingsFragment;
import group730.bookingclient.fragments.FlightsFragment;
import group730.bookingclient.fragments.NavigationDrawerFragment;
import group730.bookingclient.fragments.SearchFragment;
import group730.bookingclient.fragments.UserDetailsFragment;

/**
 * Class to create the home page of the app.
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    /**
     * Creates the home page of the app.
     *
     * @param savedInstanceState
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_drawer);
        setTitle(R.string.app_name);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.nav_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    /**
     * Creates the drawer on the home page, with a selection of the operations which the user can
     * execute (ie. Search, User Details, Bookings ...)
     *
     * @param position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (UserManager.getInstance().getCurrentUser().getUserType() ==
                UserManager.USER_TYPE.ADMINISTRATOR) {
            switch (position) {
                default:
                case 0:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            SearchFragment.newInstance()).commit();
                    break;
                case 1:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            UserDetailsFragment.newInstance()).commit();
                    break;
                case 2:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            BookingsFragment.newInstance()).commit();
                    break;
                case 3:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FlightsFragment.newInstance()).commit();
                    break;
                case 4:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            AdminToolsFragment.newInstance()).commit();
                    break;
                case 5:
                    Log.d("SIGN OUT", "Logging out user.");
                    DataManager.getInstance().deleteLoggedInUser(
                            UserManager.getInstance().getCurrentUser()
                                    .getEmail());
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    break;
            }
        } else {
            switch (position) {
                default:
                case 0:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            SearchFragment.newInstance()).commit();
                    break;
                case 1:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            UserDetailsFragment.newInstance()).commit();
                    break;
                case 2:
                    fragmentManager.beginTransaction().replace(R.id.container,
                            BookingsFragment.newInstance()).commit();
                    break;
                case 3:
                    Log.d("SIGN OUT", "Logging out user.");
                    DataManager.getInstance().deleteLoggedInUser(
                            UserManager.getInstance().getCurrentUser()
                                    .getEmail());
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    break;
            }
        }
    }
}
