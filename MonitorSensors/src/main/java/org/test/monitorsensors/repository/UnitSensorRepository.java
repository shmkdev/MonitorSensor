package org.test.monitorsensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.monitorsensors.entity.UnitSensor;

import java.util.Optional;
import java.util.UUID;

public interface UnitSensorRepository extends JpaRepository<UnitSensor, UUID> {
    Optional<UnitSensor> findByName(String name);
}
