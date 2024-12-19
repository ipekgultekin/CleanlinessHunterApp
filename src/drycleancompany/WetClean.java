package drycleancompany;

/**
 * This class represents a wet cleaning service for laundry items. It extends the LaundryItem class.
 * @author İpek Gültekin
 */
public class WetClean extends LaundryItem{


    /**
     * Constructor to create a WetClean laundry item, and it calls the LaundryItem's related constructor with super keyword.
     *
     * @param assigned employee assigned
     * @param notes    notes
     * @param quantity quantity of the items
     * @param price    price per item
     * @param type     type of item (e.g., T-shirt, jeans, etc.).
     */
    public WetClean(Employee assigned, String notes, int quantity, int price, String type) {
        super(assigned, notes, quantity, price, type);
    }


    /**
     * Constructor to create a WetClean laundry item, and it calls the LaundryItem's related constructor with super keyword.
     *
     * @param assigned employee assigned
     * @param quantity quantity of the items
     * @param price    price per item
     * @param type     type of item (e.g., T-shirt, jeans, etc.).
     */
    public WetClean(int price, int quantity, String type, Employee assigned) {
        super(price, quantity, type, assigned);
    }


    /**
     * Constructor to create a WetClean laundry item, and it calls the LaundryItem's related constructor with super keyword.
     * @param assigned employee assigned
     * @param quantity quantity of the items
     * @param price    price per item
     */
    public WetClean(Employee assigned, int price, int quantity) {
        super(assigned, price, quantity);
    }


    /**
     * Calculates the total cost of the wet cleaning service for the item.
     * The cost is based on quantity, price, water rate, and ironing rate (they become from interface).
     *
     * @return total cost of wet cleaning
     */
    @Override
    public double totalCost() {
        return getQuantity()*getPrice()*waterRate + ironingRate*getQuantity();
    }
}
