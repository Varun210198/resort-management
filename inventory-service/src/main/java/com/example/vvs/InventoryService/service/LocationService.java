package com.example.vvs.InventoryService.Services;

import com.example.vvs.InventoryService.DTO.LocationRequest;
import com.example.vvs.InventoryService.Exceptions.LocationAlreadyExistsException;
import com.example.vvs.InventoryService.Exceptions.LocationNotFoundException;
import com.example.vvs.InventoryService.Models.Location;
import com.example.vvs.InventoryService.Repositories.LocationRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location create(LocationRequest request) {

        if (locationRepository.existsByName(request.getName())) {
            throw new LocationAlreadyExistsException(
                    "Location '" + request.getName() + "' already exists"
            );
        }

        Location location = new Location();
        location.setName(request.getName());

        return locationRepository.save(location);
    }

    public Location get(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() ->
                        new LocationNotFoundException("Location with id " + id + " not found"));
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location update(Long id, LocationRequest request) {

        Location location = locationRepository.findById(id)
                .orElseThrow(() ->
                        new LocationNotFoundException("Location with id " + id + " not found"));

        // Check duplicate name
        if (!location.getName().equals(request.getName())
                && locationRepository.existsByName(request.getName())) {
            throw new LocationAlreadyExistsException(
                    "Location '" + request.getName() + "' already exists"
            );
        }

        location.setName(request.getName());

        return locationRepository.save(location);
    }

    public void delete(Long id) {
        Location location = get(id);
        locationRepository.delete(location);
    }
}
