package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ResourceAllocationService;

import java.time.LocalDate;

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
     * @param projectCode                   represents the code of the project in which user account is intended to
     *                                      be allocated in.
     * @param email                  represents the email of account to be allocated.
     * @param role                   represents the role to be associated with the account in that given project.
     * @param costPerHour            represents the cost per hour for allocate the user account to the project.
     * @param percentageOfAllocation represents the percentage of allocation of the user account to that
     *                                      project.
     * @param startDate                     represents the allocation start date.
     * @param endDate                       respresents the end date of the allocation.
     * @return true if the user account is allocated to the project and false otherwise.
     */
    public boolean addUserToProject(int projectCode, String email, String role, float costPerHour,
                                    float percentageOfAllocation, LocalDate startDate, LocalDate endDate) {
        return service.addUserToProject(projectCode, email, role, costPerHour, percentageOfAllocation, startDate, endDate);
    }
}
