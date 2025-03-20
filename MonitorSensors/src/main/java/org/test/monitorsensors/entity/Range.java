package org.test.monitorsensors.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Range {
    private Integer rangeFrom;
    private Integer rangeTo;
}
