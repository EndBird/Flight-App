package group730.bookingclient.core.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group730.bookingclient.core.services.UserManager;
import group730.bookingclient.core.utils.DateTimeUtils;

/**
 * Client class for a client, which is a subclass of User. Clients can edit
 * personal information as well as book itineraries.
 */
public class Client extends User {

    private String mAddress;
    private String mCreditCardNumber;
    private Date mExpiryDate;
    private List<Itinerary> mBookedItineraries;

    /**
     * Creates a Client object.
     *
     * @param firstName         String represents the first name of client.
     * @param lastName          String represents the last name of the client.
     * @param email             String represents the mEmail of the client.
     * @param mAddress          String represents the mAddress of the client.
     * @param mCreditCardNumber String represents the credit card number of the
     *                          client.
     * @param mExpiryDate       Date represents the credit card's expiry date.
     */
    public Client(String firstName, String lastName, String email,
                  String password, String mAddress, String mCreditCardNumber,
                  Date mExpiryDate) {
        super(UserManager.USER_TYPE.CLIENT, firstName, lastName, email,
                password);

        this.mAddress = mAddress;
        this.mCreditCardNumber = mCreditCardNumber;
        this.mExpiryDate = mExpiryDate;
        this.mBookedItineraries = new ArrayList<>();
    }

    /**
     * Returns the Address of this client.
     *
     * @return the Address.
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * Assigns the Address to this client.
     *
     * @param address the Address to set.
     */
    public void setAddress(String address) {
        this.mAddress = address;
    }

    /**
     * Returns the credit card number of this client.
     *
     * @return the CreditCardNumber.
     */
    public String getCreditCardNumber() {
        return mCreditCardNumber;
    }

    /**
     * Assigns the credit card number to this client.
     *
     * @param creditCardNumber the CreditCardNumber to set.
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.mCreditCardNumber = creditCardNumber;
    }

    /**
     * Returns this client's credit card expiry date.
     *
     * @return the ExpiryDate.
     */
    public Date getExpiryDate() {
        return mExpiryDate;
    }

    /**
     * Assigns this client's credit card number expiry date.
     *
     * @param expiryDate the ExpiryDate to set.
     */
    public void setExpiryDate(Date expiryDate) {
        this.mExpiryDate = expiryDate;
    }

    /**
     * Adds itinerary to the BookedItineraries attribute of this client.
     *
     * @param itinerary the itinerary to add to the list of User's booked
     *                  itineraries.
     */
    public void bookItinerary(Itinerary itinerary) {
        this.mBookedItineraries.add(itinerary);
    }


    /**
     * Removes itinerary from the BookedItineraries attribute of this client.
     *
     * @param itinerary the itinerary to remove from the list of User's booked
     *                  itineraries.
     */
    public void unBookItinerary(Itinerary itinerary) {
        this.mBookedItineraries.remove(itinerary);
    }

    /**
     * Returns all of the itineraries which this client has booked.
     *
     * @return the BookedItineraries.
     */
    public List<Itinerary> getBookedItineraries() {
        return mBookedItineraries;
    }

    /**
     * Return String representation of this client in the following format:
     * mLastName,mFirstName,mEmail,mAddress,mCreditCardNumber,mExpiryDate
     *
     * @return A string representation of client.
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                mLastName,
                mFirstName,
                mEmail,
                mAddress,
                mCreditCardNumber,
                DateTimeUtils.convertDateToString(mExpiryDate));
    }
}
