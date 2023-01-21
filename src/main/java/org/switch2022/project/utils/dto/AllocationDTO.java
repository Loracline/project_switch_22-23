package org.switch2022.project.utils.dto;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;

import java.time.LocalDate;

public class AllocationDTO {

    /**
     * Attributes of the class AllocationDTO, according to the AccountInProject constructor.
     */

    public Account account;
    public Project project;
    public float costPerHour;
    public float percentageAllocation;
    public LocalDate startDate;
}
