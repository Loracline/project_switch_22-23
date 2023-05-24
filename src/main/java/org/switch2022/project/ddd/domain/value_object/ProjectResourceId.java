package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

public class ProjectResourceId implements ValueObject<ProjectResourceId> {

    private final String projectResourceId;

    /**
     * Constructor.
     *
     * @param projectResourceId the ID of the ProjectResourceId.
     */
    public ProjectResourceId(int projectResourceId) {
        Validate.notNegative(projectResourceId, "Project Resource ID");
        Validate.notZero(projectResourceId, "Project Resource ID");
        this.projectResourceId = "PR" + projectResourceId;
    }

    /**
     * This method checks if two instances of ProjectResourceId are equal by comparing the value of the attribute
     * ProjectResourceId
     *
     * @param other ProjectResourceId instance to compare with.
     * @return <code>true</code> if the two instances have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(ProjectResourceId other) {
        Validate.notNull(other, "Project Resource ID must not be null");
        return this.projectResourceId.equals(other.projectResourceId);
    }

    /**
     * This method checks if an instance of ProjectResourceId is equal to another object.
     *
     * @param other object to compare with.
     * @return <code>true</code> if the two instances are equals, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other) {
        Validate.notNull(other, "The Object To Compare must not be null");
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;
        return sameValueAs((ProjectResourceId) other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectResourceId);
    }

    /**
     * This method returns the String representation of the projectResource ID.
     *
     * @return the String representation of the projectResource ID.
     */
    public String getProjectResourceId() {
        return projectResourceId;
    }
}
