package com.example.gestiontaches.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "CONFIRMATION_TOKEN")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    private Instant creation;

    private Instant activation;

    @OneToOne
    private Employee employee;




    public ConfirmationToken() {
    }

    public ConfirmationToken(int id, String value, Instant creation, Instant activation, Employee employee) {
        this.id = id;
        this.value = value;
        this.creation = creation;
        this.activation = activation;
        this.employee = employee;
    }

    public Instant getActivation() {
        return activation;
    }

    public void setActivation(Instant activation) {
        this.activation = activation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getCreation() {
        return creation;
    }

    public void setCreation(Instant creation) {
        this.creation = creation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
