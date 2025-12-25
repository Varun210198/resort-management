package com.example.vvs.InventoryService.Controllers;

import com.example.vvs.InventoryService.DTO.LocationRequest;
import com.example.vvs.InventoryService.Models.Location;
import com.example.vvs.InventoryService.Services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    // Create Location
    @PostMapping
    public ResponseEntity<Location> create(@RequestBody LocationRequest request) {
        Location created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Location> get(@PathVariable Long id) {
        Location location = service.get(id);
        return ResponseEntity.ok(location);   // 200 OK
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        List<Location> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@PathVariable Long id, @RequestBody LocationRequest request) {
        Location updated = service.update(id, request);
        return ResponseEntity.ok(updated);    // 200 OK
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Location deleted successfully");  // 204 No Content
    }
}

