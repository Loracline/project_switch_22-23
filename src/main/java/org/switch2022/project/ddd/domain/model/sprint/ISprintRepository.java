package org.switch2022.project.ddd.domain.model.sprint;


import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;

import java.util.List;
import java.util.Optional;

public interface ISprintRepository {

    /**
     * Retrieves a Sprint object by its SprintId.
     *
     * @param sprintId The identifier of the Sprint.
     * @return An Optional object containing the found Sprint, or an empty Optional if no Sprint is found.
     */
    Optional<Sprint> findById(SprintId sprintId);

    /**
     * Counts the number of Sprint objects.
     * @return The total count of Sprint objects.
     */
    long count();

    /**
     * Saves a Sprint object.
     *
     * @param sprint The Sprint object to be saved.
     * @return True if the save operation is successful, false otherwise.
     */
    boolean save(Sprint sprint);
    /**
     * Retrieves a list of Sprint objects associated with a specific project code.
     *
     * @param projectCode The code of the project.
     * @return A list of Sprint objects associated with the project code.
     */
    List<Sprint> findByProjectCode(Code projectCode);
}
