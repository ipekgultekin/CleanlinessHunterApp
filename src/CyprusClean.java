import drycleancompany.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static drycleancompany.CyprusDryClean.dateConversion;

public class CyprusClean {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> comboBox;
    private JPanel dynamicPanel;

    public CyprusClean() {
        frame = new JFrame("Cyprus Dry Clean");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        titleLabel = new JLabel("Welcome to the Cyprus Dry Clean!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // create JComboBox
        comboBox = new JComboBox<>();
        comboBox.addItem("Please Select Option");
        comboBox.addItem("Add Employee");
        comboBox.addItem("Delete Employee");
        comboBox.addItem("List Employee Details");
        comboBox.addItem("Add Customer");
        comboBox.addItem("Delete Customer");
        comboBox.addItem("Customer Details");
        comboBox.addItem("Put Order");
        comboBox.addItem("Customer Order Details");
        comboBox.addItem("Customer Order Total Cost");
        comboBox.addItem("List Employees");
        comboBox.addItem("List Customers");
        comboBox.addItem("Add Health Inspection");
        comboBox.addItem("Compare Customer Loyalty");
        comboBox.addItem("Exit");

        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(comboBox);

        // dynamic panel firstly it is invisible
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new GridLayout(8, 2, 5, 5));
        dynamicPanel.setVisible(false); // Initially invisible
        mainPanel.add(dynamicPanel);

        // JComboBox action listener that triggers whenever the selected option changes
        // Also when a new item is chosen, retrieve it and pass it to handleSelection() for further processing.
        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            handleSelection(selectedOption);
        });


        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                handleSelection(selectedOption);
            }
        });
    }

    //selection for JComboBox
    private void handleSelection(String selectedOption) {
        dynamicPanel.removeAll(); // clear dynamic panel

        switch (selectedOption) {
            case "Please Select Option" ->{
                dynamicPanel.setVisible(false);
            }
            case "Add Employee" -> {
                dynamicPanel.setVisible(true);
                createAddEmployeeForm();
            }
            case "Delete Employee" -> {
                dynamicPanel.setVisible(true);
                createDeleteEmployeeForm();
            }
            case "List Employee Details" -> {
                dynamicPanel.setVisible(true);
                createListEmployeeDetailsForm();
            }
            case "Add Customer" ->{
                dynamicPanel.setVisible(true);
                createAddCustomerForm();
            }
            case "Delete Customer" -> {
                dynamicPanel.setVisible(true);
                createDeleteCustomerForm();
            }
            case "Customer Details" -> {
                dynamicPanel.setVisible(true);
                createCustomerDetailsForm();
            }
            case "Put Order" -> {
                dynamicPanel.setVisible(true);
                createPutOrderForm();
            }
            case "Customer Order Details" -> {
                dynamicPanel.setVisible(true);
                createCustomerOrderDetailsForm();
            }
            case "Customer Order Total Cost" -> {
                dynamicPanel.setVisible(true);
                createCustomerOrderTotalCostForm();
            }
            case "List Employees" -> {
                dynamicPanel.setVisible(true);
                displayAllEmployees();
            }
            case "List Customers" -> {
                dynamicPanel.setVisible(true);
                displayAllCustomers();
            }
            case "Add Health Inspection" -> {
                dynamicPanel.setVisible(true);
                createAddHealthInspectionForm();
            }
            case "Compare Customer Loyalty" ->{
                dynamicPanel.setVisible(true);
                createCompareCustomerLoyaltyForm();
            }
            case "Exit" -> {
                // before the exit program, write the data to file
                EmployeeIO.writeToFile(CyprusDryClean.empList);

                //I created two new array list for separate subscribed and unsubscribed customers and add them related subscribed type
                ArrayList<Subscribed> subscribed = new ArrayList<>();
                ArrayList<UnSubscribed> unsubscribed = new ArrayList<>();
                for (Customer customer : CyprusDryClean.customerList) {
                    if (customer instanceof Subscribed) {
                        subscribed.add((Subscribed) customer);
                    } else if (customer instanceof UnSubscribed) {
                        unsubscribed.add((UnSubscribed) customer);
                    }
                }
                SubscribedIO.writeToFile(subscribed);
                UnSubscribedIO.writeToFile(unsubscribed);
                System.exit(0); // exit the program
            }
            default -> dynamicPanel.setVisible(false); // for other options, dynamic panel is invisible
        }

        //for update the gui when the new data added or deleted
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void createAddEmployeeForm() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField dobField = new JTextField(); // Date of Birth
        JTextField startDateField = new JTextField(); // Start Date
        JTextField nationalityField = new JTextField();
        JTextField workPermitEndDateField = new JTextField();


        dynamicPanel.add(new JLabel("Employee ID:"));
        dynamicPanel.add(idField);

        dynamicPanel.add(new JLabel("Name:"));
        dynamicPanel.add(nameField);

        dynamicPanel.add(new JLabel("Surname:"));
        dynamicPanel.add(surnameField);

        dynamicPanel.add(new JLabel("Date of Birth (dd-MM-yyyy):"));
        dynamicPanel.add(dobField);

        dynamicPanel.add(new JLabel("Start Date (dd-MM-yyyy):"));
        dynamicPanel.add(startDateField);

        dynamicPanel.add(new JLabel("Nationality:"));
        dynamicPanel.add(nationalityField);

        dynamicPanel.add(new JLabel("Work Permit End Date (dd-MM-yyyy):"));
        dynamicPanel.add(workPermitEndDateField);


        JButton addButton = new JButton("Add Employee");
        dynamicPanel.add(new JLabel());
        dynamicPanel.add(addButton);

        // For add button, I created action listener to handle
        addButton.addActionListener(e -> {
            try {
                //these come from user (input)
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String surname = surnameField.getText();
                Date dob = dateConversion(dobField.getText());
                Date startDate = dateConversion(startDateField.getText());
                String nationality = nationalityField.getText();
                Date workPermitEndDate = dateConversion(workPermitEndDateField.getText());

                for (Employee employee : CyprusDryClean.empList) {
                    if (id == employee.getID()) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID! This ID has already been taken!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Employee newEmployee = new Employee(dob, id, name, surname, nationality, startDate, workPermitEndDate); //add new employee
                CyprusDryClean.empList.add(newEmployee);
                JOptionPane.showMessageDialog(frame, "New employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // I decided the shows the success message with dialog box

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    private void createDeleteEmployeeForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel());
        formPanel.add(deleteButton);
        formPanel.add(new JLabel("Deleted"));
        formPanel.add(new JScrollPane(resultArea));


        dynamicPanel.removeAll();
        dynamicPanel.add(formPanel);

        // for delete button I add action listener to handle
        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Employee employee = CyprusDryClean.findEmployeeID(id);

                if (employee != null) {
                    CyprusDryClean.empList.remove(employee);
                    resultArea.setText("Employee with ID " + id + " deleted successfully!");
                } else {
                    resultArea.setText("Employee not found.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID! Please enter a numeric value.");
            } catch (Exception ex) {
                resultArea.setText("An error occurred. Please try again.");
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void createListEmployeeDetailsForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField idField = new JTextField();
        JButton searchButton = new JButton("Search");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel());
        formPanel.add(searchButton);
        formPanel.add(new JLabel("Employee Details:"));
        formPanel.add(resultArea);


        dynamicPanel.removeAll();
        dynamicPanel.add(formPanel);

        // for search button I create action listener to handle
        searchButton.addActionListener(e -> {
            try {
                int empID = Integer.parseInt(idField.getText());
                Employee employee = CyprusDryClean.findEmployeeID(empID);

                if (employee != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String details = "ID: " + employee.getID() +
                            "\nName: " + employee.getName() +
                            "\nSurname: " + employee.getSurname() +
                            "\nDate of Birth: " + dateFormat.format(employee.getDateOfBirth()) +
                            "\nStart Date: " + dateFormat.format(employee.getStartDate()) +
                            "\nHealth Inspection: "+ employee.getHealthInspection() +
                            "\nWork Permit End Date: "+employee.getWorkPermitEndDate();
                    resultArea.setText(details);
                } else {
                    resultArea.setText("Employee not found.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID. Please enter a numeric value.");
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void createAddCustomerForm() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField depositField = new JTextField();

        // Buttons for customer type (I divide the two button so if the customer wants to subscribe then he/she should write deposit amount and then click the button
        JButton subscribedButton = new JButton("Add Subscribed Customer");
        JButton unsubscribedButton = new JButton("Add Unsubscribed Customer");


        dynamicPanel.add(new JLabel("Customer ID:"));
        dynamicPanel.add(idField);

        dynamicPanel.add(new JLabel("Name:"));
        dynamicPanel.add(nameField);

        dynamicPanel.add(new JLabel("Surname:"));
        dynamicPanel.add(surnameField);

        dynamicPanel.add(new JLabel("Date of Birth (dd-MM-yyyy):"));
        dynamicPanel.add(dobField);

        dynamicPanel.add(new JLabel("Deposit Amount (only for Subscribed):"));
        dynamicPanel.add(depositField);

        dynamicPanel.add(subscribedButton);
        dynamicPanel.add(unsubscribedButton);


        subscribedButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String surname = surnameField.getText();
                Date dob = dateConversion(dobField.getText());
                Date registrationDate = new Date();
                double deposit = Double.parseDouble(depositField.getText());


                for (Customer customer : CyprusDryClean.customerList) {
                    if (id == customer.getID()) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID! This ID has already been taken!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                double subscribedDeposit = customerManagement.minDeposit;
                if (deposit < subscribedDeposit) {
                    JOptionPane.showMessageDialog(frame, "Deposit cannot be less than " + subscribedDeposit, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Subscribed subscribedCustomer = new Subscribed(deposit, registrationDate);
                subscribedCustomer.setID(id);
                subscribedCustomer.setName(name);
                subscribedCustomer.setSurname(surname);
                subscribedCustomer.setDateOfBirth(dob);
                subscribedCustomer.setRegistrationDate(registrationDate);
                subscribedCustomer.setSubscriptionDate(registrationDate);
                subscribedCustomer.setDepositPaid(deposit);

                CyprusDryClean.customerList.add(subscribedCustomer);
                JOptionPane.showMessageDialog(frame, "Subscribed customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        unsubscribedButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String surname = surnameField.getText();
                Date dob = dateConversion(dobField.getText());
                Date registrationDate = new Date();

                // Check for duplicate ID
                for (Customer customer : CyprusDryClean.customerList) {
                    if (id == customer.getID()) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID! This ID has already been taken!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Create Unsubscribed customer
                UnSubscribed unsubscribedCustomer = new UnSubscribed();
                unsubscribedCustomer.setID(id);
                unsubscribedCustomer.setName(name);
                unsubscribedCustomer.setSurname(surname);
                unsubscribedCustomer.setDateOfBirth(dob);
                unsubscribedCustomer.setRegistrationDate(registrationDate);

                CyprusDryClean.customerList.add(unsubscribedCustomer);
                JOptionPane.showMessageDialog(frame, "Unsubscribed customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void createDeleteCustomerForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        formPanel.add(new JLabel("Customer ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel());
        formPanel.add(deleteButton);
        formPanel.add(new JLabel("Deleted"));
        formPanel.add(new JScrollPane(resultArea));


        dynamicPanel.removeAll();
        dynamicPanel.add(formPanel);

        // for delete button I create action listener
        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Customer customer = CyprusDryClean.findCustomerID(id);

                if (customer != null) {
                    CyprusDryClean.customerList.remove(customer);
                    resultArea.setText("Customer with ID " + id + " deleted successfully!");
                } else {
                    resultArea.setText("Customer not found.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID! Please enter a numeric value.");
            } catch (Exception ex) {
                resultArea.setText("An error occurred. Please try again.");
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void createCustomerDetailsForm() {
        JTextField idField = new JTextField();
        JButton searchButton = new JButton("Search");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        dynamicPanel.removeAll();
        dynamicPanel.add(new JLabel("Enter Customer ID:"));
        dynamicPanel.add(idField);
        dynamicPanel.add(new JLabel());
        dynamicPanel.add(searchButton);
        dynamicPanel.add(new JLabel("Customer Details:"));
        dynamicPanel.add(new JScrollPane(resultArea));

        searchButton.addActionListener(e -> {
            try {
                int customerID = Integer.parseInt(idField.getText());
                Customer customer = CyprusDryClean.findCustomerID(customerID);
                if (customer != null) {
                    StringBuilder details = new StringBuilder();
                    details.append("ID: ").append(customer.getID())
                            .append("\nName: ").append(customer.getName())
                            .append("\nSurname: ").append(customer.getSurname())
                            .append("\nDate of Birth: ").append(customer.getDateOfBirth())
                            .append("\nRegistration Date: ").append(customer.getRegistrationDate())
                            .append("\nSubscription: ").append(customer instanceof Subscribed ? "Yes" : "No")
                            .append("\nOrders: ").append(customer.getOrders().size());

                    resultArea.setText(details.toString());
                } else {
                    resultArea.setText("Customer not found.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID. Please enter a numeric value.");
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void createPutOrderForm() {
        JTextField customerIdField = new JTextField();
        JTextField itemTypeField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField notesField = new JTextField();
        JComboBox<String> cleaningTypeBox = new JComboBox<>(new String[]{"Wet", "Dry"});
        JButton addOrderButton = new JButton("Add Order");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        dynamicPanel.removeAll();
        dynamicPanel.add(new JLabel("Customer ID:"));
        dynamicPanel.add(customerIdField);

        dynamicPanel.add(new JLabel("Item Type:"));
        dynamicPanel.add(itemTypeField);

        dynamicPanel.add(new JLabel("Price:"));
        dynamicPanel.add(priceField);

        dynamicPanel.add(new JLabel("Quantity:"));
        dynamicPanel.add(quantityField);

        dynamicPanel.add(new JLabel("Notes:"));
        dynamicPanel.add(notesField);

        dynamicPanel.add(new JLabel("Cleaning Type:"));
        dynamicPanel.add(cleaningTypeBox);

        dynamicPanel.add(new JLabel());
        dynamicPanel.add(addOrderButton);

        dynamicPanel.add(new JLabel("Result:"));
        dynamicPanel.add(new JScrollPane(resultArea));

        addOrderButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerIdField.getText());
                Customer customer = CyprusDryClean.findCustomerID(customerId);

                if (customer == null) {
                    resultArea.setText("Customer not found. Please enter a valid ID.");
                    return;
                }


                String itemType = itemTypeField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String notes = notesField.getText();
                String cleaningType = (String) cleaningTypeBox.getSelectedItem();


                Employee assignedEmployee = CyprusDryClean.getRandomEmployee();
                LaundryItem laundryItem;


                if ("Wet".equalsIgnoreCase(cleaningType)) {
                    laundryItem = new WetClean(assignedEmployee, notes, quantity, (int) price, itemType);
                } else {
                    laundryItem = new DryClean(assignedEmployee, notes, quantity, (int) price, itemType);
                }


                Order newOrder = new Order();
                newOrder.getItemList().add(laundryItem);
                customer.getOrders().add(newOrder);

                resultArea.setText("Order added successfully!\n" +
                        "Assigned Employee: " + assignedEmployee.getName() + "\n" +
                        "Item Type: " + itemType + "\n" +
                        "Quantity: " + quantity + "\n" +
                        "Total Cost: " + laundryItem.totalCost());
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please check your entries.");
            } catch (Exception ex) {
                resultArea.setText("An error occurred: " + ex.getMessage());
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void createCustomerOrderDetailsForm() {

        JTextField customerIdField = new JTextField();
        JTextField orderDateField = new JTextField();
        JButton searchButton = new JButton("Search Orders");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        dynamicPanel.removeAll();
        dynamicPanel.add(new JLabel("Customer ID:"));
        dynamicPanel.add(customerIdField);

        dynamicPanel.add(new JLabel("Order Date (dd-MM-yyyy):"));
        dynamicPanel.add(orderDateField);

        dynamicPanel.add(new JLabel());
        dynamicPanel.add(searchButton);

        dynamicPanel.add(new JLabel("Order Details:"));
        dynamicPanel.add(new JScrollPane(resultArea));


        searchButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerIdField.getText());
                Date orderDate = dateConversion(orderDateField.getText());
                Customer customer = CyprusDryClean.findCustomerID(customerId);

                if (customer == null) {
                    resultArea.setText("Customer not found.");
                    return;
                }

                boolean orderFound = false;
                StringBuilder details = new StringBuilder();

                for (Order order : customer.getOrders()) {
                    if (CyprusDryClean.isSameDay(order.getOrderDate(), orderDate)) {
                        orderFound = true;
                        details.append("Order Date: ").append(order.getOrderDate()).append("\n")
                                .append("Paid Status: ").append(order.isPaidStatus() ? "Yes" : "No").append("\n")
                                .append("Items:\n");
                        for (LaundryItem item : order.getItemList()) {
                            details.append("- ").append(item.getType())
                                    .append(", Quantity: ").append(item.getQuantity());
                        }
                        details.append("\n");
                    }
                }

                if (orderFound) {
                    resultArea.setText(details.toString());
                } else {
                    resultArea.setText("No orders found for the given date.");
                }
            } catch (Exception ex) {
                resultArea.setText("Invalid input. Please check your entries.");
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void createCustomerOrderTotalCostForm() {
        JTextField customerIdField = new JTextField();
        JTextField orderDateField = new JTextField();
        JButton calculateButton = new JButton("Calculate Total Cost");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        dynamicPanel.removeAll();
        dynamicPanel.add(new JLabel("Customer ID:"));
        dynamicPanel.add(customerIdField);

        dynamicPanel.add(new JLabel("Order Date (dd-MM-yyyy):"));
        dynamicPanel.add(orderDateField);

        dynamicPanel.add(new JLabel());
        dynamicPanel.add(calculateButton);

        dynamicPanel.add(new JLabel("Total Cost:"));
        dynamicPanel.add(new JScrollPane(resultArea));


        calculateButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerIdField.getText());
                Date orderDate = dateConversion(orderDateField.getText());
                Customer customer = CyprusDryClean.findCustomerID(customerId);

                if (customer == null) {
                    resultArea.setText("Customer not found.");
                    return;
                }

                boolean orderFound = false;
                double totalCost = 0;

                for (Order order : customer.getOrders()) {
                    if (CyprusDryClean.isSameDay(order.getOrderDate(), orderDate)) {
                        orderFound = true;
                        totalCost += order.totalOrderCost();
                    }
                }

                if (orderFound) {
                    double discount = customer.calculateDiscount();
                    double finalCost = totalCost - discount;
                    resultArea.setText("Original Total Cost: " + totalCost + "\n" +
                            "Discount: " + discount + "\n" +
                            "Total Cost after Discount: " + finalCost);
                } else {
                    resultArea.setText("No orders found for the given date.");
                }
            } catch (Exception ex) {
                resultArea.setText("Invalid input. Please check your entries.");
            }
        });

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void displayAllEmployees() {
        dynamicPanel.removeAll();
        JTextArea employeeListArea = new JTextArea(20, 60);
        employeeListArea.setEditable(false);
        employeeListArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        StringBuilder employeeList = new StringBuilder();


        for (Employee employee : CyprusDryClean.empList) {
            employeeList.append("ID: ").append(employee.getID())
                    .append(", Name: ").append(employee.getName())
                    .append(", Surname: ").append(employee.getSurname())
                    .append(", Date of Birth: ").append(employee.getDateOfBirth())
                    .append(", Start Date: ").append(employee.getStartDate())
                    .append(", Work Permit End Date:").append(employee.getWorkPermitEndDate())
                    .append("\n");
        }


        if (employeeList.length() == 0) {
            employeeListArea.setText("No employees found.");
        } else {
            employeeListArea.setText(employeeList.toString());
        }

        dynamicPanel.add(new JLabel("Employee List:"));
        dynamicPanel.add(new JScrollPane(employeeListArea));

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }



    private void displayAllCustomers() {
        dynamicPanel.removeAll();
        JTextArea customerListArea = new JTextArea(20, 60);
        customerListArea.setEditable(false);
        customerListArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        StringBuilder customerList = new StringBuilder();


        for (Customer customer : CyprusDryClean.customerList) {
            customerList.append("ID: ").append(customer.getID())
                    .append(", Name: ").append(customer.getName())
                    .append(", Surname: ").append(customer.getSurname())
                    .append(", Date of Birth: ").append(customer.getDateOfBirth())
                    .append(", Registration Date: ").append(customer.getRegistrationDate())
                    .append(", Subscription: ").append(customer instanceof Subscribed ? "Yes" : "No")
                    .append("\n");
        }


        if (customerList.length() == 0) {
            customerListArea.setText("No customers found.");
        } else {
            customerListArea.setText(customerList.toString());
        }

        dynamicPanel.add(new JLabel("Customer List:"));
        dynamicPanel.add(new JScrollPane(customerListArea));

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }


    private void createAddHealthInspectionForm() {
        JTextField idField = new JTextField();
        JTextField inspectionDetailsField = new JTextField();
        JButton addButton = new JButton("Add Health Inspection");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        dynamicPanel.add(new JLabel("Employee ID:"));
        dynamicPanel.add(idField);

        dynamicPanel.add(new JLabel("Inspection Details:"));
        dynamicPanel.add(inspectionDetailsField);

        dynamicPanel.add(new JLabel());
        dynamicPanel.add(addButton);

        dynamicPanel.add(new JLabel("Result:"));
        dynamicPanel.add(new JScrollPane(resultArea));

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String inspectionDetails = inspectionDetailsField.getText();
                Employee employee = CyprusDryClean.findEmployeeID(id);

                if (employee != null) {
                    Date currentDate = new Date();
                    employee.getHealthInspection().put(currentDate, inspectionDetails);
                    resultArea.setText("Health inspection added for Employee ID: " + id);
                } else {
                    resultArea.setText("Employee not found!");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID format!");
            }
        });
    }
    private void createCompareCustomerLoyaltyForm() {
        JTextField customerId1Field = new JTextField();
        JTextField customerId2Field = new JTextField();
        JButton compareButton = new JButton("Compare");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        dynamicPanel.add(new JLabel("Customer ID 1:"));
        dynamicPanel.add(customerId1Field);

        dynamicPanel.add(new JLabel("Customer ID 2:"));
        dynamicPanel.add(customerId2Field);

        dynamicPanel.add(new JLabel());
        dynamicPanel.add(compareButton);

        dynamicPanel.add(new JLabel("Result:"));
        dynamicPanel.add(new JScrollPane(resultArea));

        compareButton.addActionListener(e -> {
            try {
                int customerId1 = Integer.parseInt(customerId1Field.getText());
                int customerId2 = Integer.parseInt(customerId2Field.getText());

                Customer customer1 = CyprusDryClean.findCustomerID(customerId1);
                Customer customer2 = CyprusDryClean.findCustomerID(customerId2);

                if (customer1 != null && customer2 != null) {
                    int comparison = customer1.compareTo(customer2);

                    if (comparison > 0) {
                        resultArea.setText("Customer ID: " + customerId1 + " is more loyal.");
                    } else if (comparison < 0) {
                        resultArea.setText("Customer ID: " + customerId2 + " is more loyal.");
                    } else {
                        resultArea.setText("Both customers are equally loyal.");
                    }
                } else {
                    resultArea.setText("One or both customers not found!");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID format!");
            }
        });
    }



    public static void main(String[] args) {
        //read the data from file
        CyprusDryClean.empList = EmployeeIO.readFromFile();
        CyprusDryClean.customerList = new ArrayList<>();
        CyprusDryClean.customerList.addAll(SubscribedIO.readFromFile());
        CyprusDryClean.customerList.addAll(UnSubscribedIO.readFromFile());

        //start the gui
        CyprusClean gui = new CyprusClean();
        JFrame frame = gui.frame;
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }





}
