package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.utils.Validate;

@Controller
public class CreateUserStoryController {
    /**
     * The CreateUserStoryController class serves as an intermediary between the user
     * interface
     * (UI) and the business logic of creating a user story and adding it to the
     * product backlog.
     * This controller handles the use case specified in the US017, where a product
     * owner wants
     * to create a user story and add it to the product backlog.
     * The CreateUserStoryController receives the necessary data from the UI, such as
     * the user
     * story creation data, and passes it to the domain model through the appropriate
     * service. It
     * then returns the result of the operation back to the UI,indicating whether the
     * user story
     * was successfully created or not.
     */

    @Autowired
    private UsService usService;


    /**
     * This method creates a User Story.
     *
     * @param userStoryCreationDto the UserStoryCreationDto that represents the data
     *                             for creating the user story
     * @return the created User Story ID
     * @throws Exception if the project code of interest or UserStoryCreationDto is null
     *                   or empty
     */
    public UsId createUs(UserStoryCreationDto userStoryCreationDto) throws Exception {
        Validate.notNull(userStoryCreationDto, "User Story Creation Dto");
        return usService.createUs(userStoryCreationDto);
    }
}
