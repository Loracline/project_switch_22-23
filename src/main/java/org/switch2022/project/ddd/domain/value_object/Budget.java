package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

import java.math.BigDecimal;

public class Budget implements ValueObject<Budget> {
    private BigDecimal budget;

    /**
     * Constructor.
     *
     * @param budget of the project.
     */
    public Budget(final BigDecimal budget) {
        Validate.notNull(budget, "budget");
        Validate.notNegative(budget, "budget");
        this.budget = budget;
    }

    /**
     * Getter method for the attribute: budget.
     *
     * @return budget of the project.
     */
    public BigDecimal getBudget() {
        return budget;
    }

    /**
     * This method checks if two instances of Budget are equal by comparing the value of the attribute budget.
     *
     * @param other Budget instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Budget other) {
        return other != null && this.budget.equals(other.budget);
    }

    /**
     * This method checks if an instance of Budget is equal to another object.
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

        Budget other = (Budget) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return budget.hashCode();
    }
}
