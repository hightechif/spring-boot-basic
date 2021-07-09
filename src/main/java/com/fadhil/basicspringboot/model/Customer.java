package com.fadhil.basicspringboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model is where to connect our apps with Database
 * and where we define our data scheme.
 * */

@Entity   // Persist Customer class to be an Entity
public class Customer {
    @Id                                                  // We use @Id so we can use id as ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Used so id be auto-increment. IDENTITY for MySQL. SEQUENCE for PostgreSQL.
    private long id;                                     // Access Modifiers: private.
    private String name;
    private String email;

    public Customer() {                              // How to create default constructor.
    }

    public Customer(String name, String email) {     // How to create custom constructor.
        this.name = name;
        this.email = email;
    }

    public long getId() {                                // Access Modifiers: public.
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {               // Getter function.
        return name;
    }

    public void setName(String name) {      // Setter function.
        this.name = name;
    }

    public String getEmail() {              // String means return a string.
        return email;
    }

    public void setEmail(String email) {    // void means return nothing.
        this.email = email;
    }
}


