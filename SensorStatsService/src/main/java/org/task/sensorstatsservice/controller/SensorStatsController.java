package org.task.sensorstatsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.task.sensorstatsservice.dto.SensorStatsDto;
import org.task.sensorstatsservice.service.SensorStatsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "SensorStats", description = "Statistic API")
@RequiredArgsConstructor
@RequestMapping("/api/stats")
public class SensorStatsController {

    private final SensorStatsService sensorStatsService;

    @GetMapping
    @Operation(summary = "Find all statistic")
    public ResponseEntity<List<SensorStatsDto>> findAll() {
        return ResponseEntity.ok(sensorStatsService.findAll());
    }

    @GetMapping("/range")
    @Operation(summary = "Find statistic within a time range")
    public ResponseEntity<List<SensorStatsDto>> getStatsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate) {
        List<SensorStatsDto> stats = sensorStatsService.getStatsByDateRange(startDate, endDate);
        return ResponseEntity.ok(stats);
    }
}
