package group730.bookingclient.core.entities;

import java.io.Serializable;

import group730.bookingclient.core.services.UserManager;

public abstract class User implements Serializable {

    private static final long serialVersionUID = 1999L;

    protected UserManager.USER_TYPE mUserType;
    protected String mFirstName;
    protected String mLastName;
    protected String mEmail;
    protected String mPassword;

    /**
     * Creates a User object.
     *
     * @param userType  Enum that represents the status of tbe user.
     * @param firstName String represents the first name of User.
     * @param lastName  String represents the last name of the User.
     * @param email     String represents the Email of the User.
     * @param password  String represents the Password of the User.
     */
    public User(UserManager.USER_TYPE userType, String firstName,
                String lastName, String email, String password) {
        this.mUserType = userType;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
        this.mPassword = password;
    }

    /**
     * Returns the access level of this user.
     *
     * @return The access level as a {@see USER_TYPE} enum.
     */
    public UserManager.USER_TYPE getUserType() {
        return mUserType;
    }

    /**
     * Sets this users access level to a given access level from
     * {@see USER_TYPE}
     *
     * @param userType The access level as a {@see USER_TYPE} enum.
     */
    public void setUserType(UserManager.USER_TYPE userType) {
        this.mUserType = userType;
    }

    /**
     * Returns the first name of the User.
     *
     * @return String that is the mFirstName
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Sets the first name of the User.
     *
     * @param firstName the FirstName to set.
     */
    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    /**
     * Returns the last name of the User.
     *
     * @return String that is the LastName.
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Sets the last name of the User.
     *
     * @param lastName the LastName to set.
     */
    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    /**
     * Returns the Email of the User.
     *
     * @return String that is the Email.
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Sets the Email of the User.
     *
     * @param email the Email to set.
     */
    public void setEmail(String email) {
        this.mEmail = email;
    }

    /**
     * Returns the Password of the User.
     *
     * @return String that is the Password.
     */
    public String getPassword() {
        return mPassword;
    }


    /**
     * Sets the Password of the User.
     *
     * @param password the Password to set.
     */
    public void setPassword(String password) {
        this.mPassword = password;
    }
}
