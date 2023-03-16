package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.SprintCreationDto;

public class CreateSprintController {

    private final Company company;

    /**
     * Constructor
     *
     * @param company it receives a Company to be created
     */

    public CreateSprintController(Company company) {
        this.company = company;
    }

    /**
     * This method creates an userStory in the requested project
     * returns true if the userStory is successfully created
     */
    public boolean createSprint(ProjectDto projectDto, SprintCreationDto sprintCreationDto) {
        return projectDto != null && sprintCreationDto != null &&
                company.createSprint(projectDto, sprintCreationDto);
    }
}
