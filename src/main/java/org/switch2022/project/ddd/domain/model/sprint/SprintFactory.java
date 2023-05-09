package org.switch2022.project.ddd.domain.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;

@Component
public class SprintFactory implements ISprintFactory {
    /**
     * This method creates a new Sprint
     *
     * @param projectCode  the code of the project
     * @param sprintId     the id of the sprint
     * @param sprintNumber the number of the sprint.
     * @param period       the duration of the sprint.
     * @return a new Sprint
     */
    public Sprint createSprint(Code projectCode, SprintId sprintId, SprintNumber
            sprintNumber, Period period) {
        return new Sprint(projectCode, sprintId, sprintNumber, period);
    }
}
