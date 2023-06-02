package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;

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
}
