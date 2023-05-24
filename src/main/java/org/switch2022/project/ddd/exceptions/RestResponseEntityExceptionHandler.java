package org.switch2022.project.ddd.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * Global exception handler class using the Spring Boot @ControllerAdvice annotation.
 * When an exception is thrown in the application, Spring will look for a matching
 * @ExceptionHandler method in the @ControllerAdvice class and use it to handle the exception. If
 * no matching @ExceptionHandler method is found in any @ControllerAdvice class, Spring will
 * return a default error response.
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    protected ResponseEntity<Object> handleProjectNotFound(ProjectNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(NotFoundInRepoException.class)
    protected ResponseEntity<Object> handleNotFoundInRepo(NotFoundInRepoException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsInRepoException.class)
    protected ResponseEntity<Object> handleAlreadyExistsInRepo(AlreadyExistsInRepoException exception) {
        return new ResponseEntity<>(exception.getMessage(), CONFLICT);
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }
}
