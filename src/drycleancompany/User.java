package drycleancompany;

import java.io.Serializable;
import java.util.Date;

/**
 * This is an abstract class representing a user in the cleaning system.
 * It serves as a base class for different types of users (such as customers and employees),
 * containing specifics, constructors, getter and setters.
 */
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;
    private Date dateOfBirth;
    private String name;
    private String surname;

    /**
     * Constructor to create a User with specified date of birth, ID, name, and surname.
     *
     * @param dateOfBirth date of birth
     * @param ID          ID of the user.
     * @param name        name
     * @param surname     surname
     */
    public User(Date dateOfBirth, int ID, String name, String surname) {
        this.dateOfBirth = dateOfBirth;
        this.ID = ID;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Constructor to create a User with specified ID, name, and surname.
     * The date of birth is set to the current date by default.
     *
     * @param ID          ID of the user.
     * @param name        name
     * @param surname     surname
     */
    public User(int ID, String name, String surname) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = new Date();
    }

    /**
     * Default Constructor
     */
    public User() {
        this.ID = 0;
        this.name = null;
        this.surname = null;
        this.dateOfBirth = new Date();
    }

    /**
     * Returns the surname of the user.
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the user.
     *
     * @param surname surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the name of the user.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the id of the user.
     *
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the id of the user.
     *
     * @param ID id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Returns the dob of the user.
     *
     * @return date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dateOfBirth date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
