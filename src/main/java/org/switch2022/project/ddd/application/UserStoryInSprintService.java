package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Class UserStoryInSprintService allows to interact with Sprint aggregate.
 */

@Service
public class UserStoryInSprintService {

    /**
     * Attributes
     */

    @Autowired
    @Qualifier("sprint_jpa")
    private ISprintRepository sprintRepository;

    /**
     * This method estimates the effort of a user story.
     *
     * @param usId     of the user story which effort we want to estimate.
     * @param effort   to use for user story estimation.
     * @param sprintId of where the user story is allocated.
     * @return true if user story effort is estimated.
     * @throws Exception when sprint is not valid.
     */


    public boolean estimateEffortUserStory(String usId, int effort, String sprintId) throws Exception {
        boolean effortEstimation = false;

        String[] parts = sprintId.split("_");
        String code = parts[0];
        String sprintNumber = parts[1];
        SprintId sprintIdVO = new SprintId(code, sprintNumber);

        String[] usParts = usId.split("_");
        String projectCode = usParts[0];
        String usNumber = usParts[1];
        UsId usIdVO = new UsId(projectCode, usNumber);

        Sprint sprint = getSprintById(sprintIdVO);
        if (isSprintInValidPeriod(sprint, LocalDate.now()) == 1 && sprint.hasUserStory(usIdVO)) {
            effortEstimation = sprint.estimateEffortUserStory(usIdVO, effort);
        }
        return effortEstimation;
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
     * This method checks if the sprint period still allows to add userStories (has not
     * started and is not finished).
     *
     * @param sprint to add the userStory
     * @param date   date to verify
     * @return 1 if the sprint period is valid
     * @throws Exception if the sprint has already started or has finished
     */
    private static int isSprintInValidPeriod(Sprint sprint, LocalDate date) throws Exception {
        int result;

        if (sprint.isPeriodAfterOrEqualThanDate(date)) {
            throw new Exception("The Sprint is not valid");
        } else {
            result = 1;
        }
        return result;
    }
}
