package drycleancompany;

import java.util.Date;

/**
 * This class represents a customer who has a subscription to company and extend from Customer superclass
 * @author İpek Gültekin
 */
public class Subscribed extends Customer{
    private Date subscriptionDate;
    private double depositPaid;

    /**
     * Constructor , and it calls related Customer's constructor with super keyword.
     *
     * @param depositPaid       amount of deposit
     * @param subscriptionDate  subscription date
     */
    public Subscribed(double depositPaid, Date subscriptionDate) {
        super();
        this.depositPaid = depositPaid;
        this.subscriptionDate = subscriptionDate;
    }

    /**
     * Constructor to create a Subscribed customer with a specified deposit.
     * The subscription date will be set to the default value (null).
     *
     * @param depositPaid amount of deposit
     */
    public Subscribed(double depositPaid) {
        this.depositPaid = depositPaid;
    }

    /**
     * Gets the deposit amount paid by the subscribed customer.
     *
     * @return The deposit paid by the customer.
     */
    public double getDepositPaid() {
        return depositPaid;
    }

    /**
     * Sets the deposit amount paid by the subscribed customer.
     *
     * @param depositPaid amount of deposit
     */
    public void setDepositPaid(double depositPaid) {
        this.depositPaid = depositPaid;
    }

    /**
     * Gets the subscription date of the customer.
     *
     * @return subscription date
     */
    public Date getSubscriptionDate() {
        return subscriptionDate;
    }


    /**
     * Sets the subscription date of the customer.
     *
     * @param subscriptionDate subscription date
     */
    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    /**
     * Returns a string representation of the Subscribed customer.
     *
     * @return A string representation of the Subscribed customer.
     */
    @Override
    public String toString() {
        return super.toString() + ", Subscription: Subscribed, Deposit Paid: " + getDepositPaid();
    }


    /**
     * Compares this Subscribed customer with another customer based on their total income.
     *
     * @param o The other customer
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
     * Calculates the discount for the subscribed customer, which is 10% of the total income.
     *
     * @return discount amount.
     */
    @Override
    public double calculateDiscount() {
        double totalCost = calculateTotalIncome();
        return totalCost*0.10;
    }


    /**
     * Calculates the total income for the subscribed customer by summing up the cost of all their orders.
     *
     * @return total income from all orders of the subscribed customer.
     */
    @Override
    public double calculateTotalIncome() {
        double totalIncome = 0;
        for(Order order : this.getOrders()){
            totalIncome += order.totalOrderCost();
        }
        return totalIncome;
    }
}
