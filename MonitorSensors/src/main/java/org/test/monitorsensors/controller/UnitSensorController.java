package org.test.monitorsensors.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.monitorsensors.dto.UnitRequestDto;
import org.test.monitorsensors.dto.UnitResponseDto;
import org.test.monitorsensors.service.UnitSensorService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/units")
@Tag(name = "Unit", description = "Unit API")
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UnitSensorController {
    private final UnitSensorService unitService;

    @GetMapping
    @Operation(summary = "Find all units")
    public ResponseEntity<List<UnitResponseDto>> findAll() {
        return ResponseEntity.ok(unitService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find unit by id unit")
    public ResponseEntity<UnitResponseDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(unitService.findById(id));
    }

    @GetMapping("/unit/{name}")
    @Operation(summary = "Find unit by name unit")
    public ResponseEntity<UnitResponseDto> findById(@PathVariable("name") String name) {
        return ResponseEntity.ok(unitService.findByName(name));
    }

    @PostMapping
    @Operation(summary = "Create unit")
    public ResponseEntity<UnitResponseDto> save(@RequestBody UnitRequestDto unitRequestDto) {
        return ResponseEntity.ok(unitService.create(unitRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update unit")
    public ResponseEntity<UnitResponseDto> update(@PathVariable("id") UUID id,
                                                  @RequestBody UnitRequestDto unitRequestDto) {
        return ResponseEntity.ok(unitService.update(id, unitRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete unit")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        unitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
