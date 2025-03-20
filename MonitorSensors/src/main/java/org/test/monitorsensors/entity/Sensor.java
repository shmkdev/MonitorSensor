package org.test.monitorsensors.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "sensors")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String model;
    private String location;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDate;
    private Range range;
    @ManyToOne
    @ToString.Exclude
    private TypeSensor type;
    @ManyToOne
    @ToString.Exclude
    private UnitSensor unit;

    @PrePersist
    public void prePersist() {
        setLocalDate(LocalDate.now());
    }
}


