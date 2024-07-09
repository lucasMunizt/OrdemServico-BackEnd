package com.example.testos.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ClientOrderDTO(
        Long clientId,
        String clientName,
        Long clientTelephone,
        String clientAddress,
        String clientNeighborhood,
       List<OrderofServiceDto>orderofServices
        ) {
}

