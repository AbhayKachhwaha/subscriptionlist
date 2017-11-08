
package com.subscriptionlist.business;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Subscription implements Serializable {
    // Add class variables for each column of the Subscription table
    
    private String email;
    private String firstName;
    private String lastName;
    private boolean subscribed;
    private LocalDateTime lastUpdated;
    
    //All JavaBeans require a no-argument constructor
    public Subscription() {
        email = "";
        firstName="";
        lastName="";
        subscribed = false;
        lastUpdated= LocalDateTime.now();
    }
    //Create getter/setter method--> right click>refractor>encapsulate

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the subscribed
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * @return the lastUpdated
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    
    }

