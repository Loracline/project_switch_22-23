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

    public boolean addUserToProject(int projectCode, String email, String role, float costPerHour,
                                    float percentageOfAllocation, LocalDate startDate, LocalDate endDate) {
        return service.addUserToProject(projectCode, email, role, costPerHour, percentageOfAllocation, startDate, endDate);
    }
}
