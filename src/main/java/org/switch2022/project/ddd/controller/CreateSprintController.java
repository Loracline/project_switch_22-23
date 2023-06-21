package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.CreateSprintService;

@Controller
public class CreateSprintController {
    /**
     * The CreateSprintController class serves as an intermediary between the user
     * interface
     * (UI) and the business logic of creation of sprints.
     * This controller handles the use case specified in the US019 where it is
     * requested to create a sprint.
     */
    @Autowired
    CreateSprintService createSprintService;

    /**
     * This method creates a sprint
     *
     * @param projectCode of the sprint.
     * @param startDate   of the sprint.
     * @return a string with the sprintId.
     * @throws Exception if the sprint already exists.
     */
    public String createSprint(String projectCode, String startDate) throws Exception {
        return createSprintService.createSprint(projectCode, startDate);
    }
}
