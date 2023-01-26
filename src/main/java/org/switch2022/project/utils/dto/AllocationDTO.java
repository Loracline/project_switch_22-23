package org.switch2022.project.utils.dto;

import java.time.LocalDate;

public class AllocationDTO {

    /**
     * Attributes of the class AllocationDTO, according to the AccountInProject constructor.
     */
    public final String role;
    public final float costPerHour;
    public final float percentageAllocation;
    public final LocalDate startDate;
    public final LocalDate endDate;

    public AllocationDTO(String role, float costPerHour, float percentageAllocation, LocalDate startDate, LocalDate endDate) {
        this.role = role;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
