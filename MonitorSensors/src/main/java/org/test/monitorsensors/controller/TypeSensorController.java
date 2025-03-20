package org.test.monitorsensors.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.test.monitorsensors.dto.TypeRequestDto;
import org.test.monitorsensors.dto.TypeResponseDto;
import org.test.monitorsensors.service.TypeSensorService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/types")
@RequiredArgsConstructor
@Tag(name = "TypeSensor", description = "TypeSensor API")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class TypeSensorController {
    private final TypeSensorService typeSensorService;

    @GetMapping
    @Operation(summary = "Find all types")
    public ResponseEntity<List<TypeResponseDto>> findAll() {
        return ResponseEntity.ok(typeSensorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find type by id type")
    public ResponseEntity<TypeResponseDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(typeSensorService.findById(id));
    }

    @GetMapping("/type/{name}")
    @Operation(summary = "Find type by id name")
    public ResponseEntity<TypeResponseDto> findById(@PathVariable("name") String name) {
        return ResponseEntity.ok(typeSensorService.findByName(name));
    }

    @PostMapping
    @Operation(summary = "Create type")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<TypeResponseDto> create(@RequestBody TypeRequestDto typeRequestDto) {
        return ResponseEntity.ok(typeSensorService.create(typeRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update type")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<TypeResponseDto> update(@PathVariable("id") UUID id,
                                                  @RequestBody TypeRequestDto typeCreateDto) {
        return ResponseEntity.ok(typeSensorService.update(id, typeCreateDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete type")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        typeSensorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
