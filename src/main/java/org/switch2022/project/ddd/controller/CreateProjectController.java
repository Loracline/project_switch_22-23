package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ProjectCreationService;
import org.switch2022.project.ddd.dto.ProjectCreationDto;

/**
 * Class CreateProjectController is built to create business sectors.
 */
@Controller
public class CreateProjectController {
    @Autowired
    private ProjectCreationService projectCreationService;

    /**
     * This method receives a dto as and creates a new project.
     *
     * @param projectCreationDto that represents the project to be created.
     * @return TRUE if the project is created, and throws an AlreadyExistsInRepoException exception otherwise.
     */

    public String createProject(ProjectCreationDto projectCreationDto) {
        return projectCreationService.createProject(projectCreationDto).getCode();
    }
}
