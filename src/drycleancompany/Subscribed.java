package drycleancompany;

import java.io.Serializable;
import java.sql.Date;

public class Subscribed extends Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date subscriptionDate;
    private double depositPaid;

    public Subscribed(double depositPaid, Date subscriptionDate) {
        super();
        this.depositPaid = depositPaid;
        this.subscriptionDate = subscriptionDate != null ? subscriptionDate : new Date(System.currentTimeMillis());
    }

    public Subscribed(int customerID, String name, String surname, Date dateOfBirth, Date registrationDate, Date subscriptionDate, double depositPaid) {
        super(dateOfBirth, customerID, name, surname);
        this.setRegistrationDate(registrationDate != null ? registrationDate : new Date(System.currentTimeMillis()));
        this.subscriptionDate = subscriptionDate != null ? subscriptionDate : this.getRegistrationDate();
        this.depositPaid = depositPaid;
    }

    public Subscribed(int id, String name, String surname, java.util.Date dateOfBirth, Date registrationDate, double deposit) {
        super(new Date(dateOfBirth.getTime()), id, name, surname);
        this.depositPaid = deposit;
        this.subscriptionDate = registrationDate != null ? registrationDate : new Date(System.currentTimeMillis());
    }

    public Subscribed() {

    }

    public double getDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(double depositPaid) {
        this.depositPaid = depositPaid;
    }

    public java.sql.Date getSubscriptionDate() {
        return subscriptionDate != null ? new java.sql.Date(subscriptionDate.getTime()) : null;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate != null ? new Date(subscriptionDate.getTime()) : null;
    }

    @Override
    public Date getDateOfBirth() {
        return super.getDateOfBirth();
    }

    @Override
    public void setDateOfBirth(java.util.Date dateOfBirth) {
        super.setDateOfBirth(new Date(dateOfBirth.getTime()));
    }

    @Override
    public String toString() {
        return super.toString() + ", Subscription: Subscribed, Deposit Paid: " + depositPaid;
    }

    @Override
    public double calculateDiscount() {
        double totalCost = calculateTotalIncome();
        return totalCost * 0.10;
    }

    @Override
    public double calculateTotalIncome() {
        return this.getOrders().stream().mapToDouble(Order::totalOrderCost).sum();
    }
}
