package com.example.vvs.InventoryService.Repositories;

import com.example.vvs.InventoryService.Models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    public boolean existsByUnitNumber(String unitNumber);

    @Query("""
    select u.id
    from Unit u
    where u.id in :units
      and u.isActive = false
""")
    List<Long> getInactiveUnitsFromList(@Param("units") List<Long> units);

}
