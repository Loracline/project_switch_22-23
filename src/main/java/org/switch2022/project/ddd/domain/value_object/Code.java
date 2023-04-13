package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class Code implements ValueObject<Code> {
    private final String id;

    /**
     * Constructor.
     *
     * @param projectNumber number to attribute to project code.
     */
    public Code(final Number projectNumber) {
        Validate.notNull(projectNumber, "The project number must not be null");
        Validate.notNegative(projectNumber, "project number");

        this.id = String.format("P%03d", projectNumber).toLowerCase();
    }

    /**
     * Getter method for the attribute: code
     *
     * @return String representation of the project code.
     */
    public String getCode() {
        return id;
    }

    /**
     * This method checks if two instances of Code are equal by comparing the value of the attribute code.
     *
     * @param other Code instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Code other) {
        return other != null && this.id.equals(other.id);
    }

    /**
     * This method checks if an instance of Code is equal to another object.
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

        Code other = (Code) o;

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
