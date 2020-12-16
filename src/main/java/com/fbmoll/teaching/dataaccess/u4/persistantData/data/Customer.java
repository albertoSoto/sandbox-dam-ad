package com.fbmoll.teaching.dataaccess.u4.persistantData.data;

import javax.persistence.*;
import java.util.Date;

/**
 * com.fbmoll.teaching.dataaccess.persistantData
 * Class Customer
 * 15/12/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
//@Entity(name = "customers")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "superfirst_name")
    private String firstName;

    private String lastName;

    private Date creationDate;

    @PrePersist
    public void onPrePersist() {
        creationDate = new Date();
    }

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
