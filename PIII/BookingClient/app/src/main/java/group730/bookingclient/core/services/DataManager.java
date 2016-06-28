package group730.bookingclient.core.services;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group730.bookingclient.BookingClient;
import group730.bookingclient.core.entities.Client;
import group730.bookingclient.core.entities.Flight;
import group730.bookingclient.core.entities.User;
import group730.bookingclient.core.utils.DateTimeUtils;

/**
 * Manages all external data importing and exporting a collection of flights
 * and/or a dictionary of users.
 * <p/>
 * Additionally, this class manages all parsing from CSV files into a collection
 * of flights and/or a dictionary of users.
 */
public class DataManager {

    private final String USERS_FILE_PATH = "users.ser";
    private final String FLIGHTS_FILE_PATH = "flights.ser";
    private final String PASSWORDS_FILE_PATH = "passwords.txt";

    private static DataManager sDataManager = new DataManager();

    /**
     * Returns an static instance of this class.
     *
     * @return A DataManager object.
     */
    public static DataManager getInstance() {
        if (sDataManager == null)
            sDataManager = new DataManager();
        return sDataManager;
    }

    // Primary constructor ensures file paths exists before access. Creates if
    // not found.
    private DataManager() {
        validateFilePaths();
    }

    private void validateFilePaths() {
        try {
            File usersFile = new File(USERS_FILE_PATH);
            if (!usersFile.exists()) {
                usersFile.createNewFile();
            }

            File flightsFile = new File(FLIGHTS_FILE_PATH);
            if (!flightsFile.exists()) {
                flightsFile.createNewFile();
            }

            File passwordsFile = new File(PASSWORDS_FILE_PATH);
            if (!passwordsFile.exists()) {
                passwordsFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes collection of saved flights from file.
     *
     * @return An collection of loaded flights.
     */
    public List<Flight> loadFlights() {
        List<Flight> loadedFlights = new ArrayList<>();

        try {
            loadedFlights = (List<Flight>) deserialize(FLIGHTS_FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            // Failed to find the path to file, or the class defining the object
            // that the data is being de-serialized to is non-existent.
            e.printStackTrace();
        }

        return loadedFlights;
    }

    /**
     * Serializes collection of flights to file.
     *
     * @param flights The collection of flights that are to be saved.
     */
    public void saveFlights(List<Flight> flights) {
        try {
            serialize(flights, FLIGHTS_FILE_PATH);
        } catch (IOException e) {
            // Failed to find the path to file, or the class defining the object
            // that the data is being de-serialized to is non-existent.
            e.printStackTrace();
        }
    }

    /**
     * Deserializes collection of saved users from a file.
     *
     * @return An dictionary of loaded flights.
     */
    public Map<String, User> loadUsers() {
        Map<String, User> loadedUsers = new HashMap<>();

        try {
            loadedUsers = (Map<String, User>) deserialize(USERS_FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            // Failed to find the path to file, or the class defining the object
            // that the data is being de-serialized to is non-existent.
            e.printStackTrace();
        }

        return loadedUsers;
    }

    /**
     * Serializes dictionary of users to file.
     *
     * @param users The dictionary of users that are to be saved.
     */
    public void saveUsers(Map<String, User> users) {
        try {
            serialize(users, USERS_FILE_PATH);
        } catch (IOException e) {
            // Failed to find the path to file, or the class defining the object
            // that the data is being de-serialized to is non-existent.
            e.printStackTrace();
        }
    }

    /**
     * Parses a collection of flights from a given file with comma separated
     * values. Format is as follows:
     * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination,Price
     *
     * @param pathToFile The path to the file that is being parsed.
     * @return A collection of flights.
     */
    public List<Flight> parseFlights(String pathToFile) throws IOException {
        List<Flight> parsedFlights = new ArrayList<>();

        FileInputStream fileStream;
        fileStream = BookingClient.GetInstance()
                .getContext().openFileInput(pathToFile);
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(fileStream));

        String line;

        do {
            line = reader.readLine();
            if (line != null && !line.trim().equals("")) {
                String[] lineValues = line.split(",");

                int flightNumber = Integer.parseInt(lineValues[0]);
                Date departureDateTime = DateTimeUtils.convertToDateTime(
                        lineValues[1]);
                Date arrivalDateTime = DateTimeUtils.convertToDateTime(
                        lineValues[2]);
                String airline = lineValues[3];
                String origin = lineValues[4];
                String destination = lineValues[5];
                double cost = Double.parseDouble(lineValues[6]);
                int numSeats = Integer.parseInt(lineValues[7]);

                Flight newFlight = new Flight(flightNumber,
                        departureDateTime,
                        arrivalDateTime,
                        airline,
                        origin,
                        destination,
                        cost,
                        numSeats);

                parsedFlights.add(newFlight);
            }
        } while (line != null);

        return parsedFlights;
    }

    /**
     * Parses a collection of users from a given file with comma separated
     * values. Format is as follows:
     * LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
     *
     * @param pathToFile The path to the file that is being parsed.
     * @return A collection of users.
     */
    public List<User> parseUsers(String pathToFile) throws IOException {
        List<User> parsedUsers = new ArrayList<>();

        FileInputStream fileStream;
        fileStream = BookingClient.GetInstance()
                .getContext().openFileInput(pathToFile);
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(fileStream));

        String line;

        do {
            line = reader.readLine();
            if (line != null && !line.trim().equals("")) {
                String[] lineValues = line.split(",");

                String lastName = lineValues[0];
                String firstName = lineValues[1];
                String email = lineValues[2];
                String address = lineValues[3];
                String creditCardNumber = lineValues[4];
                Date expiry = DateTimeUtils.convertToDate(lineValues[5]);

                Client newUser = new Client(firstName,
                        lastName,
                        email,
                        "",
                        address,
                        creditCardNumber, expiry);

                parsedUsers.add(newUser);
            }
        } while (line != null);

        return parsedUsers;
    }

    /**
     * TO DO
     *
     * @return
     */
    public String findLoggedInUser() {
        File dir = BookingClient.GetInstance().getContext().getFilesDir();
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (f.getName().contains("email-")) {
                    String email = f.getName().replace("email-", "").replace(".at.", "@");
                    return email;
                }
            }
        }
        return "";
    }

    /**
     * Saves a file as a token to represent a user who is currently logged in.
     * File name is a variant of email.
     *
     * @param email A string representing email.
     */
    public void saveLoggedInUser(String email) {
        try {
            String fileName = email.replace("@", ".at.");
            fileName = "email-" + fileName;
            FileOutputStream stream;
            stream = BookingClient.GetInstance().getContext().openFileOutput(
                    fileName, Context.MODE_PRIVATE);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the saved token which represents a user who is currently logged
     * in.
     * File name is a variant of email.
     *
     * @param email A string representing email.
     */
    public void deleteLoggedInUser(String email) {
        try {
            String fileName = email.replace("@", ".at.");
            fileName = "email-" + fileName;
            BookingClient.GetInstance().getContext().deleteFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handles generic serialization of a given object to a file.
    private void serialize(Object data, String pathToFile) throws IOException {

        FileOutputStream fileStream;
        fileStream = BookingClient.GetInstance().getContext().openFileOutput(
                pathToFile, Context.MODE_PRIVATE);
        ObjectOutputStream outStream = new ObjectOutputStream(fileStream);
        outStream.writeObject(data);

        fileStream.close();
        outStream.close();
    }

    // Handles generic deserialization of a given object from a file.
    private Object deserialize(String pathToFile) throws IOException,
            ClassNotFoundException {

        FileInputStream fileStream;
        fileStream = BookingClient.GetInstance().getContext()
                .openFileInput(pathToFile);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        Object data = objectStream.readObject();

        objectStream.close();
        fileStream.close();

        return data;
    }

    /**
     * Parses a collection of passwords from a given file with colon separated.
     */
    public void loadPasswords() {
        try {
            FileReader fr = new FileReader(new File(PASSWORDS_FILE_PATH));
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(":");
                String email = array[0];
                String password = array[1];
            }

            FileInputStream fileStream;
            fileStream = BookingClient.GetInstance()
                    .getContext().openFileInput(PASSWORDS_FILE_PATH);
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(fileStream));

            String line2;

            do {
                line2 = reader.readLine();
                if (line2 != null && !line2.trim().equals("")) {
                    String[] lineValues = line2.split(":");

                    String email = lineValues[0];
                    String password = lineValues[1];

                    User user = UserManager.getInstance().getUsers().get(email);
                    if (user != null) {
                        user.setPassword(password);
                    }
                }
            } while (line != null);
            UserManager.getInstance().saveUsers();

        } catch (Exception e) {
        }
    }
}
