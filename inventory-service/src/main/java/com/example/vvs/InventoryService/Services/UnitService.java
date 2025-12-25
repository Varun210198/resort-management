package com.example.vvs.InventoryService.Services;


import com.example.vvs.InventoryService.DTO.UnitRequest;
import com.example.vvs.InventoryService.Exceptions.AccommodationTypeNotFoundException;
import com.example.vvs.InventoryService.Exceptions.UnitAlreadyExistsException;
import com.example.vvs.InventoryService.Exceptions.UnitNotFoundException;
import com.example.vvs.InventoryService.Models.AccommodationType;
import com.example.vvs.InventoryService.Models.Unit;
import com.example.vvs.InventoryService.Repositories.AccommodationTypeRepository;
import com.example.vvs.InventoryService.Repositories.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final AccommodationTypeRepository accommodationTypeRepository;

    public Unit create(UnitRequest request) throws UnitAlreadyExistsException {
        if(unitRepository.existsByUnitNumber(request.getUnitNumber())){
            throw new UnitAlreadyExistsException("Unit wit number "+request.getUnitNumber()+" already exists");
        }

        AccommodationType type = accommodationTypeRepository.findById(request.getAccommodationTypeId())
                .orElseThrow(() ->
                        new AccommodationTypeNotFoundException(
                                "AccommodationType with id " + request.getAccommodationTypeId() + " not found"));

        Unit unit = new Unit();
        unit.setAccommodationType(type);
        unit.setUnitNumber(request.getUnitNumber());
        unit.setIsActive(request.getIsActive());

        return unitRepository.save(unit);
    }

    public Unit get(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() ->
                        new UnitNotFoundException("Unit with id " + id + " not found"));
    }

    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    public Unit update(Long id, UnitRequest request) {

        Unit unit = get(id);

        AccommodationType type = accommodationTypeRepository.findById(request.getAccommodationTypeId())
                .orElseThrow(() ->
                        new AccommodationTypeNotFoundException(
                                "AccommodationType with id " + request.getAccommodationTypeId() + " not found"));

        unit.setAccommodationType(type);
        unit.setUnitNumber(request.getUnitNumber());
        unit.setIsActive(request.getIsActive());

        return unitRepository.save(unit);
    }

    public void delete(Long id) {
        Unit unit = get(id);
        unitRepository.delete(unit);
    }

    public List<Long> getInactiveUnits(List<Long> unitIds){

        return unitRepository.getInactiveUnitsFromList(unitIds);
    }

}
