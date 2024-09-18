package com.esprit.microservice.candidat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Candidat implements Serializable {
    private static final long serialVersionUID = 6;

@Id
@GeneratedValue
    private int id;
    private String nom, prenom, email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Candidat() {
    }

    public Candidat(String nom,String prenom, String email ) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
}
