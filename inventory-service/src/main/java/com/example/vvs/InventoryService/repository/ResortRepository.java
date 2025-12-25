package com.example.vvs.InventoryService.Repositories;

import com.example.vvs.InventoryService.Models.Resort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResortRepository extends JpaRepository<Resort, Long> {
    boolean existsByName(String name);
}
