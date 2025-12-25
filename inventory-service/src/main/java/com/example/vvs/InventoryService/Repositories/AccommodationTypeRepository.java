package com.example.vvs.InventoryService.Repositories;

import com.example.vvs.InventoryService.Models.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
}


