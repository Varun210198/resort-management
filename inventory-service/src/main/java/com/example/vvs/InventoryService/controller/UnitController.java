package com.example.vvs.InventoryService.Controllers;

import com.example.vvs.InventoryService.DTO.UnitRequest;
import com.example.vvs.InventoryService.Exceptions.UnitAlreadyExistsException;
import com.example.vvs.InventoryService.Models.Unit;
import com.example.vvs.InventoryService.Services.UnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService service;

    // CREATE
    @PostMapping
    public ResponseEntity<Unit> create(@Valid @RequestBody UnitRequest request) throws UnitAlreadyExistsException {
        Unit created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Unit> get(@PathVariable Long id) {
        Unit unit = service.get(id);
        return ResponseEntity.ok(unit);  // 200 OK
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Unit>> getAll() {
        List<Unit> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Unit> update(@PathVariable Long id, @Valid @RequestBody UnitRequest request) {
        Unit updated = service.update(id, request);
        return ResponseEntity.ok(updated);  // 200 OK
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Unit deleted successfully");
    }


    // for getting inactive units in a batch call
    @PostMapping("/active-check")
    public List<Long> getInactiveUnits(@RequestBody List<Long> unitIds) {
        return service.getInactiveUnits(unitIds);
    }


}
