package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;
import java.util.Objects;

public class PhoneNumber implements ValueObject<PhoneNumber> {
    private final int value;

    /**
     * Constructor.
     *
     * @param phoneNumber of the project.
     */
    public PhoneNumber(final int phoneNumber) {
        Validate.notNull(phoneNumber, "The number must not be null");
        Validate.notNegative(phoneNumber, "number");
        Validate.isPhoneNumberValid(String.valueOf(phoneNumber));
        this.value = phoneNumber;
    }

    /**
     * Getter method for the attribute: phoneNumber.
     *
     * @return phoneNumber of the account.
     */
    public int getPhoneNumber() {
        return value;
    }

    /**
     * This method checks if two instances of phoneNumber are equal by comparing the value of the attribute phoneNumber.
     *
     * @param other PhoneNumber instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(PhoneNumber other) {
        return other != null && this.value == (other.value);
    }

    /**
     * This method checks if an instance of PhoneNumber is equal to another object.
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

        PhoneNumber other = (PhoneNumber) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
