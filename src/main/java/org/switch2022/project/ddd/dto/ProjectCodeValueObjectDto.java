package org.switch2022.project.ddd.dto;

import org.switch2022.project.ddd.domain.value_object.Code;

/**
 * DTO (Data Transfer Object) representing a project code using a value object.
 * This class encapsulates the data related to a project code, using a Code value object.
 * It is used for transferring project code information between different layers of the application.
 */
public class ProjectCodeValueObjectDto {

    private final Code code;

    /**
     * Constructs a new instance of the ProjectCodeValueObjectDto class.
     *
     * @param code The project code as a Code value object.
     */
    public ProjectCodeValueObjectDto(Code code) {
        this.code = code;
    }

    /**
     * Retrieves the project code as a Code value object.
     *
     * @return The project code as a Code value object.
     */
    public Code getCode() {
        return code;
    }
}
