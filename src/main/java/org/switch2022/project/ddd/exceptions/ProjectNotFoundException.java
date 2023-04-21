package org.switch2022.project.ddd.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException (String message) {
        super (message);
    }
}
