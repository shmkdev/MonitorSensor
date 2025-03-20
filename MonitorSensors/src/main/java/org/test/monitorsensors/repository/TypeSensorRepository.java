package org.test.monitorsensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.monitorsensors.entity.TypeSensor;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public interface TypeSensorRepository extends JpaRepository<TypeSensor, UUID> {
    Optional<TypeSensor> findByName(String name);
}
