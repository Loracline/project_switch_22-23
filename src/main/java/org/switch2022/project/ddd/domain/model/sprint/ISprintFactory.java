package org.switch2022.project.ddd.domain.model.sprint;

import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;

public interface ISprintFactory {
    /**
     * This method creates a new Sprint
     * @param projectCode the code of the project
     * @param sprintId the id of the sprint
     * @param sprintNumber the number of the sprint.
     * @param period       the duration of the sprint.
     * @return a new Sprint
     */
    Sprint createSprint (Code projectCode, SprintId sprintId, SprintNumber
            sprintNumber, Period period);
}
