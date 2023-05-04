package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

public class PercentageOfAllocation implements ValueObject<PercentageOfAllocation> {

    private final float percentageAllocation;

    /**
     * Constructor.
     *
     * @param percentageOfAllocation the id of the PercentageOfAllocation.
     */
    public PercentageOfAllocation(float percentageOfAllocation) {
        if (isPercentageOfAllocationValid(percentageOfAllocation)) {
            this.percentageAllocation = percentageOfAllocation;
        } else {
            throw new InvalidInputException("Percentage of Allocation must be greater than 0% and " +
                "less or equal than 100%");
        }
    }

    /**
     * This method validates the percentage of allocation of an Account Allocated To a
     * Project.
     *
     * @return <code>true</code> if the percentage of allocation of an Account to a Project is valid, and <code>false</code> otherwise.
     */
    private boolean isPercentageOfAllocationValid(float percentageOfAllocation) {
        final float MAXIMUM_PERCENTAGE = 100;
        final float MINIMUM_PERCENTAGE = 0;
        return percentageOfAllocation > MINIMUM_PERCENTAGE && percentageOfAllocation <= MAXIMUM_PERCENTAGE;
    }

    /**
     * This method checks if two instances of PercentageOfAllocation are equal by comparing the value of the attribute
     * percentageOfAllocation.
     *
     * @param other PercentageOfAllocation instance to compare with.
     * @return <code>true</code> if the two instances have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(PercentageOfAllocation other) {
        Validate.notNull(other,"Percentage of Allocation can not be null");
        return Float.compare(this.percentageAllocation, other.percentageAllocation) == 0;
    }

    /**
     * This method checks if an instance of PercentageOfAllocation is equal to another object.
     *
     * @param other object to compare with.
     * @return <code>true</code> if the two instances are equals, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other) {
        Validate.notNull(other, "The Object To Compare must not be null");
        if (this == other) {
            return true;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        return sameValueAs((PercentageOfAllocation) other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(percentageAllocation);
    }
}
