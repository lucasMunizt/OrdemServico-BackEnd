package com.example.testos.entity;

import com.example.testos.dto.OrderofServiceDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderofservices")
public class OrderofService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String device;
    private String claims;
    private String observation;
    private Double value;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "orderofservice_client")
    private Client client;

    public OrderofService(OrderofServiceDto orderofServiceDto) {
        this.id = orderofServiceDto.id();
        this.device = orderofServiceDto.device();
        this.claims = orderofServiceDto.claims();
        this.observation = orderofServiceDto.observation();
        this.value = orderofServiceDto.value();
        this.date = orderofServiceDto.date();

    }
}