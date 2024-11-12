package com.miniproject.Banking_app.dto;


import lombok.Data;

@Data
public class EmployeDto {

    private Long id_Employe;
    private String nom;


    public String getNom() {
        return nom;
    }

    // Setter pour 'nom'
    public void setNom(String nom) {
        this.nom = nom;
    }
}

