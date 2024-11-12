package com.miniproject.Banking_app.entity;

import com.miniproject.Banking_app.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_OPERATIONS")
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long numOperation;
    @Column(nullable = false)
    private Date dateOperation;
    @Column(nullable = false)
    private  double montant;
    @Column(nullable = false)
    private TypeOperation typeOperation;
    @ManyToOne
    private Account account;

}
