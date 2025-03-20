package org.task.sensorstatsservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.task.sensorstatsservice.dto.SensorResponseDto;
import org.task.sensorstatsservice.entity.AuthRequest;

import java.util.List;
import java.util.Map;

@FeignClient(name = "monitor-sensors", url = "${monitor.sensors.api.url}")
public interface MonitorSensorsClient {

    @GetMapping("/sensors")
    List<SensorResponseDto> getAllSensors(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping(value = "/auth/login")
    ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest);
}
