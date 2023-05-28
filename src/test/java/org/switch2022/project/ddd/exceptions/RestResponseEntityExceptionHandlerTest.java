package org.switch2022.project.ddd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestResponseEntityExceptionHandlerTest {

    private final RestResponseEntityExceptionHandler exceptionHandler =
            new RestResponseEntityExceptionHandler();

    @Test
    public void handleProjectNotFoundExceptionTest() {
        // Arrange
        ProjectNotFoundException exception = new ProjectNotFoundException("Project Not Found");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleProjectNotFound(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Project Not Found", result.getBody());
    }

    @Test
    public void handleNotFoundInRepoTest() {
        // Arrange
        NotFoundInRepoException exception = new NotFoundInRepoException("Not Found in Repo");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleNotFoundInRepo(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Not Found in Repo", result.getBody());
    }

    @Test
    public void handleAlreadyExistsInRepoTest() {
        // Arrange
        AlreadyExistsInRepoException exception = new AlreadyExistsInRepoException("Already exists " +
                "in repo");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleAlreadyExistsInRepo(exception);

        // Assert
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("Already exists in repo", result.getBody());
    }

    @Test
    public void handleInvalidInputExceptionTest() {
        // Arrange
        InvalidInputException exception = new InvalidInputException("Invalid input");

        // Act
        ResponseEntity<Object> result = exceptionHandler.handleInvalidInputException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid input", result.getBody());
    }
}
