package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryInSprintDto;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddUserStoryToSprintBacklogService {
    @Qualifier("sprint_jpa")
    @Autowired
    private ISprintRepository sprintRepository;
    @Qualifier("us_jpa")
    @Autowired
    private IUsRepository usRepository;


    /**
     * Adds a user story to the Sprint Backlog.
     * Verifies if the sprint is valid to add a User Story and if the user Story is valid to be added to the Sprint.
     *
     * @param dto The UserStoryInSprintDto containing the user story ID and sprint ID.
     * @return {@code true} if the user story is added successfully, and {@code false} otherwise.
     * @throws RuntimeException if the User Story is not added to the sprint.
     */

    public boolean addUserStoryToSprint(UserStoryInSprintDto dto) {
        String projectCodeFromUS = dto.userStoryId.split("_")[0];
        String userStoryNumber = dto.userStoryId.split("_")[1];
        UsId userStoryId = new UsId(projectCodeFromUS, userStoryNumber);
        UserStory userStory = getUserStory(userStoryId);

        String projectCodeFromSprint = dto.sprintId.split("_")[0];
        String sprintNumber = dto.sprintId.split("_")[1];
        SprintId sprintId = new SprintId(projectCodeFromSprint, sprintNumber);
        Sprint sprint = getSprintById(sprintId);

        boolean userStoryWasAddedToSprintBacklog = false;
        if (isSprintValidToAddUserStories(sprint) && isUserStoryValidToBeAddedToSprint(sprint, userStory)) {
            sprint.addUserStory(userStoryId);
            sprintRepository.save(sprint);
            userStoryWasAddedToSprintBacklog = true;
        }
        return userStoryWasAddedToSprintBacklog;
    }

    /**
     * Checks if a sprint is valid to add user stories to the Sprint Backlog.
     *
     * @param sprint The Sprint to be checked.
     * @return {@code true} if the sprint is valid to add user stories.
     * @throws RuntimeException if the sprint is not valid to add user stories.
     */
    private boolean isSprintValidToAddUserStories(Sprint sprint) {
        if (!sprint.isOpen()) {
            throw new RuntimeException("Cannot add user stories to a sprint that is not in the 'OPEN' state");
        }
        return true;
    }

    /**
     * Checks if a User Story is valid to be added to the Sprint Backlog.
     *
     * @param sprint    The Sprint to validate.
     * @param userStory The User Story to validate.
     * @return {@code true} if the User Story is valid to be added to the Sprint Backlog.
     * @throws RuntimeException if the User Story does not have the 'PLANNED' status or if the Sprint and User Story do
     *                          not belong to the same project.
     */
    private boolean isUserStoryValidToBeAddedToSprint(Sprint sprint, UserStory userStory) {
        if (!doesUserStoryHavePlannedStatus(userStory)) {
            throw new RuntimeException("Only User Stories with 'PLANNED' status can be added to the Sprint");
        }
        if (!validateSprintAndUserStoryProject(sprint, userStory)) {
            throw new RuntimeException("The Sprint and User Story do not belong to the same project");
        }
        return true;
    }

    /**
     * Checks if a User Story has the 'PLANNED' status.
     *
     * @param userStory The User Story to be checked.
     * @return {@code true} if the User Story has the 'PLANNED' status and {@code false} otherwise. .
     */
    private boolean doesUserStoryHavePlannedStatus(UserStory userStory) {
        return userStory.hasStatus(Status.PLANNED);
    }

    /**
     * Validates if the Sprint and User Story belong to the same project.
     *
     * @param sprint    The Sprint to validate.
     * @param userStory The User Story to validate.
     * @return {@code true} if the Sprint and User Story belong to the same project and {@code false} otherwise.
     */
    private boolean validateSprintAndUserStoryProject(Sprint sprint, UserStory userStory) {
        Code projectCodeFromUserStory = new Code(Utils.getIntFromAlphanumericString(userStory.getProjectCode(), "p"));
        return sprint.hasProjectCode(projectCodeFromUserStory);
    }

    /**
     * This method retrieves a sprint from the repository using the sprintID
     *
     * @param sprintId of the sprint to be retrieved
     * @return a sprint
     */

    private Sprint getSprintById(SprintId sprintId) {
        Sprint sprint;
        Optional<Sprint> sprintOptional = sprintRepository.findById(sprintId);
        if (sprintOptional.isPresent()) {
            sprint = sprintOptional.get();
        } else {
            throw new RuntimeException("No sprint with that id");
        }
        return sprint;
    }


    /**
     * this method retrieves an userStory from the repository by the usId
     *
     * @param usId of the userStory to be retrieved
     * @return UserStory
     */

    private UserStory getUserStory(UsId usId) {
        UserStory userStory;
        List<UsId> usIdList = new ArrayList<>();
        usIdList.add(usId);
        List<UserStory> userStoryList = usRepository.getListOfUsWithMatchingIds(usIdList);
        if (userStoryList.isEmpty()) {
            throw new NotFoundInRepoException("There is no User Story");
        } else {
            userStory = userStoryList.get(0);
        }
        return userStory;
    }
}
