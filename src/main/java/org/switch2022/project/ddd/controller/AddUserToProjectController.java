package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ResourceAllocationService;
import org.switch2022.project.ddd.dto.AllocationDto;


/**
 * Class AddUserToProjectController is built to allocate a User to a Project.
 */

@Controller
public class AddUserToProjectController {
    @Autowired
    private ResourceAllocationService service;

    /**
     * This method adds a user account to a project.
     *
     * @param allocationDto data transfer object carrying the allocation info: projectCode, accountEmail, accountRole,
     *                      accountCostPerHour, accountPercentageOfAllocation, startDate, endDate.
     * @return true if the user account is allocated to the project and false otherwise.
     */
    public boolean addUserToProject(AllocationDto allocationDto) {
        return service.addUserToProject(allocationDto);
    }
}
