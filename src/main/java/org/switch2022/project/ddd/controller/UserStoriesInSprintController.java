package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.UserStoriesInSprintService;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.utils.Validate;



import java.time.LocalDate;

import java.util.List;

/**
 * Class ViewScrumBoardController is built to visualize the Scrum Board.
 */

@Controller
public class UserStoriesInSprintController {
    /**
     * Attributes
     */
    @Autowired
    private UserStoriesInSprintService userStoriesInSprintService;


    /**
     * This method retrieves the scrum board (e.g., list of user stories) of the current
     * sprint of a project whose date is within the current period of realization of it.
     *
     * @param projectCode the alphanumeric code of the project to retrieve the current sprint for.
     * @param date        the date to use for determining the current sprint.
     * @return a list of UserStoryDto objects for the current sprint of the specified project and
     * date.
     */

    public List<UserStoryDto> getScrumBoard(String projectCode, LocalDate date) {
        Validate.notNullOrEmptyOrBlank(projectCode, "Project Code");
        Validate.notNull(date, "Date");

        List<UserStoryDto> userStoryDtos = userStoriesInSprintService.getScrumBoard(projectCode, date);

        return userStoryDtos;
    }
}
