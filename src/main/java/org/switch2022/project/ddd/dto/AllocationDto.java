package org.switch2022.project.ddd.dto;

import java.time.LocalDate;

public class AllocationDto {
    /**
     * Attributes
     */
    public int projectCode;
    public String accountEmail;
    public String accountRole;
    public float accountCostPerHour;
    public float accountPercentageOfAllocation;
    public LocalDate startDate;
    public LocalDate endDate;

    /**
     * Constructor
     */
    public AllocationDto(int projectCode, String accountEmail, String accountRole, float accountCostPerHour,
                         float accountPercentageOfAllocation, LocalDate startDate, LocalDate endDate) {
        this.projectCode = projectCode;
        this.accountEmail = accountEmail;
        this.accountRole = accountRole;
        this.accountCostPerHour = accountCostPerHour;
        this.accountPercentageOfAllocation = accountPercentageOfAllocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
