package drycleancompany;

/**
 * This interfaces provides methods for calculating discounts and total income for customers, and defines a minimum deposit constant.
 * @author İpek Gültekin
 */
public interface  customerManagement {
    double minDeposit = 10.0;
    double calculateDiscount();
    double calculateTotalIncome();
}
