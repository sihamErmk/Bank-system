package com.miniproject.Banking_app.web;

import com.miniproject.Banking_app.dto.ClientDto;
import com.miniproject.Banking_app.entity.Client;
import com.miniproject.Banking_app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientRestController {

    private final ClientService clientService;

    @Autowired
     ClientRestController(ClientService clientService) {

         this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody ClientDto clientDto) {
        Client createdClient = clientService.createNewClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @GetMapping("/clients")
    public List<Client> findAll() {
        return this.clientService.findAll();
    }



//private final ClientService clientService;
//
//    @Autowired
//    public ClientRestController(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
//    @PostMapping("/add/{employeId}")
//    public ResponseEntity<Client> ajouterClient(@PathVariable Long employeId, @RequestBody Client client) {
//        Client nouveauClient = clientService.ajouterClientParEmploye(employeId, client);
//        return new ResponseEntity<>(nouveauClient, HttpStatus.CREATED);
//}

}

