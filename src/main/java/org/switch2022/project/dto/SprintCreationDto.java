package org.switch2022.project.dto;

import java.time.LocalDate;

public class SprintCreationDto {
    /**
     * Attributes
     */
    public final LocalDate startDate;
    public final int sprintDuration;

    public final String sprintNumber;

    /**
     * This method creates an SprintCreationDto.
     */
    public SprintCreationDto(LocalDate startDate, int sprintDuration, String sprintNumber) {
        this.startDate = startDate;
        this.sprintDuration = sprintDuration;
        this.sprintNumber = sprintNumber;
    }

    /**
     * This method returns the Sprint Number.
     */
    public String getSprintNumber() {
        return this.sprintNumber;
    }

    /**
     * This method returns the Start Date.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

}
