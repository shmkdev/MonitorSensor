package org.test.monitorsensors.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.monitorsensors.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Role role;
}
