package org.switch2022.project.utils.dto;

import java.time.LocalDate;

public class AccountInProjectDTO {

    /**
     * Attributes of the class AccountInProjectDTO, according to the AccountInProject constructor.
     */

    public AccountDTO accountDTO;
    public ProjectDTO projectDTO;
    public String role;
    public float costPerHour;
    public float percentageAllocation;
    public LocalDate startDate;
    public LocalDate endDate;
}
