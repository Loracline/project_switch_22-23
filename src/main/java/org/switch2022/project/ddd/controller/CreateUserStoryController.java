package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.utils.Utils;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateUserStoryController {
    /**
     * The CreateUserStoryController class serves as an intermediary between the user interface
     * (UI) and the
     * business logic of creating a user story and adding it to the product backlog. This
     * controller handles the use
     * case specified in the US017, where a product owner wants to create a user story and add it
     * to the product
     * backlog.
     * The CreateUserStoryController receives the necessary data from the UI, such as the project
     * and the user story
     * creation data, and passes it to the domain model through the appropriate services. It then
     * returns the
     * result of the operation back to the UI, indicating whether the user story was successfully
     * created or not.
     */

    @Autowired
    private UsService usService;


    /**
     * This method creates a new user story in the requested project and adds it to the product
     * backlog.
     *
     * @param projectCodeOfInterest the code string that represents the project in which the user
     *                              story will be created
     * @param userStoryCreationDto  the UserStoryCreationDto that represents the data for
     *                              creating the user story
     * @return true if the user story is created and added to the product backlog successfully.
     * @throws IllegalArgumentException if either of the input parameters is null.
     */
    public boolean createUs(String projectCodeOfInterest,
                            UserStoryCreationDto userStoryCreationDto) throws Exception {
        Validate.notNullOrEmptyOrBlank(projectCodeOfInterest, "Project Code Of Interest");
        Validate.notNull(userStoryCreationDto, "User Story Creation Dto");

        int codeNumber = Utils.getIntFromAlphanumericString(projectCodeOfInterest, "P");
        Code projectCode = new Code(codeNumber);

        UsNumber userStoryNumber = new UsNumber(userStoryCreationDto.userStoryNumber);
        UsText userStoryText = new UsText(userStoryCreationDto.userStoryText);
        Actor actor = new Actor(userStoryCreationDto.actor);
        int priority = userStoryCreationDto.priority;
        List<AcceptanceCriteria> acceptanceCriteria =
                convertListOfStringsToAnAcceptanceCriteriaList(userStoryCreationDto);

        return usService.createUs(userStoryNumber, userStoryText, actor, priority,
                acceptanceCriteria, projectCode);
    }

    /**
     * This method receives a UserStoryCreationDto and to convert your list of "acceptCriteria"
     * strings into a list<br>
     * of objects of type AcceptanceCriteria.
     *
     * @param userStoryCreationDto that represents the data for creating the user story
     * @return a list of objects of type AcceptanceCriteria.
     */
    private static List<AcceptanceCriteria>
    convertListOfStringsToAnAcceptanceCriteriaList(UserStoryCreationDto userStoryCreationDto) {

        List<AcceptanceCriteria> acceptanceCriteria = new ArrayList<>();
        for (int i = 0; i < userStoryCreationDto.acceptanceCriteria.size(); i++) {
            AcceptanceCriteria acceptanceCriteriaElement =
                    new AcceptanceCriteria(userStoryCreationDto.acceptanceCriteria.get(i));
            acceptanceCriteria.add(acceptanceCriteriaElement);
        }
        return acceptanceCriteria;
    }
}
