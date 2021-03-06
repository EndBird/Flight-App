package group730.bookingclient;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import group730.bookingclient.core.entities.Administrator;
import group730.bookingclient.core.entities.User;
import group730.bookingclient.core.services.DataManager;
import group730.bookingclient.core.services.UserManager;

/**
 * Acts as a global controller for the Application.
 */
public class BookingClient {


    private static BookingClient sBookingClient ;
    private static Context sContext;

    /**
     * Returns the instance of BookingClient class, or create it if
     * it does not exist yet.
     *
     * @return BookingClient the booking client using the app.
     */
    public static BookingClient GetInstance() {
        if (sBookingClient == null)
            sBookingClient = new BookingClient();
        return sBookingClient;
    }

    private BookingClient() {
        //initialize();
    }

    /**
     * Intializes the app for a new client, by loading users and flights data.
     *
     * @param context
     */
    public void initialize(Context context) {
        sContext = context;

        generateDummyData();

        DataManager.getInstance().loadUsers();
        DataManager.getInstance().loadPasswords();
        DataManager.getInstance().loadFlights();
    }

    private void generateDummyData() {

        List<User> users = new ArrayList<>();
        users.add(new Administrator("Root", "User", "root@root.com", "root"));
        UserManager.getInstance().addUsers(users);
    }

    public Context getContext() {
        return sContext;
    }
}
