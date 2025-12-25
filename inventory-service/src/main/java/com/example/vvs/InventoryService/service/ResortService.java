package com.example.vvs.InventoryService.Services;

import com.example.vvs.InventoryService.DTO.ResortRequest;
import com.example.vvs.InventoryService.Exceptions.LocationNotFoundException;
import com.example.vvs.InventoryService.Exceptions.ResortAlreadyExistsException;
import com.example.vvs.InventoryService.Exceptions.ResortNotFoundException;
import com.example.vvs.InventoryService.Models.Location;
import com.example.vvs.InventoryService.Models.Resort;
import com.example.vvs.InventoryService.Repositories.LocationRepository;
import com.example.vvs.InventoryService.Repositories.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResortService {

    private final ResortRepository resortRepository;
    private final LocationRepository locationRepository;

    public Resort create(ResortRequest request) {

        if (resortRepository.existsByName(request.getName())) {
            throw new ResortAlreadyExistsException(
                    "Resort '" + request.getName() + "' already exists"
            );
        }

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() ->
                        new LocationNotFoundException(
                                "Location with id " + request.getLocationId() + " not found"));

        Resort resort = new Resort();
        resort.setName(request.getName());
        resort.setLocation(location);

        return resortRepository.save(resort);
    }

    public Resort get(Long id) {
        return resortRepository.findById(id)
                .orElseThrow(() ->
                        new ResortNotFoundException("Resort with id " + id + " not found"));
    }

    public List<Resort> getAll() {
        return resortRepository.findAll();
    }

    public Resort update(Long id, ResortRequest request) {

        Resort resort = resortRepository.findById(id)
                .orElseThrow(() ->
                        new ResortNotFoundException("Resort with id " + id + " not found"));

        // Duplicate name check
        if (!resort.getName().equals(request.getName())
                && resortRepository.existsByName(request.getName())) {

            throw new ResortAlreadyExistsException(
                    "Resort '" + request.getName() + "' already exists");
        }

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() ->
                        new LocationNotFoundException(
                                "Location with id " + request.getLocationId() + " not found"));

        resort.setName(request.getName());
        resort.setLocation(location);

        return resortRepository.save(resort);
    }

    public void delete(Long id) {
        Resort resort = get(id);
        resortRepository.delete(resort);
    }
}
