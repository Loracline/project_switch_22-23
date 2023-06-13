package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.dto.SprintStatusDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.List;
import java.util.Optional;

/**
 * Class ChangeSprintStatusService allows to change the status of a sprint.
 */
@Service
public class SprintStatusChangeService {
    @Qualifier("sprint_jpa")
    @Autowired
    private ISprintRepository sprintRepository;

    @Qualifier("us_jpa")
    @Autowired
    private IUsRepository userStoryRepository;

    /**
     * This method changes the status os a sprint.
     *
     * @param sprintStatusDto with the sprintId and the status.
     * @return true if the status is changed, throws an InvalidInputException if the
     * status is not valid (close or open) and a NotFoundInRepoException when the
     * sprint is not found.
     */
    public boolean changeSprintStatus(SprintStatusDto sprintStatusDto) {
        boolean wasSprintChanged = false;
        String[] sprintIdParts = sprintStatusDto.getSprintId().split("_", -2);
        SprintId sprintId = new SprintId(sprintIdParts[0], sprintIdParts[1]);
        Optional<Sprint> sprintOptional = sprintRepository.findById(sprintId);

        if (sprintOptional.isPresent()) {
            if (sprintStatusDto.getSprintStatus().equalsIgnoreCase("close")) {
                wasSprintChanged = closeSprint(sprintOptional.get());
            } else if (sprintStatusDto.getSprintStatus().equalsIgnoreCase("open")) {
                wasSprintChanged = openSprint(sprintOptional.get());
            } else {
                throw new InvalidInputException("Sprint status must be OPEN or CLOSED.");
            }
        } else {
            throw new NotFoundInRepoException("This sprint doesn't exist");
        }
        return wasSprintChanged;
    }

    /**
     * This method closes a sprint and changes the User Stories status that are blocked
     * or running to planned.
     *
     * @param sprint to be closed.
     * @return true it the sprint is closed, throws an InvalidInputException if the
     * sprint is already closed.
     */
    protected boolean closeSprint(Sprint sprint) {
        if (sprint.isOpen()) {
            List<UserStory> userStories =
                    userStoryRepository.getListOfUsWithMatchingIds(sprint.getSprintBacklog());
            for (UserStory userStory : userStories) {
                if (isUserStoryBlockedOrRunning(userStory)) {
                    userStory.changeStatus(Status.PLANNED);
                    userStoryRepository.save(userStory);
                }
            }
            return sprint.changeStatus("CLOSED");
        } else {
            throw new InvalidInputException("The sprint is already closed");
        }
    }

    /**
     * This method verifies if a User Story has status "running" or "blocked".
     *
     * @param userStory to be analysed.
     * @return true if the user story has blocked or running status and false otherwise.
     */
    private boolean isUserStoryBlockedOrRunning(UserStory userStory) {
        return userStory.hasStatus(Status.RUNNING) || userStory.hasStatus(Status.BLOCKED);

    }

    /**
     * This method opens a sprint if there are no other open sprints in the repository.
     *
     * @param sprint to be open.
     * @return true it the sprint is open, throws an AlreadyExistsInRepoException otherwise..
     */
    protected boolean openSprint(Sprint sprint) {
        if (sprintRepository.existsByStatus(SprintStatus.OPEN)) {
            throw new AlreadyExistsInRepoException("There is currently another OPEN sprint.");
        }
        return sprint.changeStatus("OPEN");
    }
}
