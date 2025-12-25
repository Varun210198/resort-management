package com.example.vvs.InventoryService.Services;


import com.example.vvs.InventoryService.DTO.AccommodationTypeRequest;
import com.example.vvs.InventoryService.Exceptions.AccommodationTypeAlreadyExistsException;
import com.example.vvs.InventoryService.Exceptions.AccommodationTypeNotFoundException;
import com.example.vvs.InventoryService.Exceptions.ResortNotFoundException;
import com.example.vvs.InventoryService.Models.*;
import com.example.vvs.InventoryService.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationTypeService {

    private final AccommodationTypeRepository accommodationTypeRepository;
    private final ResortRepository resortRepository;

    public AccommodationType create(AccommodationTypeRequest request) {

        if (accommodationTypeRepository.existsByName(request.getName())) {
            throw new AccommodationTypeAlreadyExistsException(
                    "AccommodationType '" + request.getName() + "' already exists");
        }

        if (accommodationTypeRepository.existsByCode(request.getCode())) {
            throw new AccommodationTypeAlreadyExistsException(
                    "Code '" + request.getCode() + "' already exists");
        }

        Resort resort = resortRepository.findById(request.getResortId())
                .orElseThrow(() ->
                        new ResortNotFoundException(
                                "Resort with id " + request.getResortId() + " not found"));

        AccommodationType type = new AccommodationType();
        type.setName(request.getName());
        type.setCode(request.getCode());
        type.setResort(resort);
        type.setType(AccoType.valueOf(request.getType()));
        type.setCapacity(request.getCapacity());
        type.setDescription(request.getDescription());

        return accommodationTypeRepository.save(type);
    }

    public AccommodationType get(Long id) {
        return accommodationTypeRepository.findById(id)
                .orElseThrow(() ->
                        new AccommodationTypeNotFoundException(
                                "AccommodationType with id " + id + " not found"));
    }

    public List<AccommodationType> getAll() {
        return accommodationTypeRepository.findAll();
    }

    public AccommodationType update(Long id, AccommodationTypeRequest request) {

        AccommodationType type = get(id);

        // Duplicate name check
        if (!type.getName().equals(request.getName())
                && accommodationTypeRepository.existsByName(request.getName())) {

            throw new AccommodationTypeAlreadyExistsException(
                    "Name '" + request.getName() + "' already exists");
        }

        // Duplicate code check
        if (!type.getCode().equals(request.getCode())
                && accommodationTypeRepository.existsByCode(request.getCode())) {

            throw new AccommodationTypeAlreadyExistsException(
                    "Code '" + request.getCode() + "' already exists");
        }

        Resort resort = resortRepository.findById(request.getResortId())
                .orElseThrow(() ->
                        new ResortNotFoundException(
                                "Resort with id " + request.getResortId() + " not found"));

        type.setName(request.getName());
        type.setCode(request.getCode());
        type.setResort(resort);
        type.setType(AccoType.valueOf(request.getType()));
        type.setCapacity(request.getCapacity());
        type.setDescription(request.getDescription());

        return accommodationTypeRepository.save(type);
    }

    public void delete(Long id) {
        AccommodationType type = get(id);
        accommodationTypeRepository.delete(type);
    }
}
