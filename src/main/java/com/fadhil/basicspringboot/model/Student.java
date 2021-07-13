package com.fadhil.basicspringboot.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * Student Model
 * A Class Object as a Database Model for Student Object
 * */

@Entity // Entity annotation to persist Student object as an Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private LocalDate dob;   // date of birth
    @Transient
    private Integer age;

    // Empty Constructor
    public Student() {
    }

    // Constructor with Attributes
    public Student(String first_name, String last_name, LocalDate dob) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
