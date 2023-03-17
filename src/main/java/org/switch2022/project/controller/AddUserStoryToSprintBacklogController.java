package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.UserStoryDto;
/**
 * Class AddUserStoryToSprintBacklogController is built to allow access to Company
 * in Company Class.
 */
public class AddUserStoryToSprintBacklogController {

    /**
     * Attribute of the AddUserStoryToSprintBacklogController class.
     */
    private final Company company;

    /**
     * Constructor of AddUserStoryToSprintBacklogController class.
     * @param company to access Project Container
     */

    public AddUserStoryToSprintBacklogController(Company company) {
        this.company = company;
    }

    /**
     * This method adds a User Story to Sprint Backlog.
     * @param projectCode of the project one searches for.
     * @param userStoryDto of the User Story to be added to the Sprint Backlog.
     * @param sprintNumber of the Sprint that contains the sprint backlog.
     * @return TRUE if the User Story was added to Sprint Backlog and FALSE otherwise.
     */

    public boolean addUserStoryToSprintBacklog(String projectCode, UserStoryDto userStoryDto,
                                               String sprintNumber) {
        boolean result = false;
        if (projectCode != null && userStoryDto != null && sprintNumber != null) {
            result = company.addUserStoryToSprintBacklog(projectCode,
                    userStoryDto.userStoryNumber, sprintNumber);
        }
        return result;
    }
}
