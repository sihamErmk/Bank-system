package com.miniproject.Banking_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id_Client;
    private String nom;
    private  String prenom;
    private Date birthday;
    private  String Email;
    private Long id_Employe;





}
