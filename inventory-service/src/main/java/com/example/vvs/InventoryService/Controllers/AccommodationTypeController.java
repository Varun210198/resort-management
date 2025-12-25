package com.example.vvs.InventoryService.Controllers;

import com.example.vvs.InventoryService.DTO.AccommodationTypeRequest;
import com.example.vvs.InventoryService.Models.AccommodationType;
import com.example.vvs.InventoryService.Services.AccommodationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/accommodation-types")
@RequiredArgsConstructor
public class AccommodationTypeController {

    private final AccommodationTypeService service;

    // CREATE
    @PostMapping
    public ResponseEntity<AccommodationType> create(@RequestBody AccommodationTypeRequest request) {
        AccommodationType created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationType> get(@PathVariable Long id) {
        AccommodationType type = service.get(id);
        return ResponseEntity.ok(type);  // 200 OK
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<AccommodationType>> getAll() {
        List<AccommodationType> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AccommodationType> update(@PathVariable Long id, @RequestBody AccommodationTypeRequest request) {
        AccommodationType updated = service.update(id, request);
        return ResponseEntity.ok(updated); // 200 OK
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("AccommodationType deleted successfully");
    }
}
