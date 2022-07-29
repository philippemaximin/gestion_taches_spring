package com.example.gestiontaches.entity;

import com.example.gestiontaches.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private UserRole label;

    public Role() {
    }

    public Role(int id, UserRole label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getLabel() {
        return label;
    }

    public void setLabel(UserRole label) {
        this.label = label;
    }
}