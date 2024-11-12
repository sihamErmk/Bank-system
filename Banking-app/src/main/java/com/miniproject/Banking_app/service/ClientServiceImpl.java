package com.miniproject.Banking_app.service;

import com.miniproject.Banking_app.dto.ClientDto;
import com.miniproject.Banking_app.entity.Client;
import com.miniproject.Banking_app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createNewClient(ClientDto clientDto) {
        // Convertir ClientDto en Client
        Client client = new Client();
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setBirthday(clientDto.getBirthday());
        client.setEmail(clientDto.getEmail());
        // Sauvegarder le client dans la base de données et retourner l'objet créé
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll(); // Retourner la liste de tous les clients
    }
}
