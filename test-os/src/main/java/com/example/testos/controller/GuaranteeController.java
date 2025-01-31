package com.example.testos.controller;


import com.example.testos.dto.GuaranteeDto;
import com.example.testos.entity.Guarantee;
import com.example.testos.repository.GuaranteeRepository;
import com.example.testos.services.Servicess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GuaranteeController {
    @Autowired
    private GuaranteeRepository repository;

    @Autowired
    private Servicess servicess;

    @GetMapping(path = "/garantia")
    public ResponseEntity<List<Guarantee>> findAll(){
        ResponseEntity<List<Guarantee>> response = null;
        List<Guarantee> guarantees = repository.findAll();
        if( guarantees == null || guarantees.isEmpty() ){
            response =ResponseEntity.noContent().build();
        }else {
            response = ResponseEntity.ok(guarantees);
        }
        return response;
    }
    @PostMapping(path = "/garantia")
    public void salvar(@RequestBody GuaranteeDto guaranteeDto){
        repository.save(new Guarantee(guaranteeDto));
    }

    @GetMapping(path = "/garantia/{id}")
    public Optional<Guarantee> FindServicesById(@PathVariable(name = "id")Long id){
        Optional<Guarantee> guarantee = repository.findById(id);
        return guarantee;
    }
//    @DeleteMapping(path = "/garantia/{id}")
//    public void deletar(@PathVariable Long id){
//        servicess.deletar(id);
//    }

}
