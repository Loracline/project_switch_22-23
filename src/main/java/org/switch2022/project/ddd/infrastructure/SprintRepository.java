package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;

import java.util.*;

/**
 * Class SprintRepository is built to access and manipulate the set of sprints
 * of this company.
 */

@Repository("sprint_memory")
public class SprintRepository implements ISprintRepository {

    /**
     * Attributes
     */
    private final List<Sprint> sprints = new ArrayList<>();

    /**
     * This method checks if an instance of SprintRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        SprintRepository that = (SprintRepository) o;
        return Objects.equals(sprints, that.sprints);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(sprints);
    }

    /**
     * This method verify the existence of a sprint by id.
     *
     * @return the sprint with given code.
     */
    @Override
    public Optional<Sprint> findById(SprintId sprintId) {
        Sprint sprintRequested = null;
        int i = 0;
        while (i < this.sprints.size() && sprintRequested == null) {
            if (sprints.get(i).hasSprintId(sprintId)) {
                sprintRequested = sprints.get(i);
            }
            i++;
        }
        return Optional.ofNullable(sprintRequested);
    }

    /**
     * This method returns the number of sprints contained in the list
     *
     * @return int equivalent to the number of elements in the list
     */

    @Override
    public long count() {
        return sprints.size();
    }

    /**
     * This method adds a Sprint to Sprint repository if the sprint does not exist.
     *
     * @param sprint to be added.
     * @return TRUE if the Sprint was added to Sprint Repository and false otherwise.
     */

    @Override
    public boolean save(Sprint sprint) {
        boolean result = false;
        if (!sprints.contains(sprint)) {
            sprints.add(sprint);
            result = true;
        }
        return result;
    }

    /**
     * This method returns an unmodifiable view of the sprints.
     *
     * @return an unmodifiable view of the sprints.
     */

    @Override
    public List<Sprint> findByProjectCode(Code projectCode) {
        List<Sprint> sprintsByProject = new ArrayList<>();
        for (Sprint sprint : sprints) {
            if (sprint.hasProjectCode(projectCode)) {
                sprintsByProject.add(sprint);

            }
        }
        return Collections.unmodifiableList(sprintsByProject);
    }

    /**
     * This method checks if at least one instance of Sprint with a given status already exists in the list of sprints.
     *
     * @param sprintStatus SprintStatus to look for in the sprint list.
     * @return true if at least one instance of Sprint with a given status already exists in the list, and false otherwise.
     */
    public boolean existsByStatus(SprintStatus sprintStatus) {
        boolean exists = false;
        for (Sprint sprint : sprints) {
            if (sprint.hasStatus(sprintStatus)) {
                exists = true;

            }
        }
        return exists;
    }

    /**
     * This method checks if at least one instance of Sprint with a given id already exists in the list of sprints.
     *
     * @param sprintId SprintId to look for in the sprint list.
     * @return true if at least one instance of Sprint with a given id already exists in the list,
     * and false otherwise.
     */

    @Override
    public boolean existsById(SprintId sprintId) {
        boolean sprintExists = false;
        for (Sprint sprint : sprints) {
            if (sprint.hasSprintId(sprintId)) {
                sprintExists = true;
            }
        }
        return sprintExists;
    }

    /**
     * This method checks if one given sprint has the status given
     *
     * @param sprintId the identifier of the sprint
     * @param status   the sprint status that needs to be checked
     * @return true if the sprint has the given status and false otherwise
     */

    public boolean hasStatus(SprintId sprintId, SprintStatus status) {
        Optional<Sprint> sprint = findById(sprintId);
        return sprint.isPresent() && sprint.get().hasStatus(status);
    }
}
