package org.test.monitorsensors.util.exception;

import lombok.Builder;

@Builder
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException() {
        super("Data not found");
    }
}
