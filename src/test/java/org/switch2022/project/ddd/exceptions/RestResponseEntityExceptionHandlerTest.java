package org.switch2022.project.ddd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestResponseEntityExceptionHandlerTest {

    private final RestResponseEntityExceptionHandler exceptionHandler =
            new RestResponseEntityExceptionHandler();

    @Test
    public void handleNotFoundInRepoTest() {
        // Arrange
        NotFoundInRepoException exception = new NotFoundInRepoException("Not Found in Repo");
        ErrorMessage expected = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), "Not " +
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
        ErrorMessage expected = new ErrorMessage(HttpStatus.CONFLICT.value(), new Date(),
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
        ErrorMessage expected = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
                "Invalid input");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleInvalidInputException(exception);
        ErrorMessage resultErrorMessage = (ErrorMessage) result.getBody();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(expected.getMessage(), resultErrorMessage.getMessage());
    }
}
