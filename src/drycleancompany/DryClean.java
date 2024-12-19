package drycleancompany;

/**
 * This class extends the LaundryItem class and adds specific total cost calculation.
 * @author İpek Gültekin
 */
public class DryClean extends LaundryItem{

    /**
     * Constructor for creating a DryClean object, and it calls LaundryItem's related constructor with super keyword.
     *
     * @param assigned  employee assigned
     * @param notes notes
     * @param quantity  number of items
     * @param price  price per item
     * @param type  type of laundry item (e.g., Shirt, Jean, etc.)
     */
    public DryClean(Employee assigned, String notes, int quantity, int price, String type) {
        super(assigned, notes, quantity, price, type);
    }

    /**
     * Constructor for creating a DryClean object, and it calls LaundryItem's related constructor with super keyword.
     *
     * @param price  price per item
     * @param quantity  number of items
     * @param type  type of laundry item (e.g., Shirt, Jean, etc.)
     * @param assigned  employee assigned
     */
    public DryClean(int price, int quantity, String type, Employee assigned) {
        super(price, quantity, type, assigned);
    }

    /**
     * Constructor for creating a DryClean object, and it calls LaundryItem's related constructor with super keyword.
     *
     * @param assigned  employee assigned
     * @param price  price per item
     * @param quantity  number of items
     */
    public DryClean(Employee assigned, int price, int quantity) {
        super(assigned, price, quantity);
    }


    /**
     * This method calculates the total cost of the company, factoring in the quantity,
     * price per item, and electricity rate (they become from interface).
     *
     * @return  total cost for dry cleaning
     */
    @Override
    public double totalCost() {
        return getQuantity()*getPrice()*electricityRate;
    }
}
