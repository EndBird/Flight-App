package group730.bookingclient.core.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import group730.bookingclient.core.utils.DateTimeUtils;

/**
 * A flight object details the information of a flight.
 * Parameters include the flight number, departure date and time, arrival date
 * and time, name of mAirline, mOrigin of departure, mDestination and the mCost
 * of this flight.
 */
public class Flight implements Serializable {
    
    private static final long serialVersionUID = 1999L;

    private int mFlightNumber;
    private Date mDepartureDateTime;
    private Date mArrivalDateTime;
    private String mAirline;
    private String mOrigin;
    private String mDestination;
    private double mCost;
    private int mNumSeats;

    /**
     * Assigns the default values of a new instance of the Flight object.
     *
     * @param flightNumber      int Represents the flight number of the Flight.
     * @param departureDateTime Date Represents the departure date and time of
     *                          the Flight.
     * @param arrivalDateTime   Date Represents the arrival date and time of
     *                          the Flight.
     * @param airline           String Represents the mAirline of the Flight.
     * @param origin            String Represents the mOrigin of the Flight.
     * @param destination       String Represents the mDestination of the
     *                          Flight.
     * @param cost              double Represents the mCost of the Flight.
     * @param numSeats          int Represents the number of seats available
     *                          for the Flight
     */
    public Flight(int flightNumber, Date departureDateTime,
                  Date arrivalDateTime, String airline, String origin,
                  String destination, double cost, int numSeats) {

        this.mFlightNumber = flightNumber;
        this.mDepartureDateTime = departureDateTime;
        this.mArrivalDateTime = arrivalDateTime;
        this.mAirline = airline;
        this.mOrigin = origin;
        this.mDestination = destination;
        this.mCost = cost;
        this.mNumSeats = numSeats;
    }

    /**
     * Returns the flight number of this flight.
     *
     * @return Integer representing the flight number.
     */
    public int getFlightNumber() {
        return mFlightNumber;
    }

    /**
     * Re/assigns the flight number of this flight.
     *
     * @param flightNumber Integer representing the flight number.
     */
    public void setFlightNumber(int flightNumber) {
        this.mFlightNumber = flightNumber;
    }

    /**
     * Returns the time at which this flight departs.
     *
     * @return Date object representing the date and/or time.
     */
    public Date getDepartureDateTime() {
        return mDepartureDateTime;
    }

    /**
     * Assigns a departure Date to a flight.
     *
     * @param departureDateTime Date object representing the departure date and/
     *                          or time.
     */
    public void setDepartureDateTime(Date departureDateTime) {
        this.mDepartureDateTime = departureDateTime;
    }

    /**
     * Returns the time at which this flight arrives at its Destination.
     *
     * @return Date object representing the date and/or time.
     */
    public Date getArrivalDateTime() {
        return mArrivalDateTime;
    }

    /**
     * Assigns the arrival time of this flight at its Destination.
     *
     * @param arrivalDateTime Date object representing the arrival date and/or
     *                        time.
     */
    public void setArrivalDateTime(Date arrivalDateTime) {
        this.mArrivalDateTime = arrivalDateTime;
    }

    /**
     * Returns the name of the Airline that is operating this flight.
     *
     * @return A string representing the name.
     */
    public String getAirline() {
        return mAirline;
    }

    /**
     * Assigns the name of the Airline that is operating this flight.
     *
     * @param airline The name of the Airline as a string.
     */
    public void setAirline(String airline) {
        this.mAirline = airline;
    }

    /**
     * Returns the Origin of departure.
     *
     * @return A string representing the origin.
     */
    public String getOrigin() {
        return mOrigin;
    }

    /**
     * Assigns the origin of departure.
     *
     * @param origin The origin of departure as a string.
     */
    public void setOrigin(String origin) {
        this.mOrigin = origin;
    }

    /**
     * Returns the Destination of this flight.
     *
     * @return An string representing the Destination.
     */
    public String getDestination() {
        return mDestination;
    }


    /**
     * Assigns the Destination of this flight.
     *
     * @param destination The Destination as a string.
     */
    public void setDestination(String destination) {
        this.mDestination = destination;
    }

    /**
     * Returns the Cost of the flight.
     *
     * @return Double representing the Cost of the flight.
     */
    public double getCost() {
        return mCost;
    }

    /**
     * Assigns the Cost of the flight.
     *
     * @param cost Cost of the flight as a Double.
     */
    public void setCost(double cost) {
        this.mCost = cost;
    }

    /**
     * Returns the number of available seats of this flight.
     *
     * @return Integer representing the number of seats
     * available.
     */
    public int getNumSeats() {
        return mNumSeats;
    }

    /**
     * Re/assigns the number of available seats of this flight.
     *
     * @param numSeats Integer representing the number of available
     *                 seats.
     */
    public void setNumSeats(int numSeats) {
        this.mNumSeats = numSeats;
    }

    /**
     * Returns the duration of the flight in milliseconds.
     *
     * @return A long representation of the duration in milliseconds.
     */

    public long getTimeInMillis() {
        return (this.mArrivalDateTime.getTime()
                - this.mDepartureDateTime.getTime());
    }

    /**
     * Leverages the overloaded toString function and converts the flight in the
     * following format:
     * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,Cost
     *
     * @return A string representation of the flight.
     */
    public String toString() {
        return this.toString(true);
    }

    /**
     * Returns String representation of this flight in the following format:
     * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,Cost
     *
     * @param showCost Indicates whether the string representation should
     *                 include the mCost of the flight.
     * @return A string representation of the flight.
     */
    public String toString(boolean showCost) {
        return String.format("%s,%s,%s,%s,%s,%s%s",
                mFlightNumber,
                DateTimeUtils.convertDateTimeToString(mDepartureDateTime),
                DateTimeUtils.convertDateTimeToString(mArrivalDateTime),
                mAirline,
                mOrigin,
                mDestination,
                showCost ? "," + new DecimalFormat("0.00").format(mCost) : "");
    }
}

