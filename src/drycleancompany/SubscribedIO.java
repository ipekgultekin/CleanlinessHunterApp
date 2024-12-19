package drycleancompany;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
/**
 * This class provide the read from and write to a binary file subscribed customer data.
 */
public class SubscribedIO {
    /**
     * This method writes a list of subscribed customers to the binary file "Subscribed.dat"
     *
     * @param subscribedCustomers   list of Subscribed customers to be written to the file
     */
    public static void writeToFile(ArrayList<Subscribed> subscribedCustomers) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("Subscribed.dat"))) {
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
     * This method read a list of subscribed customers from the binary file "Subscribed.dat"
     *
     * @return An ArrayList of Subscribed customers read from the file.
     */
    public static ArrayList<Subscribed> readFromFile() {
        ArrayList<Subscribed> subscribedCustomers = new ArrayList<>();
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("Subscribed.dat"))) {
            int numCustomers = dataInputStream.readInt();

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

                Subscribed customer = new Subscribed(depositPaid, registrationDate);
                customer.setID(id);
                customer.setName(name);
                customer.setSurname(surname);
                customer.setDateOfBirth(dateOfBirth);
                customer.setSubscriptionDate(subscriptionDate);
                subscribedCustomers.add(customer);
            }
            System.out.println("Subscribed customers read from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Subscribed.dat file not found, returning an empty list.");
        } catch (IOException e) {
            System.err.println("Error while reading subscribed customers from file: " + e.getMessage());
        }
        return subscribedCustomers;
    }
}
