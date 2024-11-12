package com.miniproject.Banking_app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_CLIENTS")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Client;

    private String prenom;
    private String nom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Collection<Account> accounts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}
