package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;

public class CreateUserStoryController {
    private final Company company;

    /**
     * Constructor
     */

    public CreateUserStoryController(Company company) {
        this.company = company;
    }
    /**
     * This method creates an userStory in the requested project
     * returns true if the userStory is successfully created
     */

    public boolean createUserStory(ProjectDto projectDto, UserStoryCreationDto userStoryCreationDto){
        return projectDto!= null && userStoryCreationDto!= null &&
                company.createUserStory(projectDto,userStoryCreationDto);
    }
}
