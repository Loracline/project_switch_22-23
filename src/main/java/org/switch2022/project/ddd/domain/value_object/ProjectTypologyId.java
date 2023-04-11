package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class ProjectTypologyId implements ValueObject<ProjectTypologyId> {

    private final String typologyId;

    /**
     * Constructor.
     *
     * @param idNumber the id number of the customer.
     */
    public ProjectTypologyId(final Number idNumber) {
        Validate.notNull(idNumber, "The project typology number must not be null");
        Validate.notNegative(idNumber, "project typology number");

        this.typologyId = String.format("PT%03d", idNumber).toLowerCase();

    }

    /**
     * Getter method for the attribute: typologyId.
     *
     * @return String representation of the project typology ID.
     */
    public String getProjectTypologyId() {
        return typologyId;
    }

    /**
     * This method checks if two instances of ProjectTypologyId are equal by comparing the value of the attribute
     * typology ID.
     *
     * @param other ProjectTypologyId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(ProjectTypologyId other) {
        return other != null && this.typologyId.equals(other.typologyId);
    }

    /**
     * This method checks if an instance of ProjectTypologyId is equal to another object.
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

        ProjectTypologyId other = (ProjectTypologyId) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return typologyId.hashCode();
    }
}
