package org.switch2022.project.ddd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;

import static org.springframework.http.HttpStatus.*;

/**
 * Global exception handler class using the Spring Boot @ControllerAdvice annotation.
 * When an exception is thrown in the application, Spring will look for a matching
 * @ExceptionHandler method in the @ControllerAdvice class and use it to handle the exception. If
 * no matching @ExceptionHandler method is found in any @RestControllerAdvice class, Spring will
 * return a default error response.
 */

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    protected ResponseEntity<Object> handleProjectNotFound(ProjectNotFoundException exception) {
        final ErrorMessage message = new ErrorMessage(
                NOT_FOUND.value(),
                new Date(),
                exception.getMessage());
        return new ResponseEntity<>(message, NOT_FOUND);
    }

    @ExceptionHandler(NotFoundInRepoException.class)
    protected ResponseEntity<Object> handleNotFoundInRepo(NotFoundInRepoException exception) {
        final ErrorMessage message = new ErrorMessage(
                NOT_FOUND.value(),
                new Date(),
                exception.getMessage());
        return new ResponseEntity<>(message, NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsInRepoException.class)
    protected ResponseEntity<Object> handleAlreadyExistsInRepo(AlreadyExistsInRepoException exception) {
        final ErrorMessage message = new ErrorMessage(
                CONFLICT.value(),
                new Date(),
                exception.getMessage());
        return new ResponseEntity<>(message, CONFLICT);
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        final ErrorMessage message = new ErrorMessage(
                BAD_REQUEST.value(),
                new Date(),
                exception.getMessage());
        return new ResponseEntity<>(message, BAD_REQUEST);
    }
}
