package org.switch2022.project.ddd.domain.model.sprint;


import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.List;
import java.util.Optional;

public interface ISprintRepository {

    Optional<Sprint> getSprintById (SprintId sprintId);

    int getSprintNumber();

    boolean addSprintToSprintRepository(Sprint sprint);

    List<Sprint> findAllByProject(Code projectCode);

    /**
     * This method estimates the effort of a user story.
     *
     * @param usId of the user story to estimate the effort.
     * @param effort given to the user story.
     * @return TRUE if the effort was set and false otherwise.
     */

    boolean estimateEffortUserStory(UsId usId, int effort, SprintId sprintId);
}
