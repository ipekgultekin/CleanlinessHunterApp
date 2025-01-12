package drycleancompany;

import java.io.*;
import java.util.ArrayList;
import java.sql.Date;

/**
 * This class provides methods to read from and write to a binary file for subscribed customer data.
 */
public class SubscribedIO {

    private static final String FILE_NAME = "Subscribed.dat";

    /**
     * This method writes a list of subscribed customers to the binary file "Subscribed.dat".
     *
     * @param subscribedCustomers List of Subscribed customers to be written to the file.
     */
    public static void writeToFile(ArrayList<Subscribed> subscribedCustomers) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            dataOutputStream.writeInt(subscribedCustomers.size());

            for (Subscribed customer : subscribedCustomers) {
                dataOutputStream.writeInt(customer.getID());
                dataOutputStream.writeUTF(customer.getName());
                dataOutputStream.writeUTF(customer.getSurname());
                dataOutputStream.writeLong(customer.getDateOfBirth().getTime());
                dataOutputStream.writeLong(customer.getRegistrationDate().getTime());
                dataOutputStream.writeLong(customer.getSubscriptionDate().getTime());
                dataOutputStream.writeDouble(customer.getDepositPaid());
            }
            System.out.println("Subscribed customers written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error while writing subscribed customers to file: " + e.getMessage());
        }
    }

    /**
     * This method reads a list of subscribed customers from the binary file "Subscribed.dat".
     *
     * @return An ArrayList of Subscribed customers read from the file.
     */
    public static ArrayList<Subscribed> readFromFile() {
        ArrayList<Subscribed> subscribedCustomers = new ArrayList<>();

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(FILE_NAME))) {
            int numCustomers = dataInputStream.readInt(); // Read number of customers

            for (int i = 0; i < numCustomers; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                String surname = dataInputStream.readUTF();
                long dob = dataInputStream.readLong();
                Date dateOfBirth = new Date(dob);
                long registrationDateLong = dataInputStream.readLong();
                Date registrationDate = new Date(registrationDateLong);
                long subscriptionDateLong = dataInputStream.readLong();
                Date subscriptionDate = new Date(subscriptionDateLong);
                double depositPaid = dataInputStream.readDouble();


                Subscribed customer = new Subscribed(id, name, surname, dateOfBirth, registrationDate, depositPaid);
                customer.setSubscriptionDate(subscriptionDate);

                subscribedCustomers.add(customer);
            }
            System.out.println("Subscribed customers read from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NAME + " file not found, returning an empty list.");
        } catch (IOException e) {
            System.err.println("Error while reading subscribed customers from file: " + e.getMessage());
        }
        return subscribedCustomers;
    }
}
