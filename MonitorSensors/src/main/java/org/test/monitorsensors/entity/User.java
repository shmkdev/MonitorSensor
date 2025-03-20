package org.test.monitorsensors.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;
}
