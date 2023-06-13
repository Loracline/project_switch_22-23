package org.switch2022.project.ddd.dto;

import org.switch2022.project.ddd.domain.value_object.SprintNumber;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) representing a sprint with value objects.
 * This class encapsulates the data related to a sprint, using value objects for certain properties.
 * It is used for transferring sprint information between different layers of the application.
 */
public class SprintValueObjectsDto {

    private final SprintNumber number;
    private final SprintStatus status;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new instance of the SprintValueObjectsDto class.
     *
     * @param number    The sprint number value object.
     * @param status    The sprint status value object.
     * @param startDate The start date of the sprint.
     * @param endDate   The end date of the sprint.
     */
    public SprintValueObjectsDto(SprintNumber number, SprintStatus status, LocalDate startDate, LocalDate endDate) {
        this.number = number;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Retrieves the sprint number value object.
     *
     * @return The sprint number value object.
     */
    public String getNumber() {
        return number.getSprintNumber();
    }

    /**
     * Retrieves the sprint status value object.
     *
     * @return The sprint status value object.
     */
    public String getStatus() {
        return status.getStatus();
    }

    /**
     * Retrieves the start date of the sprint.
     *
     * @return The start date of the sprint.
     */
    public String getStartDate() {
        return startDate.toString();
    }

    /**
     * Retrieves the end date of the sprint.
     *
     * @return The end date of the sprint.
     */
    public String getEndDate() {
        return endDate.toString();
    }
}
