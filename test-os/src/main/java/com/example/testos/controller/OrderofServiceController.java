package com.example.testos.controller;

import com.example.testos.dto.ClientOrderDTO;
import com.example.testos.dto.OrderofServiceDto;
import com.example.testos.entity.Client;
import com.example.testos.entity.OrderofService;
import com.example.testos.repository.ClientRepository;
import com.example.testos.repository.OrderofServiceRepository;
import com.example.testos.services.Servicess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderofServiceController {
    @Autowired
    private OrderofServiceRepository repository;
    @Autowired
    private ClientRepository clientRepository;
    private final Servicess servicess;
    @Autowired
    public OrderofServiceController(Servicess servicess) {
        this.servicess = servicess;
    }
    @GetMapping(path = "/servicos")
    public ResponseEntity<List<OrderofService>> findAll(){
        ResponseEntity<List<OrderofService>> response = null;
        List<OrderofService> orderofServices = repository.findAll();
        if(orderofServices == null || orderofServices.isEmpty() ){
            response = ResponseEntity.noContent().build();
        }else {
            response = ResponseEntity.ok(orderofServices);
        }
        return response;
    }

    @PostMapping(path = "/servicos")
    public void salvar(@RequestBody OrderofServiceDto orderofServiceDto){
        System.out.println("deu certo");
        repository.save(new OrderofService(orderofServiceDto));
    }


    @GetMapping(path = "/servicos/{id}")
    public Optional<OrderofService> FindServicesById(@PathVariable(name = "id")Long id){
        Optional<OrderofService> orderofService = repository.findById(id);
        return orderofService;
    }

    @DeleteMapping(path = "/servicos/{id}")
    public void deleteById(@PathVariable Long id){
        servicess.deleteByid(id);
    }




    @GetMapping("/clientWithOrders")
    public ResponseEntity<List<ClientOrderDTO>> getClientWithOrders() {
        List<ClientOrderDTO> clientsWithOrders = servicess.getClientsWithOrderofServices();
        if (clientsWithOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientsWithOrders);
    }
}

