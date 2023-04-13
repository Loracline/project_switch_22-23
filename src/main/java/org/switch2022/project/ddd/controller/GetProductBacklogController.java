package org.switch2022.project.ddd.controller;

import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryDto;

import java.util.ArrayList;
import java.util.List;

public class GetProductBacklogController {
    /**
     * The GetProductBacklogController class serves as an intermediary between the user interface
     * (UI) and the business logic underlying the "US018 - As PO/SM/Team Member, I want to
     * consult the product backlog, i.e. to get the list of user stories sorted by priority."
     * The GetProductBacklogController receives the necessary data from the UI, such as the
     * projectCode and passes it to the domain model through the appropriate services. It then
     * returns the result of the operation back to the UI, which the list of user
     * Story Dto was successfully returned or not.
     */
    private final ProjectService projectService;
    private final UsService usService;

    /**
     * Constructor
     */
    public GetProductBacklogController(ProjectService projectService, UsService usService) {
        this.projectService = projectService;
        this.usService = usService;
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
     * @throws IllegalArgumentException if the input parameter is null.
     */
    public List<UserStoryDto> getProductBacklog(String projectCode) throws Exception {
        if (projectCode == null) {
            throw new IllegalArgumentException("Input parameter cannot be null.");
        }

        List<UsId> productBacklog = projectService.getProductBacklog(projectCode);
        List<UserStoryDto> userStoryDtoList = new ArrayList<>();

        if (!productBacklog.isEmpty()) {
            userStoryDtoList = usService.requestAllPlannedUs(productBacklog);
        }
        return userStoryDtoList;
    }
}
