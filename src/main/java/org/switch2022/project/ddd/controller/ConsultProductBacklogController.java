package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.utils.Validate;

import java.util.List;

@Controller
public class ConsultProductBacklogController {
    /**
     * The ConsultProductBacklogController class serves as an intermediary between the user interface
     * (UI) and the business logic underlying the "US018 - As PO/SM/Team Member, I want to
     * consult the product backlog, i.e. to get the list of user stories sorted by priority."
     * The ConsultProductBacklogController receives the necessary data from the UI, such as the
     * projectCode and passes it to the domain model through the appropriate services. It then
     * returns the result of the operation back to the UI, which the list of user
     * Story Dto was successfully returned or not.
     */
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserStoryMapper userStoryMapper;

    /**
     * Constructor
     */
    public ConsultProductBacklogController() {
    }

    /**
     * This method receives the input data from the UI (projectCode) to get the respective
     * Product Backlog through the responsible domain service (ProjectService).
     * As the returned Product Backlog is a list of UsId, it's necessary get the information
     * for each Planned User Story from the responsible domain service (UsService).
     * After that it should return to the UI:
     * - A list of User Stories Dto; or
     * - If the given Product Backlog is empty, an empty list.
     *
     * @param projectCode Code of the Project.
     * @return a list of User Stories Dto.
     */
    public List<UserStoryDto> getProductBacklog(String projectCode) {
        Validate.notNullOrEmptyOrBlank(projectCode, "Project Code");

        List<UserStory> userStories = projectService.getProductBacklog(projectCode);
        return userStoryMapper.userStoryToDtoList(userStories);
    }
}
