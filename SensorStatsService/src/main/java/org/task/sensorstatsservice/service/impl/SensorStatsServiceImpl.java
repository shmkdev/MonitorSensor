package org.task.sensorstatsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.task.sensorstatsservice.dto.SensorResponseDto;
import org.task.sensorstatsservice.dto.SensorStatsDto;
import org.task.sensorstatsservice.entity.AuthRequest;
import org.task.sensorstatsservice.entity.SensorStats;
import org.task.sensorstatsservice.feign.MonitorSensorsClient;
import org.task.sensorstatsservice.mapper.SensorStatsMapper;
import org.task.sensorstatsservice.repository.SensorStatsRepository;
import org.task.sensorstatsservice.service.SensorStatsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorStatsServiceImpl implements SensorStatsService {
    @Value("${monitor.sensors.auth.username}")
    private String username;
    @Value("${monitor.sensors.auth.password}")
    private String password;

    private final MonitorSensorsClient monitorSensorsClient;
    private final SensorStatsRepository sensorStatsRepository;
    private final SensorStatsMapper sensorStatsMapper;

    @Override
    @Scheduled(cron = "0 0 2 * * ?")
    public void collectAndSaveStats() {
        String token = jwtToken();
        String authorizationHeader = "Bearer " + token;

        LocalDateTime now = LocalDateTime.now();

        List<SensorResponseDto> sensors = monitorSensorsClient.getAllSensors(authorizationHeader);
        sensorStatsRepository.deleteAll();

        int totalSensors = sensors.size();
        Map<String, Integer> typeCounts = sensors.stream()
                .collect(Collectors.groupingBy(
                        SensorResponseDto::getType,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));

        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            SensorStats stats = new SensorStats();
            stats.setStatDate(now);
            stats.setTotalSensors(totalSensors);
            stats.setType(entry.getKey());
            stats.setCountByType(entry.getValue());
            sensorStatsRepository.save(stats);
        }
    }

    @Override
    public List<SensorStatsDto> getStatsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<SensorStats> stats = sensorStatsRepository.findByStatDateBetween(startDate, endDate);
        return stats.stream()
                .map(sensorStatsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SensorStatsDto> findAll() {
        return sensorStatsRepository.findAll().stream()
                .map(sensorStatsMapper::toDto)
                .toList();
    }

    private String jwtToken() {
        ResponseEntity<Map<String, String>> response = monitorSensorsClient.login(new AuthRequest(username, password));
        if (response.getStatusCode().is2xxSuccessful()) return response.getBody().get("token");
        else throw new RuntimeException("Authentication failed: " + response.getStatusCode());
    }
}