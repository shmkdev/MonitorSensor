package org.test.monitorsensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.monitorsensors.entity.Sensor;

import java.util.List;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    List<Sensor> findByNameContainingAndModelContaining(String name, String model);
}
