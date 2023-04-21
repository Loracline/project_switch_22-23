package org.switch2022.project.ddd.controller;

import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.exceptions.UserStoryAlreadyExistException;
import org.switch2022.project.ddd.utils.Utils;



public class CreateUsController {

    /**
     * The CreateUserStoryController class serves as an intermediary between the user interface (UI) and the
     * business logic of creating a user story and adding it to the product backlog. This controller handles the use
     * case specified in the US017, where a product owner wants to create a user story and add it to the product
     * backlog.
     * The CreateUserStoryController receives the necessary data from the UI, such as the project and the user story
     * creation data, and passes it to the domain model through the appropriate services. It then returns the
     * result of the operation back to the UI, indicating whether the user story was successfully created or not.
     */
    private final UsService usService;

    /**
     * Constructor
     */
    public CreateUsController(UsService usService) {
        this.usService = usService;
    }

    /**
     * This method creates a new user story in the requested project and adds it to the product backlog.
     *
     * @param projectDto           the ProjectDto that represents the project in which the user story will be created
     * @param userStoryCreationDto the UserStoryCreationDto that represents the data for creating the user story
     * @return true if the user story is created and added to the product backlog successfully.
     * @throws IllegalArgumentException if either of the input parameters is null.
     * @throws Exception                if an error occurs while creating the user story or adding it to.
     */


    public boolean createUs(ProjectDto projectDto, UserStoryCreationDto userStoryCreationDto)  {
        if (projectDto == null || userStoryCreationDto == null) {
            throw new IllegalArgumentException("Input parameters cannot be null.");
        }
        int codeNumber = Utils.getIntFromAlphanumericString(projectDto.code,"P");
        Code projectCode = new Code(codeNumber);

        UsNumber userStoryNumber = new UsNumber(userStoryCreationDto.userStoryNumber);
        UsText userStoryText = new UsText(userStoryCreationDto.userStoryText);
        Actor actor = new Actor(userStoryCreationDto.actor);
        int priority = userStoryCreationDto.priority;

        UsId usId = usService.createUs(userStoryNumber, userStoryText, actor, priority, projectCode);
        try {
            usService.addUsToProductBacklog(usId, projectCode, userStoryCreationDto.priority);
        } catch (UserStoryAlreadyExistException usaee) {
            usService.deleteUs(usId);
            throw usaee;
        }
        return true;
    }
}
