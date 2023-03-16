package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.dto.assembler.UserStoryDtoListAssembler;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.model.SprintBacklog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class ViewScrumBoardController is built to visualize the Scrum Board.
 */
public class ViewScrumBoardController {
    private final Company company;

    /**
     * View constructor ViewScrumBoardController
     */
    public ViewScrumBoardController(Company company) {
        this.company = company;
    }

    /**
     * This method shows the Scrum board which has a list of all user stories and their status that are in a sprint
     * backlog of a sprint of a project.
     *
     * @param projectDto        of the project
     * @param iFactoryUserStory interface one must use to copy the User Stories
     *                          contained in the Sprint Backlog.
     * @return a list of userStoriesDto that carry data about user stories and their status.
     */
    public List<UserStoryDto> getScrumBoard(ProjectDto projectDto, LocalDate date, IFactoryUserStory iFactoryUserStory)
    {
        List<UserStoryDto> result = new ArrayList<>();

        Optional<SprintBacklog> sprintBacklogOptional = company.getScrumBoard(projectDto.code, date, iFactoryUserStory);
        if (sprintBacklogOptional.isPresent()) {
            SprintBacklog sprintBacklog = sprintBacklogOptional.get();
            List<UserStoryDto> userStories = UserStoryDtoListAssembler.backlogToDto(sprintBacklog, iFactoryUserStory);
            result.addAll(userStories);
        }
        return result;
    }

}
