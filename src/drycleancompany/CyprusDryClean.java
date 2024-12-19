package drycleancompany;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class manages the basic functionality of the CyprusDryClean application.
 * @author İpek Gültekin
 * @version 22.0.2
 */
public class CyprusDryClean {
    public static ArrayList<Employee> empList = new ArrayList<>();
    public static ArrayList<Customer> customerList = new ArrayList<>();

    /**
     *
     The main method that starts the CyprusDryClean application.
     * It initializes test data, creates an instance of the application,
     * and displays the main menu for user interaction.
     * @param args  Command-line arguments but not used.
     * @throws ParseException if there is an error while parsing dates during data population (checked exception).
     */
    public static void main(String[] args) throws ParseException {
        //PopulateData.populate();
        CyprusDryClean obj = new CyprusDryClean();
        //obj.menu();

    }

    /**
     * This method displays a menu for user.
     */
    public void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to CyprusDry Clean!!");
        int choice;
        do{
            System.out.println("1. Add Employee\n"
                    + "2. Delete Employee\n"
                    + "3. List Employee Details\n"
                    + "4. Add Customer\n"
                    + "5. Delete Customer\n"
                    + "6. List Customer Details\n"
                    + "7. Put Order\n"
                    + "8. Customer Order Details\n"
                    + "9. Customer Order Total Cost\n"
                    + "10. List Employees\n"
                    + "11. List Customers\n"
                    + "12. Add Health Inspection\n"
                    + "13. Compare Customer Loyalty\n"
                    + "14. Exit\n"
            );

            System.out.print("Enter a choice: ");
            choice = input.nextInt();

            switch(choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    System.out.print("Please enter employee ID to delete: ");
                    int employeeID = input.nextInt();
                    deleteUser(employeeID);
                    break;
                case 3:
                    System.out.print("Please enter employee ID to list of details: ");
                    employeeID = input.nextInt();
                    listEmployeeDetails(employeeID);
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    System.out.print("Please enter customer ID to delete: ");
                    int customerID = input.nextInt();
                    deleteCustomer(customerID);
                    break;
                case 6:
                    System.out.print("Please enter customer ID to list of details: ");
                    customerID = input.nextInt();
                    getCustomerDetails(customerID);
                    break;
                case 7:
                    System.out.print("Please enter customer ID: ");
                    customerID = input.nextInt();
                    putOrder(customerID);
                    break;
                case 8:
                    System.out.print("Please enter customer ID: ");
                    customerID = input.nextInt();
                    input.nextLine();
                    System.out.print("Please enter the order date to check (dd-MM-yyyy): ");
                    String dateOrder = input.nextLine();
                    Date orderDate = dateConversion(dateOrder);

                    getCustomerOrderDetails(customerID,orderDate);
                    break;
                case 9:
                    System.out.print("Please enter customer ID: ");
                    customerID = input.nextInt();
                    input.nextLine();
                    System.out.print("Please enter the order date to check (dd-MM-yyyy): ");
                    String costDate = input.nextLine();
                    Date orderCostDate = dateConversion(costDate);

                    getCustomerOrderTotalCost(customerID, orderCostDate);
                    break;
                case 10:
                    listEmployees();
                    break;
                case 11:
                    listCustomers();
                    break;
                case 12:
                    System.out.print("Please enter employee ID to add health inspection: ");
                    employeeID = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter health inspection info: ");
                    String info = input.nextLine();
                    addHealthInspection(employeeID,info);
                    break;
                case 13:
                    System.out.println("Enter first customer ID: ");
                    int customerID1 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter second customer ID: ");
                    int customerID2 = input.nextInt();
                    input.nextLine();
                    compareCustomerLoyalty(customerID1,customerID2);
                    break;
                case 14:
                    exit();
                    break;
                default:
                    System.out.print("Invalid choice!! ");
                    System.out.print("Sorry!");
                    break;
            }
        }while(choice !=14);
    }


    /**
     * This helper method converts a date from String format to Date format.
     * @param string date in String format
     * @return the date in Date format
     */
    public static Date dateConversion(String string){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(string);

        }catch (ParseException e){
            System.out.println("Invalid date format!");
        }

        return date;

    }

    /**
     * This method adds a new Employee to the employeeList
     * also it controls the ID to be unique.
     */
    public static void addEmployee() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter new employee id: ");
        int id = input.nextInt();
        input.nextLine();
        for (Employee employee : empList) {
            if (id == employee.getID())
            {
                System.out.print("Invalid ID! This ID has already taken!");
                return;
            }
        }

        System.out.print("Enter new employee name: ");
        String name = input.nextLine();

        System.out.print("Enter new employee surname: ");
        String surname = input.nextLine();

        System.out.print("Enter new employee's date of birth (dd-MM-yyyy): ");
        String dateBirth = input.nextLine();
        Date dateOfBirth = dateConversion(dateBirth);

        System.out.print("Enter new employee's start date (dd-MM-yyyy): ");
        String dateStart = input.nextLine();
        Date startDate = dateConversion(dateStart);

        System.out.print("Enter new employee's nationality: ");
        String nationality = input.nextLine();

        System.out.print("Enter new employee's work permit end date (dd-MM-yyyy): ");
        String workPermitEnd = input.nextLine();
        Date workPermitEndDate = dateConversion(workPermitEnd);


        Employee newEmployee = new Employee(dateOfBirth, id, name, surname, nationality, startDate, workPermitEndDate);
        empList.add(newEmployee);
        System.out.println("New employee added successfully!");
    }


    /**
     * This helper method check that the employee ID entered by the user and the IDs in the system match.
     * @param empID the ID of the employee to finding
     * @return null if they are not match
     */
    public static Employee findEmployeeID(int empID){
        for(User user : empList){
            if(user instanceof Employee employee){
                if(employee.getID() == empID){
                    return employee;
                }
            }
        }
        return null;
    }

    /**
     * This helper method check that the customer ID entered by the user and the IDs in the system match.
     * @param customerID the ID of the employee to finding
     * @return null if they are not match
     */
    public static Customer findCustomerID(int customerID){
        for(User user : customerList){
            if(user instanceof Customer customer){
                if(customer.getID() == customerID){
                    return customer;
                }
            }

        }
        return null;
    }

    /**
     * This method deletes an Employee from the employeeList based on the ID.
     * @param empID the ID of the employee
     */
    public static void deleteUser(int empID) {
        Employee employee = findEmployeeID(empID);
        if (employee != null) {
            empList.remove(employee);
            System.out.println("Employee deleted successfully.");
        }
        else{
            System.out.println("Employee not found! Please enter a valid ID.");
        }

    }

    /**
     * This method shows the employee details only id, name, surname, date of birth, and start date.
     * @param empID the ID of the employee
     */
    public static void listEmployeeDetails(int empID){
        Employee employee = findEmployeeID(empID);
        if(employee != null){
            System.out.println("Employee is found!");
            System.out.println("Employee ID: " + employee.getID());
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Employee Surname: " + employee.getSurname());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("Employee Date of Birth: " + dateFormat.format(employee.getDateOfBirth()));
            System.out.println("Employee Start Date: " + employee.getStartDate());

        }
        else
            System.out.print("Sorry, employee not found.");
    }


    /**
     * This method adds a new Customer to the customerList
     * also it controls the ID to be unique.
     */
    public void addCustomer(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter customer id: ");
        int id = input.nextInt();
        input.nextLine();
        for (Customer customer : customerList) {
            if (id == customer.getID())
            {
                System.out.println("Invalid ID! This ID has already taken!");
                return;
            }
        }

        System.out.print("Enter customer name: ");
        String customerName = input.nextLine();

        System.out.print("Enter customer surname: ");
        String customerSurname = input.nextLine();

        System.out.print("Enter customer's date of birth (dd-MM-yyyy) : ");
        String dateBirth = input.nextLine();
        Date dateOfBirth = dateConversion(dateBirth);


        Date registrationDate = new Date();

        System.out.print("Would you like to become subscribed? (yes/no): ");
        String subscribed = input.nextLine();

        if(subscribed.equalsIgnoreCase("yes")){
            double subscribedDeposit = customerManagement.minDeposit;
            double newCustomerDeposit;

            System.out.print("Enter deposit amount: ");
            newCustomerDeposit = input.nextDouble();
            input.nextLine();

            if(newCustomerDeposit >= subscribedDeposit){
                System.out.println("Thanks for deposit.");
            }
            else {
                System.out.println("Deposit can not be less than " + customerManagement.minDeposit + " .");
                return;
            }



            Subscribed subscribedCustomer = new Subscribed(subscribedDeposit, registrationDate);
            subscribedCustomer.setID(id);
            subscribedCustomer.setName(customerName);
            subscribedCustomer.setSurname(customerSurname);
            subscribedCustomer.setDateOfBirth(dateOfBirth);
            subscribedCustomer.setRegistrationDate(registrationDate);
            subscribedCustomer.setSubscriptionDate(registrationDate);
            subscribedCustomer.setDepositPaid(newCustomerDeposit);


            customerList.add(subscribedCustomer);
            System.out.println("Subscribed customer added.");
        }

        else{
            UnSubscribed unsubscribedCustomer = new UnSubscribed();
            unsubscribedCustomer.setID(id);
            unsubscribedCustomer.setName(customerName);
            unsubscribedCustomer.setSurname(customerSurname);
            unsubscribedCustomer.setDateOfBirth(dateOfBirth);
            unsubscribedCustomer.setRegistrationDate(registrationDate);

            customerList.add(unsubscribedCustomer);
            System.out.println("Unsubscribed customer added.");
        }
    }


    /**
     * This method deletes a Customer from the customerList based on the ID.
     * @param customerID the ID of the customer
     */
    public static void deleteCustomer(int customerID) {
        Customer customer = findCustomerID(customerID);
        if (customer != null) {
            customerList.remove(customer);
            System.out.println("Customer deleted successfully.");
        }
        else{
            System.out.println("Customer not found! Please enter a valid ID.");
        }
    }


    /**
     * This method shows the customer details only id, name, surname, date of birth, and start date.
     * @param customerID the ID of the customer
     */
    public static void getCustomerDetails(int customerID){
        Customer customer = findCustomerID(customerID);
        if(customer != null){
            System.out.println("Customer is found!");
            System.out.println("Customer ID: " + customer.getID());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Surname: " + customer.getSurname());
            System.out.println("Registration Date: " + customer.getRegistrationDate());
            System.out.println("Orders: " + customer.getOrders());
            System.out.println("Num of Orders: " + customer.getOrders().size());

        }
        else
            System.out.println("Sorry, customer not found.");
    }


    /**
     * This helper method allows laundry items to be given to employees randomly.
     * @return a randomly selected Employee object
     */
    public static Employee getRandomEmployee(){
        Random random = new Random();
        return empList.get(random.nextInt(empList.size()));
    }


    /**
     *This method places an order for a customer, and assigns to each employee to each item, and adds the items their order depends on the laundry item type (wet or dry).
     * @param customerID the ID of the customer
     */
    public void putOrder(int customerID){
        Customer customer = findCustomerID(customerID);
        if(customer == null){
            System.out.println("Customer does not found. Please enter valid customer ID.");
            return;
        }

        Scanner input = new Scanner(System.in);

        Order order = new Order();
        System.out.print("Paid status -> (yes/no)");
        boolean isPaid = input.nextLine().equalsIgnoreCase("yes");

        System.out.print("Enter the number of laundry items: ");
        int numberOfItems = input.nextInt();
        input.nextLine();

        ArrayList<LaundryItem> laundryItems = new ArrayList<>();

        for(int i=0; i<numberOfItems; i++){
            System.out.println("Enter the type of item (Shirt, Jean, Dress, T-Shirt): ");
            String itemType = input.nextLine();

            System.out.println("Enter price per item: ");
            int priceItem = input.nextInt();
            input.nextLine();

            System.out.println("Enter quantity of " + itemType + ": ");
            int quantity = input.nextInt();
            input.nextLine();

            System.out.println("Enter notes: ");
            String notes = input.nextLine();

            System.out.println("Enter your cleaning choice wet or dry? (wet/dry): ");
            String cleanChoice = input.nextLine();

            Employee employeeAssigned = getRandomEmployee();
            LaundryItem laundryItem;
            if(cleanChoice.equalsIgnoreCase("wet")){
                laundryItem = new WetClean(employeeAssigned,notes,quantity,priceItem,itemType);
            }
            else{
                laundryItem = new DryClean(employeeAssigned,notes,quantity,priceItem,itemType);
            }
            laundryItems.add(laundryItem);
            System.out.println("\nThank you for choosing us!\n" +
                    "Your order added successfully!\n" +
                    quantity + " " +itemType + " assigned to " + employeeAssigned.getName()
                    + " and its cost is " + laundryItem.totalCost() + " $.");



        }

        order.setItemList(laundryItems);
        customer.getOrders().add(order);
        System.out.println("Putting order successfully!");

    }


    /**
     * Compares two Date objects and checks if they represent the same calendar day
     * by comparing only the year, month, and day components, ignoring the time.
     * @param date1 first date
     * @param date2 second date
     * @return true if both dates are equal
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * This method displays all orders made by a specific customer on a given date.
     * @param customerID the ID of the customer
     * @param orderDate the specific date of order
     */
    public void getCustomerOrderDetails(int customerID, Date orderDate){
        Customer customer = findCustomerID(customerID);
        if(customer == null){
            System.out.println("Customer is not found!");
            return;
        }

        if (orderDate == null) {
            System.out.println("Invalid date. Please enter a valid date.");
            return;
        }

        boolean orderFound = false;

        ArrayList<Order> orders = customer.getOrders();

        for (Order order: orders){
            if(isSameDay(orderDate,order.getOrderDate())){
                orderFound = true;
                System.out.println("\nOrder date: " + order.getOrderDate());
                System.out.println("Paid status: " + ((order.isPaidStatus()) ? "Yes" : "No"));
                System.out.println("Laundry items: ");

                for(LaundryItem laundryItem : order.getItemList()){
                    System.out.println("  Item type: " + laundryItem.getType()+ ",  Quantity: " + laundryItem.getQuantity());
                }
            }

        }
        if(!orderFound){
            System.out.println("Sorry, order is not found for this customer and this date.");
        }
    }

    /**
     * This method calculate and display the total cost of all orders made by a specific customer on a given date.
     * @param customerID the ID of the customer
     * @param orderDate specific date
     */
    public void getCustomerOrderTotalCost(int customerID, Date orderDate){
        Customer customer = findCustomerID(customerID);
        if(customer == null){
            System.out.println("Customer is not found!");
            return;
        }
        ArrayList<Order> orders = customer.getOrders();
        boolean orderFound = false;

        for(Order order : orders){
            if(isSameDay(order.getOrderDate(), orderDate)){
                orderFound = true;
                double totalCost = order.totalOrderCost();
                double discount = customer.calculateDiscount();

                double finalCost = totalCost - discount;

                System.out.println("Thank you for choosing us!");
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Original Total Cost: " + totalCost + " $");
                System.out.println("Discount: " + discount + " $");
                System.out.println("Total Cost after Discount: " + finalCost + " $");

            }
        }


    }

    /**
     * This method lists of all employees with their all details
     */
    public void listEmployees() {
        System.out.println("List of Employees");
        for (Employee employee : empList) {
            System.out.println("Employee ID:" + employee.getID() +
                    " Name/Surname: " + employee.getName() + " " + employee.getSurname() +
                    " Date of Birth: " + employee.getDateOfBirth()+
                    " Start Date: " + employee.getStartDate() +
                    " Nationality: " + employee.getNationality() +
                    " Work Permit End Date: " + employee.getWorkPermitEndDate() +
                    " Health Inspection: " + employee.getHealthInspection());
        }
    }


    /**
     * This method lists of all customers with their all details
     */
    public void listCustomers() {
        System.out.println("List of Employees");
        for (Customer customer : customerList) {
            System.out.println("Customer ID:" + customer.getID() +
                    " Name/Surname: " + customer.getName() + " " + customer.getSurname() +
                    " Date of Birth: " + customer.getDateOfBirth()+
                    " Registration Date: " + customer.getRegistrationDate() +
                    " Number of Orders: " + customer.getOrders().size() +
                    " Total Orders: " + customer.toString());


            if(customer instanceof Subscribed){
                System.out.println("Subscribed type: Subscribed");
            }
            else if(customer instanceof UnSubscribed){
                System.out.println("Subscribed type: Unsubscribed");
            }
            else {
                System.out.println("Error. Incorrect type!");
            }
        }
    }

    /**
     * This method contains a message to end the program.
     */
    public void exit(){
        System.out.println("Have a good day!!!");
    }

    /**
     * This method adds a health inspection record for a specific employee on the current date.
     * @param empID the ID of the employee
     * @param info information about health inspection
     */
    public void addHealthInspection (int empID, String info){
        Employee employee = findEmployeeID(empID);
        if(employee == null){
            System.out.println("Employee is not found!");
        }

        Date todaysDate = new Date();

        HashMap<Date, String> healthInspection = employee.getHealthInspection();
        if(healthInspection.containsKey(todaysDate)){ // Check if a health inspection is already recorded for today
            System.out.println("Health inspection for today " + todaysDate + " is recorded for employee " + employee.getName() + " .");
            return;
        }

        healthInspection.put(todaysDate,info); // Add the new health inspection record
        System.out.println("Recorded successfully.");

    }

    /**
     * This method compares two customers using their total income and outputs who contributed more.
     * @param customerID1 the ID of the customer 1
     * @param customerID2 the ID of the customer 2
     */
    public void compareCustomerLoyalty(int customerID1, int customerID2) {
        Customer customer1 = findCustomerID(customerID1);
        Customer customer2 = findCustomerID(customerID2);

        if (customer1 == null || customer2 == null) {
            System.out.println("One or both customers not found. Please check the IDs.");
            return;
        }

        int result = customer1.compareTo(customer2);

        if(result > 0){
            System.out.println("Customer 1 " + customer1.getName() + " has more amount of income.");
        }

        else if(result < 0){
            System.out.println("Customer 2 " + customer2.getName() + " has more amount of income.");
        }

        else{
            System.out.println("Both customers have the same amount of income. ");

        }
    }




}