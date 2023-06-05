package org.switch2022.project.ddd.exceptions;

import java.time.LocalTime;
import java.util.Date;

public class ErrorMessage {
    private final int statusCode;
    private final LocalTime timestamp;
    private final String message;

    public ErrorMessage(int statusCode, LocalTime timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
