package com.example.gestiontaches.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    //temps initial
    private int it;

    //temps restant
    private int rt;

    public Task() {
    }

    public Task(Long id, String title, String description, int it, int rt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.it = it;
        this.rt = rt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIt() {
        return it;
    }

    public void setIt(int it) {
        this.it = it;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }
}
