package com.example.testos.repository;

import com.example.testos.dto.ClientOrderDTO;
import com.example.testos.entity.OrderofService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderofServiceRepository extends JpaRepository<OrderofService, Long> {
//    @Query("SELECT c, o FROM Client c INNER JOIN c.orderofServices o")
//    List<Object[]> findClientsWithOrderofServices();
}
