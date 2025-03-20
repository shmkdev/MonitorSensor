package org.test.monitorsensors.util.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthError {
    private int status;
    private String message;
}
