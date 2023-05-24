package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

public class CostPerHour implements ValueObject<CostPerHour> {
    private final float cost;

    /**
     * Constructor.
     *
     * @param cost the id of the Cost Per Hour.
     */
    public CostPerHour(final float cost) {
        Validate.notNegative(cost, "Cost Per Hour");
        this.cost = cost;
    }

    /**
     * This method checks if two instances of CostPerHour are equal by comparing the value of the attribute cost.
     *
     * @param other CostPerHour instance to compare with.
     * @return <code>true</code> if the two instances have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(CostPerHour other) {
        return Float.compare(this.cost, other.cost) == 0;
    }

    /**
     * This method checks if an instance of CostPerHour is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two instances are equals, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        Validate.notNull(o,"The Object To Compare must not be null");
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        return sameValueAs((CostPerHour) o);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }

    /**
     * This method returns the String cost.
     *
     * @return the cost of the value object.
     */
    public float getCost() {
        return cost;
    }
}


