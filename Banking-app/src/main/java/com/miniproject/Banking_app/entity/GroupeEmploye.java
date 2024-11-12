package com.miniproject.Banking_app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "groupe_employe")
public class GroupeEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;



    // Constructeurs, getters et setters
    public GroupeEmploye() {}

    public GroupeEmploye(Groupe groupe, Employe employe, LocalDate dateAjout) {
        this.groupe = groupe;
        this.employe = employe;

    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }


}
