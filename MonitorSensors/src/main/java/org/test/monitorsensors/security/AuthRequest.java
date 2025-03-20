package org.test.monitorsensors.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
}
