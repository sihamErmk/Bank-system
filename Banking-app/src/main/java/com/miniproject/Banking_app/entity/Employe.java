package com.miniproject.Banking_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_EMPLOYES")
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Employe; // Code unique

    private String nom;

    // Relation ManyToOne avec un employé supérieur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superieur_id")
    @JsonManagedReference
    @JsonIgnore
    private Employe superieur; // Référence à l'employé supérieur

    // Relation Many-to-Many avec les groupes
    @ManyToMany(mappedBy = "employes")
    @JsonBackReference
    private Set<Groupe> groupes = new HashSet<>();
    // Only the Set definition is needed here

    // Getters et Setters
    public Long getId_Employe() {
        return id_Employe;
    }

    public void setId_Employe(Long id_Employe) {
        this.id_Employe = id_Employe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    public Employe getSuperieur() {
        return superieur;
    }

    public void setSuperieur(Employe superieur) {
        this.superieur = superieur;
    }
}
