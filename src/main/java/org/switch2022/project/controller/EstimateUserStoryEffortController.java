package org.switch2022.project.controller;
import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;


/**
 * Class EstimateUserStoryEffortController acts as an intermediary between the
 * user interface (UI) and the business logic underlying the "US021 - As Team Member,
 * I want to estimate the effort of a user story in a sprint, during
 * the sprint planning ceremony."
 */

public class  EstimateUserStoryEffortController {

    /**
     * Attributes of the class EstimateUserStoryEffortController, according to the Class Diagram.
     */
    private final Company company;

    /**
     * EstimateUserStoryEffortController constructor
     */

    public EstimateUserStoryEffortController(Company company) {
        this.company = company;
    }

    /**
     * This method sets the effort of a userStory.
     * @param userStoryDto to estimate the effort.
     * @param effort       of the userStory.
     * @param projectDto  code of the project.
     * @return true if the effort is set and false otherwise.
     */

    public boolean estimateEffortUserStory (UserStoryDto userStoryDto, Effort effort, ProjectDto projectDto) {
        LocalDate date = LocalDate.now();
        return userStoryDto != null && projectDto.code != null &&
                company.estimateEffortUserStory(userStoryDto, effort, projectDto.code, date);
    }
}
