package drycleancompany;

import java.sql.Date;
import java.util.Calendar;

public class UnSubscribed extends Customer {

    public UnSubscribed() {
    }

    public UnSubscribed(int customerID, String name, String surname, Date dateOfBirth, Date registrationDate) {
        super(dateOfBirth, customerID, name, surname);
        this.setRegistrationDate(registrationDate != null ? registrationDate : new Date(System.currentTimeMillis()));
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Customer otherCustomer) {
            return Double.compare(this.calculateTotalIncome(), otherCustomer.calculateTotalIncome());
        }
        return 0;
    }

    @Override
    public double calculateDiscount() {
        double totalCost = calculateTotalIncome();
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getRegistrationDate());
        cal.add(Calendar.YEAR, 10);

        return new Date(System.currentTimeMillis()).after(new Date(cal.getTimeInMillis())) ? totalCost * 0.05 : 0.0;
    }

    @Override
    public double calculateTotalIncome() {
        double totalIncome = 0.0;
        for (Order order : this.getOrders()) {
            totalIncome += order.totalOrderCost();
        }
        return totalIncome;
    }

    @Override
    public String toString() {
        return super.toString() + ", Subscription: Unsubscribed";
    }
}
