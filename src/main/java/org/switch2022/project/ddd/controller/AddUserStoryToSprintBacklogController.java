package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.AddUserStoryToSprintBacklogService;
import org.switch2022.project.ddd.dto.UserStoryInSprintDto;

@Controller
public class AddUserStoryToSprintBacklogController {
    /**
     * The AddUserStoryToSprintBacklogController class serves as an intermediary between the user interface
     * (UI) and the business logic of adding userStories to sprints.
     * This controller handles the use case specified in the US021 where it is requested to add a user story
     * to a sprint.
     */
    @Autowired
    AddUserStoryToSprintBacklogService service;

    /**
     * This method adds a user story to a sprint
     *
     * @return true if the user story is added and false if it is already in the sprint.
     */
    public boolean addUserStoryToSprintBacklog(UserStoryInSprintDto userStoriesInSprintDto) {
        return service.addUserStoryToSprint(userStoriesInSprintDto);
    }
}
