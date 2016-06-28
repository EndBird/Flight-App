package entities;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import group730.bookingclient.core.utils.DateTimeUtils;


public class Client implements User {
	
	String firstName;
    private String lastName;
    private String email;
    private String address;
    private String creditCardNumber;
    private Date expiryDate;
    private List<Itinerary> bookedItineraries;

	
	public Client(String firstName, String lastName, String email,
            String address, String creditCardNumber, Date expiryDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.creditCardNumber = creditCardNumber;
    this.expiryDate = expiryDate;
    this.bookedItineraries = new ArrayList<>();
}

	@Override
	/**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @param itinerary the itinerary to add to the list of User's booked itineraries.
     */
    public void bookItinerary(Itinerary itinerary) {
        this.bookedItineraries.add(itinerary);
    }

    /**
     * @param itinerary the itinerary to remove from the list of User's booked itineraries.
     */
    public void unBookItinerary(Itinerary itinerary) {
        this.bookedItineraries.remove(itinerary);
    }

    /**
     * @return the bookedItineraries
     */
    public List<Itinerary> getBookedItineraries() {
        return bookedItineraries;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                lastName,
                firstName,
                email,
                address,
                creditCardNumber,
                DateTimeUtils.convertDateToString(expiryDate));
    }
}
