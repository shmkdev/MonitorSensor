package org.test.monitorsensors.dto;

import lombok.Data;
import lombok.Value;
import org.test.monitorsensors.entity.Role;

import java.util.UUID;

@Value
@Data
public class UserResponseDto {
    UUID id;
    String username;
    String firstname;
    String lastname;
    Role role;
}
