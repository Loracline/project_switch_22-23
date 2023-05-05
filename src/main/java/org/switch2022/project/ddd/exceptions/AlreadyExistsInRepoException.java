package org.switch2022.project.ddd.exceptions;

public class AlreadyExistsInRepoException extends IllegalArgumentException {
    public AlreadyExistsInRepoException(String message) {
        super (message);
    }
}
