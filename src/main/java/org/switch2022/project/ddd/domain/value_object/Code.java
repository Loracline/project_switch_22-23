package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class Code implements ValueObject<Code> {
    private String code;

    /**
     * Constructor.
     *
     * @param projectCode project code.
     */
    public Code(final String projectCode) {
        Validate.notNullOrEmptyOrBlank(projectCode, "project code");

        this.code = projectCode.toLowerCase();
    }

    /**
     * Getter method for the attribute: code
     *
     * @return String representation of the project code.
     */
    public String getCode() {
        return code;
    }

    /**
     * This method checks if two instances of Code are equal by comparing the value of the attribute code.
     *
     * @param other Code instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Code other) {
        return other != null && this.code.equals(other.code);
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
        return code.hashCode();
    }
}
