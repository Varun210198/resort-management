package com.example.vvs.InventoryService.Controllers;

import com.example.vvs.InventoryService.DTO.ResortRequest;
import com.example.vvs.InventoryService.Models.Resort;
import com.example.vvs.InventoryService.Services.ResortService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/resorts")
@RequiredArgsConstructor
public class ResortController {

    private final ResortService service;

    // CREATE
    @PostMapping
    public ResponseEntity<Resort> create(@Valid @RequestBody ResortRequest request) {
        Resort created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Resort> get(@PathVariable Long id) {
        Resort resort = service.get(id);
        return ResponseEntity.ok(resort); // 200 OK
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Resort>> getAll() {
        List<Resort> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Resort> update(@PathVariable Long id, @Valid @RequestBody ResortRequest request) {
        Resort updated = service.update(id, request);
        return ResponseEntity.ok(updated); // 200 OK
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Resort deleted successfully");
    }
}
