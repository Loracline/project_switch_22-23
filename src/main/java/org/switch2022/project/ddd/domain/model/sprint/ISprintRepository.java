package org.switch2022.project.ddd.domain.model.sprint;


import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.domain.value_object.UsId;

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
     *
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

    boolean existsById(SprintId sprintId);

    /**
     * This method checks if one given sprint has the status given
     *
     * @param sprintId the identifier of the sprint
     * @param status   the sprint status that needs to be checked
     * @return true if the sprint has the given status and false otherwise
     */

    boolean hasStatus(SprintId sprintId, SprintStatus status);

    /**
     * This method checks if the userStory is in the sprint
     * @param usId the id of the userStory
     * @param sprintId the id of the sprint
     * @return true if the userStory is present
     */
    boolean hasUsId(SprintId sprintId, UsId usId);

    /**
     * Retrieves a Sprint object by its ProjectCode and status.
     *
     * @param projectCode the project code of the sprint
     * @param status the status of the sprint
     * @return An Optional object containing the found Sprint, or an empty Optional if no Sprint is found.
     */

    Optional<Sprint> findByProjectCodeAndStatus(Code projectCode,SprintStatus status);

    /**
     * This method checks if at least one instance of Sprint with a given status already exists in the list of
     * sprints of a given project.
     *
     * @param projectCode the code of the project to which the sprints belong.
     * @param status SprintStatus to look for in the sprint list.
     * @return true if at least one instance of Sprint with a given status already exists in the list,
     * and false otherwise.
     */
    boolean existsByProjectCodeAndStatus(Code projectCode,SprintStatus status);
}
