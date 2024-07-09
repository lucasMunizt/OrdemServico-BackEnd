package com.example.testos.dto;
import java.time.LocalDateTime;
public record GuaranteeDto(
        Long id,
        Double value,
        String parts,
        String product,
        String name,
        LocalDateTime date,
        int os) {}
