package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;

public class CreateUserStoryController {
    /**
     * Class CreateUserStoryController acts as an intermediary between the
     * user interface (UI) and the business logic underlying the US017 - As a Product Owner, I want to create a user
     * story and add it to the Product Backlog.
     */
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
