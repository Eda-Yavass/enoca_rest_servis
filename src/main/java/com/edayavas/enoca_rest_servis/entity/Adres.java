package com.edayavas.enoca_rest_servis.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "adres")
public class Adres implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String il;

    private String ilce;

    private String mahalle;

    private String sokak;

    private String evNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getSokak() {
        return sokak;
    }

    public void setSokak(String sokak) {
        this.sokak = sokak;
    }

    public String getEvNo() {
        return evNo;
    }

    public void setEvNo(String evNo) {
        this.evNo = evNo;
    }
}
