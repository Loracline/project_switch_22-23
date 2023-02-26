package org.switch2022.project.dto;

import java.time.LocalDate;

public class AllocationDto {

    /**
     * Attributes of the class AllocationDto, according to the AccountInProject constructor.
     */
    public final String role;
    public final float costPerHour;
    public final float percentageAllocation;
    public final LocalDate startDate;
    public final LocalDate endDate;

    public AllocationDto(String role, float costPerHour,
                         float percentageAllocation, LocalDate startDate,
                         LocalDate endDate) {
        this.role = role;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
