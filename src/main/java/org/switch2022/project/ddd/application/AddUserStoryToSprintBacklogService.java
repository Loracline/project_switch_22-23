package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Effort;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddUserStoryToSprintBacklogService {
    @Autowired
    private ISprintRepository sprintRepository;
    @Autowired
    private IUsRepository usRepository;

    /**
     * This method adds a userStory to the SprintBacklog. It verifies if the sprint ID is valid and if the sprint
     * period still allows to add userStories (has not started and is not finished). Checks if the userStory exists in
     * the repository and if its status is not Finished or blocked. In the end adds the userStory to the sprint.
     *
     * @param usId     of the user Story to be added
     * @param sprintId of the sprint where the userStory will be added
     * @return true if the userStory is added with success false if it is not added
     */

    public boolean addUserStoryToSprintBacklog(String usId, String sprintId) throws Exception {
        String[] parts = sprintId.split("_");
        String code = parts[0];
        String sprintNumber = parts[1];
        SprintId sprintIdVO = new SprintId(code, sprintNumber);

        String[] usParts = usId.split("_");
        String projectCode = usParts[0];
        String usNumber = usParts[1];
        UsId usIdVO = new UsId(projectCode, usNumber);

        boolean addUserStoryToSprintBacklog = false;
        Sprint sprint = getSprintById(sprintIdVO);
        if (isSprintInValidPeriod(sprint, LocalDate.now()) == 1) {
            if (hasUserStoryStatus(usIdVO) && sprint.addUserStory(usIdVO, 1)) {
                addUserStoryToSprintBacklog = true;
            }
        }
        return addUserStoryToSprintBacklog;
    }

    /**
     * This method retrieves a sprint from the repository using the sprintID
     *
     * @param sprintId of the sprint to be retrieved
     * @return a sprint
     */

    private Sprint getSprintById(SprintId sprintId) {
        Sprint sprint;
        Optional<Sprint> sprintOptional = sprintRepository.getSprintById(sprintId);
        if (sprintOptional.isPresent()) {
            sprint = sprintOptional.get();
        } else {
            throw new RuntimeException("No sprint with that id");
        }
        return sprint;
    }

    /**
     * This method checks if the sprint period still allows to add userStories (has not started and is not finished).
     *
     * @param sprint to add the userStory
     * @param date   date to verify
     * @return 1 if the sprint period is valid
     * @throws Exception if the sprint has already started or has finished
     */
    private int isSprintInValidPeriod(Sprint sprint, LocalDate date) throws Exception {
        int result;

        if (sprint.isPeriodAfterOrEqualThanDate(date)) {
            throw new Exception("The Sprint is not valid");
        } else {
            result = 1;
        }
        return result;
    }

    /**
     * This method verifies if the userStory to be added has status different from finished or blocked
     *
     * @param usId of the user story to be verified
     * @return true if the user story status is suitable
     * @throws Exception if the user story status is finished or blocked
     */
    private boolean hasUserStoryStatus(UsId usId) throws Exception {
        UserStory userStory = getUserStory(usId);
        if (userStory.hasStatus(Status.FINISHED) || userStory.hasStatus(Status.BLOCKED)) {
            throw new Exception("The User Story Status is not suitable to be added to the Sprint");
        }
        return true;
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
