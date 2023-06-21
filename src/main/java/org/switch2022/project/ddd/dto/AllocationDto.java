package org.switch2022.project.ddd.dto;

import java.time.LocalDate;

public class AllocationDto {
    /**
     * Attributes
     */
    public final String projectCode;
    public final String accountEmail;
    public final String accountRole;
    public final float accountCostPerHour;
    public final float accountPercentageOfAllocation;
    public final LocalDate startDate;
    public final LocalDate endDate;

    /**
     * Constructor
     *
     * @param projectCode                   of the project.
     * @param accountEmail                  of the resource.
     * @param accountRole                   of the resource in this project.
     * @param accountCostPerHour            of the resource.
     * @param accountPercentageOfAllocation in this project.
     * @param startDate                     of this allocation.
     * @param endDate                       of this allocation.
     */
    public AllocationDto(String projectCode, String accountEmail, String accountRole,
                         float accountCostPerHour,
                         float accountPercentageOfAllocation, LocalDate startDate,
                         LocalDate endDate) {
        this.projectCode = projectCode;
        this.accountEmail = accountEmail;
        this.accountRole = accountRole;
        this.accountCostPerHour = accountCostPerHour;
        this.accountPercentageOfAllocation = accountPercentageOfAllocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
