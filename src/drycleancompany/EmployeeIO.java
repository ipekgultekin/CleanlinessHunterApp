package drycleancompany;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class provide the read from and write to a binary file employee data.
 */
public class EmployeeIO {
    /**
     * This method writes a list of employees to the binary file "emp.dat"
     *
     * @param employees  list of Employee objects to be written to the file
     */
    public static void writeToFile(ArrayList<Employee> employees) {
        String filePath = "emp.dat";
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filePath))) {
            dataOutputStream.writeInt(employees.size());
            for (Employee employee : employees) {
                dataOutputStream.writeInt(employee.getID());
                dataOutputStream.writeUTF(employee.getName());
                dataOutputStream.writeUTF(employee.getSurname());
                dataOutputStream.writeLong(employee.getDateOfBirth().getTime());
                dataOutputStream.writeLong(employee.getStartDate().getTime());
                dataOutputStream.writeUTF(employee.getNationality());
                dataOutputStream.writeLong(employee.getWorkPermitEndDate().getTime());
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }


    /**
     * This method reads a list of employees from the binary file "emp.dat".
     * @return  ArrayList of Employee objects read from the file
     */
    public static ArrayList<Employee> readFromFile() {
        String filePath = "emp.dat";
        ArrayList<Employee> employees = new ArrayList<>();
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))) {
            int numEmployees = dataInputStream.readInt();
            for (int i = 0; i < numEmployees; i++) {
                int id = dataInputStream.readInt();
                String firstName = dataInputStream.readUTF();
                String lastName = dataInputStream.readUTF();
                long dob = dataInputStream.readLong();
                Date dateOfBirth = new Date(dob);
                long startDateLong = dataInputStream.readLong();
                Date startDate = new Date(startDateLong);
                String nationality = dataInputStream.readUTF();
                long permitEndDateLong = dataInputStream.readLong();
                Date permitEndDate = new Date(permitEndDateLong);
                employees.add(new Employee(dateOfBirth, id, firstName, lastName, nationality, startDate, permitEndDate));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath + ". Creating a new empty file.");
            writeToFile(employees);
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }
        return employees;
    }

}
