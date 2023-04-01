package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class AcceptanceCriteria implements ValueObject<AcceptanceCriteria> {
    private String acceptanceCriteria;

    /**
     * Constructor.
     *
     * @param acceptanceCriteria description of acceptance criteria.
     */
    public AcceptanceCriteria(final String acceptanceCriteria) {
        Validate.notNullOrEmptyOrBlank(acceptanceCriteria, "acceptance criteria");

        this.acceptanceCriteria = acceptanceCriteria.toLowerCase();
    }

    /**
     * Getter method for the attribute: acceptanceCriteria
     *
     * @return String representation of the acceptance criteria.
     */
    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    /**
     * This method checks if two instances of AcceptanceCriteria are equal by comparing the value of the attribute
     * acceptanceCriteria.
     *
     * @param other AcceptanceCriteria instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(AcceptanceCriteria other) {
        return other != null && this.acceptanceCriteria.equals(other.acceptanceCriteria);
    }

    /**
     * This method checks if an instance of AcceptanceCriteria is equal to another object.
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

        AcceptanceCriteria other = (AcceptanceCriteria) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return acceptanceCriteria.hashCode();
    }
}
