package drycleancompany;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class provide the read from and write to a binary file unsubscribed customer data.
 */
public class UnSubscribedIO {
    /**
     * This method writes a list of unsubscribed customers to the binary file "UnSubscribed.dat"
     *
     * @param unsubscribedCustomers   list of UnSubscribed customers to be written to the file
     */
    public static void writeToFile(ArrayList<UnSubscribed> unsubscribedCustomers) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("UnSubscribed.dat"))) {
            dataOutputStream.writeInt(unsubscribedCustomers.size());

            for (UnSubscribed customer : unsubscribedCustomers) {
                dataOutputStream.writeInt(customer.getID());
                dataOutputStream.writeUTF(customer.getName());
                dataOutputStream.writeUTF(customer.getSurname());
                dataOutputStream.writeLong(customer.getDateOfBirth().getTime());
                dataOutputStream.writeLong(customer.getRegistrationDate().getTime());
            }
            System.out.println("Unsubscribed customers written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error while writing unsubscribed customers to file: " + e.getMessage());
        }
    }

    /**
     * This method read a list of unsubscribed customers from the binary file "UnSubscribed.dat"
     *
     * @return An ArrayList of UnSubscribed customers read from the file.
     */
    public static ArrayList<UnSubscribed> readFromFile() {
        ArrayList<UnSubscribed> unsubscribedCustomers = new ArrayList<>();
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("UnSubscribed.dat"))) {
            int numCustomers = dataInputStream.readInt();

            for (int i = 0; i < numCustomers; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                String surname = dataInputStream.readUTF();
                long dob = dataInputStream.readLong();
                Date dateOfBirth = new Date(dob);
                long registrationDateLong = dataInputStream.readLong();
                Date registrationDate = new Date(registrationDateLong);

                UnSubscribed customer = new UnSubscribed();
                customer.setID(id);
                customer.setName(name);
                customer.setSurname(surname);
                customer.setDateOfBirth(dateOfBirth);
                customer.setRegistrationDate(registrationDate);
                unsubscribedCustomers.add(customer);
            }
            System.out.println("Unsubscribed customers read from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("UnSubscribed.dat file not found, returning an empty list.");
        } catch (IOException e) {
            System.err.println("Error while reading unsubscribed customers from file: " + e.getMessage());
        }
        return unsubscribedCustomers;
    }
}
