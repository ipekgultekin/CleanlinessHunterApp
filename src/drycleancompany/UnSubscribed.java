package drycleancompany;

import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a customer who has not a subscription to company and extend from Customer superclass
 * @author İpek Gültekin
 */
public class UnSubscribed extends Customer{

    /**
     * Default Constructor
     */
    public UnSubscribed() {
    }

    /**
     * Constructor to create an unsubscribed customer, and it calls the Customer's related constructor with super keyword.
     *
     * @param dateOfBirth date of birth.
     * @param ID          ID of the customer.
     * @param name        name.
     * @param surname     last name.
     */
    public UnSubscribed(Date dateOfBirth, int ID, String name, String surname) {
        super(dateOfBirth, ID, name, surname);
    }

    /**
     * Constructor to create an unsubscribed customer, and it calls the Customer's related constructor with super keyword.
     *
     * @param ID      ID of the customer
     * @param name    name
     * @param surname last name
     */
    public UnSubscribed(int ID, String name, String surname) {
        super(ID, name, surname);
    }

    /**
     * Constructor to create an unsubscribed customer, and it calls the Customer's default constructor with super keyword.
     *
     * @param registrationDate date of registration
     */
    public UnSubscribed(Date registrationDate){
        super();
        setRegistrationDate(registrationDate);
    }

    /**
     * Compares this UnSubscribed customer with another customer based on their total income.
     *
     * @param o other customer to compare to
     * @return depend on the comparison
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Customer otherCustomer) {
            double thisIncome = this.calculateTotalIncome();
            double otherIncome = otherCustomer.calculateTotalIncome();
            return Double.compare(thisIncome, otherIncome);
        }
        return 0;
    }

    /**
     * Calculates the discount for the unsubscribed customer, which is 5% of the total income
     * if the customer has been registered for more than 10 years.
     *
     * @return discount amount
     */
    @Override
    public double calculateDiscount() {
        double totalCost = calculateTotalIncome();

        Calendar cal = Calendar.getInstance();
        cal.setTime(getRegistrationDate());
        cal.add(Calendar.YEAR,10);

        if(new Date().after(cal.getTime())){
            return totalCost * 0.05;
        }
        return 0.0;
    }

    /**
     * Calculates the total income for the unsubscribed customer by summing up the cost of all their orders.
     *
     * @return total income from all orders
     */
    @Override
    public double calculateTotalIncome() {
        double totalIncome = 0;
        for(Order order : this.getOrders()){
            totalIncome += order.totalOrderCost();
        }
        return totalIncome;
    }

    @Override
    public String toString() {
        return super.toString() + ", Subscription: Unsubscribed";
    }


}
