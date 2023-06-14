package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.UserStoryStatusDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class UserStoriesInSprintService allows to interact with Sprint and User Story aggregate.
 */

@Service
public class UserStoriesInSprintService {
    /**
     * Attributes
     */
    @Autowired
    @Qualifier("sprint_jpa")
    private ISprintRepository sprintRepository;

    @Autowired
    @Qualifier("us_jpa")
    private IUsRepository userStoryRepository;

    /**
     * This method extracts the User Story IDs from a list of UserStoryInSprint objects.
     *
     * @param userStoryInSprints a list of UserStoryInSprint objects.
     * @return a list of UsId objects representing the extracted User Story IDs.
     */
    private static List<UsId> extractUsIds(List<UserStoryInSprint> userStoryInSprints) {
        List<UsId> usIds = new ArrayList<>();
        for (UserStoryInSprint userStoryInSprint : userStoryInSprints) {
            usIds.add(userStoryInSprint.getUsId());
        }
        return usIds;
    }

    /**
     * This method retrieves the scrum board (e.g., list of user stories) of the current
     * sprint of a project whose date is within the current period of realization of it.
     *
     * @param sprintId the alphanumeric code of the sprint to be retrieved.
     * @return a list of UserStoryDto objects for the current sprint of the specified project and
     * date.
     */

    public List<UserStoryDto> getSprintBacklog(String sprintId) {
        List<UserStoryDto> userStoryDtos;

        String[] parts = sprintId.split("_");
        String code = parts[0];
        String sprintNumber = parts[1];
        SprintId sprintIdVO = new SprintId(code, sprintNumber);

        Optional<Sprint> sprint = sprintRepository.findById(sprintIdVO);

        if (sprint.isPresent()) {
            List<UserStoryInSprint> userStoriesInSprint = sprint.get().getUserStoriesInSprint();
            List<UsId> usIds = extractUsIds(userStoriesInSprint);
            List<UserStory> userStories = userStoryRepository.getListOfUsWithMatchingIds(usIds);
            userStoryDtos = new UserStoryMapper().userStoryToDtoList(userStories);
        } else {
            throw new NotFoundInRepoException("No sprint with that id");
        }
        return userStoryDtos;
    }

    /**
     * Changes the status of a User Story based on the provided User Story Status DTO.
     * The method first creates SprintId and UsId objects using the provided Sprint ID and User Story ID.
     * Then, it creates a Code object using the Sprint ID and compares it with the project code of the User Story.
     * If the Sprint ID is in the OPEN status and the User Story has the same project code,
     * the User Story status is changed to the new status specified in the User Story Status DTO.
     * The updated User Story is then saved in the User Story repository.
     *
     * @param userStoryStatusDto the User Story Status DTO containing the new status and the Sprint ID
     * @return true if the User Story status was successfully changed, false otherwise
     */
    public boolean changeUserStoryStatus(UserStoryStatusDto userStoryStatusDto) {
        boolean result = false;
        SprintId sprintId = createSprintId(userStoryStatusDto.sprintId);
        UsId usId = createUsId(userStoryStatusDto.usId);
        Status userStoryStatus = Status.valueOf(userStoryStatusDto.status.toUpperCase());
        UserStory userStory = getUserStory(usId);
        if (sprintRepository.hasStatus(sprintId, SprintStatus.OPEN) && isUserStoryInSprint
                (usId, sprintId)) {
            userStory.changeStatus(userStoryStatus);
            userStoryRepository.save(userStory);
            result = true;
        }
        return result;
    }

    /**
     * Creates a SprintId object from the given Sprint ID.
     * The Sprint ID is split into parts, and the first part is used as the code,
     * while the second part is used as the Sprint number.
     *
     * @param sprintId the Sprint ID to be used for creating the SprintId object
     * @return the SprintId object with the code and Sprint number derived from the Sprint ID
     */
    private static SprintId createSprintId(String sprintId) {
        Validate.notNullOrEmptyOrBlank(sprintId, "sprintId");
        String[] parts = sprintId.split("_");
        String code = parts[0];
        String sprintNumber = parts[1];
        return new SprintId(code, sprintNumber);
    }

    /**
     * Creates a UsId object from the given User Story ID.
     * The User Story ID is split into parts, and the first part is used as the code,
     * while the second part is used as the User Story number.
     *
     * @param usId the User Story ID to be used for creating the UsId object
     * @return the UsId object with the code and User Story number derived from the User Story ID
     */
    private static UsId createUsId(String usId) {
        Validate.notNullOrEmptyOrBlank(usId, "usId");
        String[] parts = usId.split("_");
        String code = parts[0];
        String usNumber = parts[1];
        return new UsId(code, usNumber);
    }

    /**
     * Retrieves the UserStory object with the specified UsId.
     * If no UserStory is found with the given UsId, a RuntimeException is thrown.
     *
     * @param usId the UsId of the UserStory to be retrieved
     * @return the UserStory object with the specified UsId, or null if not found
     * @throws RuntimeException if no UserStory is found with the given UsId
     */
    private UserStory getUserStory(UsId usId) {
        Optional<UserStory> userStoryOptional = userStoryRepository.findByUsId(usId);
        if (userStoryOptional.isEmpty()) {
            throw new RuntimeException("No user story with that id");
        }
        return userStoryOptional.get();
    }

    /**
     * Validates if the given user story belongs to the specified project code and sprint.
     * Throws a RuntimeException if the user story does not belong to the sprint.
     *
     * @param usId the UsId of the user story to be validated
     * @param sprintId the SprintId of the sprint to be checked
     * @return true if the user story belongs to the specified project code and sprint, false otherwise
     * @throws RuntimeException if the user story does not belong to the sprint
     */
    private boolean isUserStoryInSprint(UsId usId, SprintId sprintId) {
        if (!sprintRepository.hasUsId(sprintId, usId)) {
            throw new RuntimeException("The User Story doesn't belong to the sprint");
        }
        return sprintRepository.hasUsId(sprintId, usId);
    }

}
