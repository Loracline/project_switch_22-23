package org.switch2022.project.ddd.dto;

/**
 * DTO (Data Transfer Object) representing a project code as a string.
 * This class encapsulates the data related to a project code, using a string value.
 * It is used for transferring project code information between different layers of the application.
 */
public class ProjectCodeStringDto {

    private final String code;

    /**
     * Constructs a new instance of the ProjectCodeStringDto class.
     *
     * @param code The project code as a string.
     */
    public ProjectCodeStringDto(String code) {
        this.code = code;
    }

    /**
     * Retrieves the project code as a string.
     *
     * @return The project code as a string.
     */
    public String getCode() {
        return code;
    }
}
