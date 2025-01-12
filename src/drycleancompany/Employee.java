package drycleancompany;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * This class extends the User class and includes additional properties such as nationality,
 * start date, work permit end date, and health inspection records, constructors, getters, setters.
 */
public class Employee extends User implements Serializable {
    private static final long serialVersionUID = 1L; // constant serializable id

    private Date startDate;
    private String nationality;
    private transient HashMap<Date, String> healthInspection; // I don't want to do serialize so I added transient
    private Date workPermitEndDate;

    /**
     * Constructor for creating an Employee object, and it calls User's related constructor with super keyword.
     *
     * @param ID  ID of the employee
     * @param name  name
     * @param surname  surname
     * @param nationality  nationality
     * @param healthInspection  health inspection records
     * @param startDate  date the employee started working
     * @param workPermitEndDate  date the employee's work permit ends
     */
    public Employee(int ID, String name, String surname, String nationality, HashMap<Date, String> healthInspection, Date startDate, Date workPermitEndDate) {
        super(ID, name, surname);
        this.nationality = nationality;
        this.healthInspection = healthInspection;
        this.startDate = startDate;
        this.workPermitEndDate = workPermitEndDate;
    }

    public Employee(String nationality, Date startDate, Date workPermitEndDate) {
        this.nationality = nationality;
        this.startDate = startDate;
        this.workPermitEndDate = workPermitEndDate;
    }

    /**
     * Constructor for creating an Employee object, and it calls User's related constructor with super keyword.
     * Initializes health inspection records to a new empty HashMap.
     *
     * @param ID  ID of the employee
     * @param name  name
     * @param surname  surname
     * @param nationality  nationality
     * @param startDate  date the employee started working
     * @param workPermitEndDate  date the employee's work permit ends
     */
    public Employee(Date dateOfBirth, int ID, String name, String surname, String nationality, Date startDate, Date workPermitEndDate) {
        super(dateOfBirth, ID, name, surname);
        this.nationality = nationality;
        this.startDate = startDate;
        this.workPermitEndDate = workPermitEndDate;
        this.healthInspection = new HashMap<>();
    }

    /**
     * Constructor for creating an Employee object, and it calls User's related constructor with super keyword.
     * including health inspection records.
     *
     * @param ID  ID of the employee
     * @param name  name
     * @param surname  surname
     * @param nationality  nationality
     * @param healthInspection  health inspection records
     * @param startDate  date the employee started working
     * @param workPermitEndDate  date the employee's work permit ends
     */
    public Employee(Date dateOfBirth, int ID, String name, String surname, String nationality, HashMap<Date, String> healthInspection, Date startDate, Date workPermitEndDate) {
        super(dateOfBirth, ID, name, surname);
        this.nationality = nationality;
        this.healthInspection = healthInspection;
        this.startDate = startDate;
        this.workPermitEndDate = workPermitEndDate;
    }

    /**
     * Gets the health inspection records of the employee.
     *
     * @return hashMap where the keys are the inspection dates and the values are the corresponding health inspection details
     */
    public HashMap<Date, String> getHealthInspection() {
        return healthInspection;
    }

    /**
     * Sets the health inspection records of the employee.
     *
     * @param healthInspection A HashMap containing the health inspection records for the employee
     */
    public void setHealthInspection(HashMap<Date, String> healthInspection) {
        this.healthInspection = healthInspection;
    }

    public int getEmployeeID() {
        return this.getID();
    }

    /**
     * Gets the nationality of the employee.
     *
     * @return  nationality of the employee.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality of the employee.
     *
     * @param nationality  nationality to be set for the employee.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the start date of the employee.
     *
     * @return start date of the employee.
     */
    public java.sql.Date getStartDate() {
        return new java.sql.Date(startDate.getTime());
    }

    /**
     * Sets the start date of the employee.
     *
     * @param startDate  start date to be set for the employee.
     */
    public void setStartDate(Date startDate) {
        this.startDate = new java.util.Date(startDate.getTime());
    }

    /**
     * Gets the work permit end date for the employee.
     *
     * @return work permit end date of the employee.
     */
    public java.sql.Date getWorkPermitEndDate() {
        return new java.sql.Date(workPermitEndDate.getTime());
    }

    /**
     * Sets the work permit end date for the employee.
     *
     * @param workPermitEndDate  work permit end date to be set for the employee.
     */
    public void setWorkPermitEndDate(Date workPermitEndDate) {
        this.workPermitEndDate = new java.util.Date(workPermitEndDate.getTime());
    }

    /**
     * Provides a string representation of the employee details.
     *
     * @return A string representing the health inspection, start date, nationality, work permit end date.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "healthInspection=" + healthInspection +
                ", startDate=" + startDate +
                ", nationality='" + nationality + '\'' +
                ", workPermitEndDate=" + workPermitEndDate +
                '}';
    }

}
