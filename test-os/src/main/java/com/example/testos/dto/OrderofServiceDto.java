package com.example.testos.dto;

import com.example.testos.entity.Client;

import java.util.Date;
import java.time.LocalDateTime;
public record OrderofServiceDto(
        Long id,
        String device,
        String claims,
        String observation,
        Double value,
        LocalDateTime date
) {}
