package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.UserStoryInSprintService;

@Controller
public class UserStoryEffortEstimationController {

    /**
     * The UserStoryEffortEstimationController class serves as an intermediary between
     * the user interface
     * (UI) and the business logic of estimating effort of userStories.
     * This controller handles the use case specified in the US021 where it is
     * requested to estimate the effort of
     * a user story.
     */
    @Autowired
    UserStoryInSprintService service;

    /**
     * This method adds a user story to a sprint
     *
     * @param usId     of the user story.
     * @param effort   of the user story.
     * @param sprintId were the user story is located.
     * @return true if the user story is added and false if it is already in the sprint.
     * @throws Exception if the us doesn't exist.
     */
    public boolean estimateEffortUserStory(String usId, int effort, String sprintId) throws Exception {
        return service.estimateEffortUserStory(usId, effort, sprintId);
    }
}
