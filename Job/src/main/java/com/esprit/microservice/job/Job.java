package com.esprit.microservice.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Job implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue
    private int id;
    private String service;
    private Boolean etat; // true if available, false if not

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Job() {
    }

    public Job(String service, Boolean etat) {
        this.service = service;
        this.etat = etat;
    }


}
