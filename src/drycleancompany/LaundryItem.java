package drycleancompany;

/**
 * This class contains Laundry item details such as type, quantity, price, notes, assigned employee,
 * getter and setter methods, constructors, and extra methods.
 * And it implements an interface.
 * @author İpek Gültekin
 */
public abstract class LaundryItem implements CleaningStyle {
    private String type;
    private int quantity;
    private int price;
    private String notes;
    private Employee assigned;



    /**
     * Constructor for creating a LaundryItem with all details.
     *
     * @param assigned  employee assigned to laundry item
     * @param notes notes
     * @param quantity  number of items
     * @param price  price per item
     * @param type  type of laundry item (e.g., Shirt, Jean, T-shirt)
     */
    public LaundryItem(Employee assigned, String notes, int quantity, int price, String type) {
        this.assigned = assigned;
        this.notes = notes;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    /**
     * Constructor for creating a LaundryItem
     *
     * @param price  price per item
     * @param quantity  number of items
     * @param type  type of laundry item
     * @param assigned employee assigned to laundry item
     */
    public LaundryItem(int price, int quantity, String type, Employee assigned) {
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.assigned = assigned;
    }

    /**
     * Constructor for creating a LaundryItem
     *
     * @param assigned  employee assigned to the laundry item
     * @param price  price per item
     * @param quantity  number of items
     */
    public LaundryItem(Employee assigned, int price, int quantity) {
        this.assigned = assigned;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * This abstract method calculate the total cost of the laundry item.
     * This method must be implemented by subclasses (DryClean or WetClean).
     *
     * @return  total cost of the laundry item based on its quantity, price, and type.
     */
    public abstract double totalCost();

    /**
     * Gets the employee assigned to this laundry item.
     *
     * @return  assigned employee.
     */
    public Employee getAssigned() {
        return assigned;
    }

    /**
     * Sets the employee assigned to this laundry item.
     *
     * @param assigned employee to be assigned.
     */
    public void setAssigned(Employee assigned) {
        this.assigned = assigned;
    }

    /**
     * Gets the notes related to this laundry item.
     *
     * @return  notes associated with this laundry item.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes related to this laundry item.
     *
     * @param notes  notes to be set for this item.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets the price per item.
     *
     * @return The price of a single item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price per item.
     *
     * @param price The price to be set for a single item.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the type of laundry item (e.g., Shirt, T-shirt, Jean).
     *
     * @return The type of laundry item.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the laundry item.
     *
     * @param type type to be set for the laundry item.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the quantity of laundry items being processed.
     *
     * @return quantity of laundry items.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of laundry items being processed.
     *
     * @param quantity  quantity of items to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Provides a string representation of the LaundryItem.
     *
     * @return A string describing the details of the laundry item.
     */
    @Override
    public String toString() {
        return "LaundryItem{" +
                "assigned=" + assigned +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                '}';
    }
}
