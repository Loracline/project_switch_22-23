package org.switch2022.project.ddd.domain.model.sprint;


import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;

import java.util.List;
import java.util.Optional;

public interface ISprintRepository {

    Optional<Sprint> getSprintById (SprintId sprintId);

    int getSprintNumber();

    boolean addSprintToSprintRepository(Sprint sprint);

    List<Sprint> findAllByProject(Code projectCode);
}
