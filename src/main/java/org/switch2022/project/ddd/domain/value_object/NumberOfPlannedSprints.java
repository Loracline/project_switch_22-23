package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class NumberOfPlannedSprints implements ValueObject<NumberOfPlannedSprints> {
    private final Integer numberOfSprints;

    /**
     * Constructor.
     *
     * @param number of planned sprints of the project.
     */
    public NumberOfPlannedSprints(final Number number) {
        Validate.notNull(number, "number of planned sprints");
        Validate.notNegative(number, "number of planned sprints");
        this.numberOfSprints = number.intValue();
    }

    /**
     * Getter method for the attribute: numberOfPlannedSprints.
     *
     * @return number of planned sprints of the project.
     */
    public int getNumberOfPlannedSprints() {
        return numberOfSprints;
    }

    /**
     * This method checks if two instances of NumberOfPlannedSprints are equal by comparing the value of the attribute
     * numberOfPlannedSprints.
     *
     * @param other NumberOfPlannedSprints instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(NumberOfPlannedSprints other) {
        return other != null && this.numberOfSprints.equals(other.numberOfSprints);
    }

    /**
     * This method checks if an instance of NumberOfPlannedSprints is equal to another object.
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

        NumberOfPlannedSprints other = (NumberOfPlannedSprints) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return numberOfSprints.hashCode();
    }
}
