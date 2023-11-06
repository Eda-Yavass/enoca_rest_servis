package com.edayavas.enoca_rest_servis.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "persons")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  firstName;

    private String lastName;

    private String indentityId;

    @ManyToOne
    @JoinColumn(name = "ID_ADRESS")
    private Adres adres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndentityId() {
        return indentityId;
    }

    public void setIndentityId(String indentityId) {
        this.indentityId = indentityId;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}
