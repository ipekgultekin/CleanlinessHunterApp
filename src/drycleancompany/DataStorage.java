package drycleancompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private Connection connection;

    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/CyprusDryCleanDB","cng443user","1234");
            System.out.println("Database connection is successful.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            if(connection != null){
                connection.close();
                System.out.println("Database disconnected.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Customer customer;
                char type = rs.getString("type").charAt(0);

                if (type == 'S') {
                    customer = new Subscribed(
                            rs.getInt("customerID"),
                            rs.getString("name").trim(),
                            rs.getString("surname").trim(),
                            rs.getDate("dateOfBirth"),
                            rs.getDate("registrationDate"),
                            rs.getDate("subscriptionDate"),
                            rs.getDouble("depositPaid")
                    );
                } else if (type == 'U') {
                    customer = new UnSubscribed(
                            rs.getInt("customerID"),
                            rs.getString("name").trim(),
                            rs.getString("surname").trim(),
                            rs.getDate("dateOfBirth"),
                            rs.getDate("registrationDate")
                    );
                } else {
                    throw new IllegalArgumentException("Unknown customer type: " + type);
                }

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }


    public void addCustomer(Customer customer) {
        String sql;
        if (customer instanceof Subscribed) {
            sql = "INSERT INTO customer (customerID, name, surname, dateOfBirth, registrationDate, type, depositPaid, subscriptionDate) VALUES (?, ?, ?, ?, ?, 'S', ?, DEFAULT)";
        } else {
            sql = "INSERT INTO customer (customerID, name, surname, dateOfBirth, registrationDate, type) VALUES (?, ?, ?, ?, ?, 'U')";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer.getID());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setDate(4, customer.getDateOfBirth());
            preparedStatement.setDate(5, customer.getRegistrationDate());

            if (customer instanceof Subscribed) {
                preparedStatement.setDouble(6, ((Subscribed) customer).getDepositPaid());
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }


    public void updateCustomer(Customer customer) {
        String query = "UPDATE Customer SET name = ?, surname = ?, dateOfBirth = ?, type = ?, registrationDate = ?, subscriptionDate = ?, depositPaid = ? WHERE customerID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getSurname());
            pstmt.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
            pstmt.setString(4, String.valueOf(customer.getType()));
            pstmt.setDate(5, new java.sql.Date(customer.getRegistrationDate().getTime()));


            if (customer instanceof Subscribed) {
                Subscribed subscribed = (Subscribed) customer;
                pstmt.setDate(6, new java.sql.Date(subscribed.getSubscriptionDate().getTime()));
                pstmt.setDouble(7, subscribed.getDepositPaid());
            } else {
                pstmt.setDate(6, null);
                pstmt.setDouble(7, 0);
            }

            pstmt.setInt(8, customer.getCustomerID());
            pstmt.executeUpdate();
            System.out.println("Customer updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM Customer WHERE customerID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee(
                        new java.util.Date(rs.getDate("dateOfBirth").getTime()),
                        rs.getInt("employeeID"),
                        rs.getString("name").trim(),
                        rs.getString("surname").trim(),
                        rs.getString("nationality").trim(),
                        new java.util.Date(rs.getDate("startDate").getTime()),
                        new java.util.Date(rs.getDate("workPermitEndDate").getTime())
                                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }


    public void addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (employeeID, name, surname, dateOfBirth, nationality, startDate, workPermitEndDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employee.getEmployeeID());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getSurname());
            pstmt.setDate(4, new java.sql.Date(employee.getDateOfBirth().getTime()));
            pstmt.setString(5, employee.getNationality());
            pstmt.setDate(6, new java.sql.Date(employee.getStartDate().getTime()));
            pstmt.setDate(7, new java.sql.Date(employee.getWorkPermitEndDate().getTime()));
            pstmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET name = ?, surname = ?, dateOfBirth = ?, nationality = ?, startDate = ?, workPermitEndDate = ? WHERE employeeID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getSurname());
            pstmt.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
            pstmt.setString(4, employee.getNationality());
            pstmt.setDate(5, employee.getStartDate());
            pstmt.setDate(6, employee.getWorkPermitEndDate());
            pstmt.setInt(7, employee.getEmployeeID());
            pstmt.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE employeeID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
