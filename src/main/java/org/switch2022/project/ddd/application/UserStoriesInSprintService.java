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
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

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
}
