package org.switch2022.project.ddd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestResponseEntityExceptionHandlerTest {

    private final RestResponseEntityExceptionHandler exceptionHandler =
            new RestResponseEntityExceptionHandler();

    @Test
    public void handleNotFoundInRepoTest() {
        // Arrange
        NotFoundInRepoException exception = new NotFoundInRepoException("Not Found in Repo");
        ErrorMessage expected = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalTime.now(), "Not " +
                "Found in Repo");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleNotFoundInRepo(exception);
        ErrorMessage resultErrorMessage = (ErrorMessage) result.getBody();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(expected.getMessage(), resultErrorMessage.getMessage());
    }

    @Test
    public void handleAlreadyExistsInRepoTest() {
        // Arrange
        AlreadyExistsInRepoException exception = new AlreadyExistsInRepoException("Already exists" +
                " " +
                "in repo");
        ErrorMessage expected = new ErrorMessage(HttpStatus.CONFLICT.value(), LocalTime.now(),
                "Already exists in repo");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleAlreadyExistsInRepo(exception);
        ErrorMessage resultErrorMessage = (ErrorMessage) result.getBody();

        // Assert
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals(expected.getMessage(), resultErrorMessage.getMessage());
    }

    @Test
    public void handleInvalidInputExceptionTest() {
        // Arrange
        InvalidInputException exception = new InvalidInputException("Invalid input");
        ErrorMessage expected = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalTime.now(),
                "Invalid input");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleInvalidInputException(exception);
        ErrorMessage resultErrorMessage = (ErrorMessage) result.getBody();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(expected.getMessage(), resultErrorMessage.getMessage());
    }

    @Test
    public void handleRunTimeExceptionTest() {
        // Arrange
        RuntimeException exception = new RuntimeException("Run time exception");
        ErrorMessage expected = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalTime.now(),
                "Run time exception");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleRuntimeException(exception);
        ErrorMessage resultErrorMessage = (ErrorMessage) result.getBody();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(expected.getMessage(), resultErrorMessage.getMessage());
    }
}
