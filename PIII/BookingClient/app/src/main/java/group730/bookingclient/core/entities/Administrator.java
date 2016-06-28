package group730.bookingclient.core.entities;

import group730.bookingclient.core.services.UserManager;

/**
 * Administrator defines a user with elevated permissions. Additionally, the
 * administrator is a subclass of the {@link User} class.
 */
public class Administrator extends User {

    /**
     * Creates an Administrator object.
     *
     * @param firstName String represents the first name of the Administrator.
     * @param lastName  String represents the last name of Administrator.
     * @param email     String represents the last name of the Administrator.
     * @param password  String represents the mPassword of the Administrator.
     */
    public Administrator(
            String firstName, String lastName, String email, String password) {
        super(UserManager.USER_TYPE.ADMINISTRATOR, firstName, lastName, email,
                password);
    }

    /**
     * Returns String representation of this administrator in the format:
     * mLastName,mFirstName,mEmail
     *
     * @return A string representation of administrator.
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s",
                mLastName,
                mFirstName,
                mEmail);
    }
}
