package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.utils.Utils;

import java.time.LocalDate;
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
    private ISprintRepository sprintRepository;

    @Autowired
    private IUsRepository userStoryRepository;

    /**
     * This method retrieves the scrum board (e.g., list of user stories) of the current
     * sprint of a project whose date is within the current period of realization of it.
     *
     * @param projectCode the alphanumeric code of the project to retrieve the current sprint for.
     * @param date        the date to use for determining the current sprint.
     * @return a list of UserStoryDto objects for the current sprint of the specified project and
     * date.
     */
    public List<UserStoryDto> getScrumBoard(String projectCode, LocalDate date) {
        List<UserStoryDto> userStoryDtos = new ArrayList<>();

        int codeNumber = Utils.getIntFromAlphanumericString(projectCode, "P");
        Code code = new Code(codeNumber);
        List<Sprint> sprints = this.sprintRepository.findAllByProject(code);

        Optional<Sprint> currentSprint = getCurrentSprint(sprints, date);

        if (currentSprint.isPresent()) {
            List<UserStoryInSprint> userStoryInSprints =
                    currentSprint.get().getUserStoriesInSprint();
            List<UsId> usIds = extractUsIds(userStoryInSprints);
            List<UserStory> userStories =
                    this.userStoryRepository.getListOfUsWithMatchingIds(usIds);
            userStoryDtos = new UserStoryMapper().userStoryToDtoList(userStories);
        }

        return userStoryDtos;
    }

    /**
     * This method returns the current sprint based on a list of sprints and a specific date.
     *
     * @param sprints a list of Sprint objects representing the available sprints.
     * @param date    the date for which the current sprint is to be determined.
     * @return an Optional object containing the current sprint if found, or an empty Optional if
     * no current sprint is found.
     */
    private Optional<Sprint> getCurrentSprint(List<Sprint> sprints, LocalDate date) {
        Optional<Sprint> currentSprint = Optional.empty();
        for (Sprint sprint : sprints) {
            if (sprint.isDateWithinPeriod(date)) {
                currentSprint = Optional.of(sprint);
            }
        }
        return currentSprint;
    }

    /**
     * This method extracts the User Story IDs from a list of UserStoryInSprint objects.
     *
     * @param userStoryInSprints a list of UserStoryInSprint objects.
     * @return a list of UsId objects representing the extracted User Story IDs.
     */
    private List<UsId> extractUsIds(List<UserStoryInSprint> userStoryInSprints) {
        List<UsId> usIds = new ArrayList<>();
        for (UserStoryInSprint userStoryInSprint : userStoryInSprints) {
            usIds.add(userStoryInSprint.getUsId());
        }
        return usIds;
    }
}
