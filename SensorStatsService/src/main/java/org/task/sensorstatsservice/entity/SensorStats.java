package org.task.sensorstatsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class SensorStats {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime statDate;
    private Integer totalSensors;
    private String type;
    private Integer countByType;
}
