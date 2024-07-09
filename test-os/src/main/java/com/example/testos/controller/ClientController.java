package com.example.testos.controller;

import com.example.testos.dto.ClientDto;
import com.example.testos.entity.Client;
import com.example.testos.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ClientController {
    @Autowired
    ClientRepository repository;

    @GetMapping(path = "/client")
    public ResponseEntity<List<Client>> findAll(){
        ResponseEntity<List<Client>> response = null;
        List<Client> clients = repository.findAll();
        if(clients == null || clients.isEmpty() ){
            response =ResponseEntity.noContent().build();
        }else {
            response = ResponseEntity.ok(clients);
        }
        return response;
    }
    @PostMapping(path = "/client")
    public void salvar(@RequestBody ClientDto clientDto){
        repository.save(new Client(clientDto));
    }

    @GetMapping("/{clientId}/services")
    public ResponseEntity<Client> getClientWithServices(@PathVariable Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }
}
