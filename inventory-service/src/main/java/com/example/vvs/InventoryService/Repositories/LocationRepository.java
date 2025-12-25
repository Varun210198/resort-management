package com.example.vvs.InventoryService.Repositories;

import com.example.vvs.InventoryService.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
    boolean existsByName(String name);

}
