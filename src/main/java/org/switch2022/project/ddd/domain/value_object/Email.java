package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class Email implements ValueObject<Email> {
    private final String email;

    /**
     * Constructor.
     *
     * @param email of the account.
     */
    public Email(final String email) {
        Validate.notNullOrEmptyOrBlank(email, "email");
        Validate.isEmailValid(email);
        this.email = email.toLowerCase();
    }

    /**
     * Getter method for the attribute: email
     *
     * @return String representation of the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method checks if two instances of Email are equal by comparing the value of the attribute email.
     *
     * @param other Email instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.email.equals(other.email);
    }

    /**
     * This method checks if an instance of Email is equal to another object.
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

        Email other = (Email) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
