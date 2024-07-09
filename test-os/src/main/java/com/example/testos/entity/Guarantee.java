package com.example.testos.entity;

import com.example.testos.dto.GuaranteeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guarantees")
public class Guarantee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int os;
    private String name;
    private Double value;
    private String parts;
    private String product;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "orderofservice_client")
    private OrderofService orderofService;

    public Guarantee(GuaranteeDto guaranteeDto) {
        this.id = guaranteeDto.id();
        this.value = guaranteeDto.value();
        this.parts = guaranteeDto.parts();
        this.product = guaranteeDto.product();
        this.name = guaranteeDto.name();
        this.date = guaranteeDto.date();
        this.os = guaranteeDto.os();
    }
}