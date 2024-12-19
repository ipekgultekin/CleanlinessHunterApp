package drycleancompany;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class contains Order details, getter and setter methods, constructors, and totalOrderCost method.
 * @author İpek Gültekin
 */
public class Order {
    private Date orderDate;
    private boolean paidStatus;
    private ArrayList<LaundryItem> itemList;

    /**
     * Default Constructor
     */
    public Order() {
        this.orderDate = new Date();
        this.itemList = new ArrayList<>();
    }

    /**
     * Constructor for creating an order with provided details.
     *
     * @param assigned  employee assigned to
     * @param notes notes related to the order
     * @param quantity quantity of items
     * @param price price of each item
     * @param type type of laundry items
     * @param itemList list of laundry items
     * @param paidStatus payment status
     * @param orderDate date the order
     */
    public Order(Employee assigned, String notes, int quantity, int price, String type, ArrayList<LaundryItem> itemList, boolean paidStatus, Date orderDate) {
        this.itemList = itemList;
        this.paidStatus = paidStatus;
        this.orderDate = orderDate;
    }

    /**
     * Constructor for creating an order with the list of items and order date.
     * Payment status is not set in this constructor.
     *
     * @param assigned  employee assigned to
     * @param notes notes related to the order
     * @param quantity quantity of items
     * @param price price of each item
     * @param type type of laundry items
     * @param itemList list of laundry items
     * @param orderDate date the order
     */
    public Order(Employee assigned, String notes, int quantity, int price, String type, ArrayList<LaundryItem> itemList, Date orderDate) {
        this.itemList = itemList;
        this.orderDate = orderDate;
    }

    /**
     * Calculates the total cost of the order by summing the cost of each laundry item.
     *
     * @return  total cost of the order.
     */
    public double totalOrderCost(){
        double totalCost = 0 ;
        for (LaundryItem item : itemList){
            totalCost += item.totalCost();
        }
        return totalCost;
    }

    /**
     * Gets the list of laundry items included in the order.
     *
     * @return  list of laundry items.
     */
    public ArrayList<LaundryItem> getItemList() {
        return itemList;
    }

    /**
     * Sets the list of laundry items for the order.
     *
     * @param itemList  list of laundry items to set.
     */
    public void setItemList(ArrayList<LaundryItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * Gets the date the order was placed.
     *
     * @return  order date.
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the date the order was placed.
     *
     * @param orderDate  date to set as the order date.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    /**
     * Gets the payment status of the order.
     *
     * @return True if the order is paid, false otherwise.
     */
    public boolean isPaidStatus() {
        return paidStatus;
    }


    /**
     * Sets the payment status of the order.
     *
     * @param paidStatus  payment status to set.
     */
    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    /**
     * Provides a string representation of the order details.
     *
     * @return A string representing the order, including the item list, order date, and payment status.
     */
    @Override
    public String toString() {
        return "Order{" +
                "itemList=" + itemList +
                ", Order Date=" + orderDate +
                ", Paid Status=" + paidStatus +
                ", Total order cost=" + totalOrderCost() + " $ "+
                '}';
    }
}
