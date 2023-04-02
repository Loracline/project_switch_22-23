package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class SprintNumber implements ValueObject<SprintNumber> {

    private final String sprintId;

    /**
     * Constructor.
     *
     * @param sprintNumber the number of the sprint.
     */
    public SprintNumber(final Number sprintNumber) {
        Validate.notNull(sprintNumber, "sprint number");
        Validate.notNegative(sprintNumber, "sprint number");

        this.sprintId = String.format("S%03d", sprintNumber).toLowerCase();

    }

    /**
     * Getter method for the attribute: sprintId.
     *
     * @return String representation of the sprint ID.
     */
    public String getSprintId() {
        return sprintId;
    }

    /**
     * This method checks if two instances of SprintNumber are equal by comparing the value of the attribute sprint id.
     *
     * @param other SprintNumber instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(SprintNumber other) {
        return other != null && this.sprintId.equals(other.sprintId);
    }

    /**
     * This method checks if an instance of SprintNumber is equal to another object.
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

        SprintNumber other = (SprintNumber) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return sprintId.hashCode();
    }
}
