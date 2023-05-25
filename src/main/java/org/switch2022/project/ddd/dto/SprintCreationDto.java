package org.switch2022.project.ddd.dto;

/**
 * Represents a sprint creation data transfer object.
 * This class contains information about the sprint such as the project code,
 * and start date.
 */
public class SprintCreationDto {

    /**
     * Attributes
     */
    public final String projectCode;
    public final String startDate;

    /**
     * Constructor to instantiate a new sprint dto.
     * @param projectCode to add a sprint to.
     * @param startDate of the sprint.
     */

    public SprintCreationDto(String projectCode, String startDate) {
        this.projectCode = projectCode;
        this.startDate = startDate;
    }
}
