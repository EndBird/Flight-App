package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group730.bookingclient.core.utils.DateTimeUtils;

public interface User extends Serializable {


    /**
     * Creates a User interface.
     *
     * @param firstName        String represents the first name of User.
     * @param lastName         String represents the last name of the User.
     * @param email            String represents the email of the User.
     * @param address          String represents the address of the User.
     * @param creditCardNumber int represents the credit card number of the User.
     * @param expiryDate       int represents the User's credit card number's expiry date.
     */


    /**
     * @return the firstName
     */
    public String getFirstName();
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName); 

    /**
     * @return the lastName
     */
    public String getLastName();

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName);
    /**
     * @return the email
     */
    public String getEmail();

    /**
     * @param email the email to set
     */
    public void setEmail(String email);

    /**
     * @return the address
     */
    public String getAddress();

    /**
     * @param address the address to set
     */
    public void setAddress(String address);

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber();
    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber);

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate();

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate);

    /**
     * @param itinerary the itinerary to add to the list of User's booked itineraries.
     */
    public void bookItinerary(Itinerary itinerary);

    /**
     * @param itinerary the itinerary to remove from the list of User's booked itineraries.
     */
    public void unBookItinerary(Itinerary itinerary);
    /**
     * @return the bookedItineraries
     */
    public List<Itinerary> getBookedItineraries();

    @Override
    public String toString();
}

