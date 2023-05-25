package org.switch2022.project.ddd.dto;

/**
 * Represents a sprint creation data transfer object.
 * This class contains information about the sprint such as the project code,
 * and start date.
 */
public class BusinessSectorCreationDto {

    /**
     * Attributes
     */
    public final String name;

    /**
     * Constructor to instatiate a new business sector dto.
     * @param name the name of the
     */

    public BusinessSectorCreationDto (String name) {
        this.name = name;
    }
}
