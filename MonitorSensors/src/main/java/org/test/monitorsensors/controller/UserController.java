package org.test.monitorsensors.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.test.monitorsensors.dto.UserRequestDto;
import org.test.monitorsensors.dto.UserResponseDto;
import org.test.monitorsensors.service.UserService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Find all users")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find user by id user")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/registration")
    @Operation(summary = "Create user")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.create(userRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<UserResponseDto> update(@PathVariable("id") UUID id,
                                                  @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.update(id, userRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
