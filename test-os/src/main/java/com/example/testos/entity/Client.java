package com.example.testos.entity;

import com.example.testos.dto.ClientDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long telephone;
    private String addres;
    private String neighboard;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<OrderofService> orderofServices = new ArrayList<>();

    public Client(ClientDto clientDto) {
        this.id = clientDto.id();
        this.name = clientDto.name();
        this.telephone = clientDto.telephone();
        this.addres = clientDto.addres();
        this.neighboard = clientDto.neighboard();

    }
}