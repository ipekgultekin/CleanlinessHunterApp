package drycleancompany;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;

public class SerializationHandler {

    // Serialize customer list to a file
    public static void serializeCustomers(ArrayList<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.ser"))) {
            oos.writeObject(customers);

            // Generate MD5 in a separate thread
            new Thread(() -> {
                try {
                    String hash = generateMD5Hash("customers.ser");
                    saveHashToFile(hash, "customers.md5");
                    System.out.println("Customers serialized and hash saved successfully.");
                } catch (IOException e) {
                    System.err.println("Error during MD5 generation for customers: " + e.getMessage());
                }
            }).start();

        } catch (IOException e) {
            System.err.println("Error during customer serialization: " + e.getMessage());
        }
    }

    // Deserialize customer list from a file and verify integrity
    public static ArrayList<Customer> deserializeCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        // Thread for verification
        Thread verificationThread = new Thread(() -> {
            try {
                if (!verifyMD5Hash("customers.ser", "customers.md5")) {
                    System.err.println("Customer data integrity check failed!");
                } else {
                    System.out.println("Customer data integrity verified.");
                }
            } catch (Exception e) {
                System.err.println("Error verifying MD5 for customers: " + e.getMessage());
            }
        });

        verificationThread.start();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.ser"))) {
            ArrayList<Customer> deserializedCustomers = (ArrayList<Customer>) ois.readObject();
            customers.addAll(deserializedCustomers);
            verificationThread.join(); // Wait for the MD5 verification thread to finish
            System.out.println("Customers deserialized successfully.");
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.err.println("Error during customer deserialization: " + e.getMessage());
        }

        return customers;
    }

    // Serialize employee list to a file
    public static void serializeEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees.ser"))) {
            oos.writeObject(employees);

            // Generate MD5 in a separate thread
            new Thread(() -> {
                try {
                    String hash = generateMD5Hash("employees.ser");
                    saveHashToFile(hash, "employees.md5");
                    System.out.println("Employees serialized and hash saved successfully.");
                } catch (IOException e) {
                    System.err.println("Error during MD5 generation for employees: " + e.getMessage());
                }
            }).start();

        } catch (IOException e) {
            System.err.println("Error during employee serialization: " + e.getMessage());
        }
    }

    // Deserialize employee list from a file and verify integrity
    public static ArrayList<Employee> deserializeEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        // Thread for verification
        Thread verificationThread = new Thread(() -> {
            try {
                if (!verifyMD5Hash("employees.ser", "employees.md5")) {
                    System.err.println("Employee data integrity check failed!");
                } else {
                    System.out.println("Employee data integrity verified.");
                }
            } catch (Exception e) {
                System.err.println("Error verifying MD5 for employees: " + e.getMessage());
            }
        });

        verificationThread.start();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employees.ser"))) {

            ArrayList<Employee> deserializedEmployees = (ArrayList<Employee>) ois.readObject();
            employees.addAll(deserializedEmployees);
            verificationThread.join(); // Wait for the MD5 verification thread to finish
            System.out.println("Employees deserialized successfully.");
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.err.println("Error during employee deserialization: " + e.getMessage());
        }

        return employees;
    }

    // Generate MD5 hash for a file
    private static String generateMD5Hash(String filePath) throws IOException {
        try (InputStream is = new FileInputStream(filePath)) {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            byte[] hashBytes = digest.digest();
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }
            return hashString.toString();
        } catch (Exception e) {
            throw new IOException("Error generating MD5 hash: " + e.getMessage(), e);
        }
    }

    // Save MD5 hash to a file
    private static void saveHashToFile(String hash, String hashFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(hashFilePath))) {
            writer.write(hash);
        }
    }

    // Verify MD5 hash of a file
    private static boolean verifyMD5Hash(String filePath, String hashFilePath) {
        try {
            String currentHash = generateMD5Hash(filePath);
            String savedHash = new BufferedReader(new FileReader(hashFilePath)).readLine();
            return currentHash.equals(savedHash);
        } catch (Exception e) {
            System.err.println("Error verifying MD5 hash: " + e.getMessage());
            return false;
        }
    }

}
