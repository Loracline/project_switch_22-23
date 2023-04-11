package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class SprintId implements ValueObject<SprintId> {
    private final String id;

    /**
     * Constructor.
     *
     * @param projectCode project code.
     * @param sprintNumber sprint number.
     */
    public SprintId(final String projectCode, final String sprintNumber) {
        Validate.notNullOrEmptyOrBlank(projectCode, "project code");
        Validate.notNullOrEmptyOrBlank(sprintNumber, "sprint number");

        this.id = (projectCode + "_" + sprintNumber).toLowerCase();
    }

    /**
     * Getter method for the attribute: sprintId.
     *
     * @return String representation of the sprint ID.
     */
    public String getSprintId() {
        return id;
    }

    /**
     * This method checks if two instances of SprintId are equal by comparing the value of the attribute sprint id.
     *
     * @param other SprintId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(SprintId other) {
        return other != null && this.id.equals(other.id);
    }

    /**
     * This method checks if an instance of SprintId is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SprintId other = (SprintId) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
