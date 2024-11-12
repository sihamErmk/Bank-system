package com.miniproject.Banking_app.service;


import com.miniproject.Banking_app.dto.ClientDto;
import com.miniproject.Banking_app.entity.Client;


import java.util.List;



public interface ClientService {
    Client createNewClient(ClientDto clientDto);  // Utilise ClientDto
    List<Client> findAll();
}

