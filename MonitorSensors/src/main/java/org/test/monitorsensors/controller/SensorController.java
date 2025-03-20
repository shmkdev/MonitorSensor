package org.test.monitorsensors.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.test.monitorsensors.dto.SensorRequestDto;
import org.test.monitorsensors.dto.SensorResponseDto;
import org.test.monitorsensors.service.SensorService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/sensors")
@Tag(name = "Sensor", description = "Sensor API")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class SensorController {
    private final SensorService sensorService;

    @GetMapping
    public List<SensorResponseDto> findAll() {
        return sensorService.findAll();
    }

    @GetMapping("/search")
    public List<SensorResponseDto> search(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String model) {
        return sensorService.findByNameContainingAndModelContaining(name, model);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find sensor by id")
    public ResponseEntity<SensorResponseDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(sensorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create sensor")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<SensorResponseDto> create(@Valid @RequestBody SensorRequestDto sensorRequestDto) {
        return ResponseEntity.ok(sensorService.create(sensorRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update sensor")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<SensorResponseDto> update(@PathVariable UUID id, @Valid @RequestBody SensorRequestDto sensorRequestDto) {
        return ResponseEntity.ok(sensorService.update(id, sensorRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sensor")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        sensorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
