package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class UsNumber implements ValueObject<UsNumber> {
    private final String userStoryNumber;

    /**
     * Constructor.
     *
     * @param usNumber user story number.
     */
    public UsNumber(final String usNumber) {
        Validate.notNullOrEmptyOrBlank(usNumber, "user story number");
        this.userStoryNumber = String.format("us%03d", Integer.parseInt(usNumber));
    }

    /**
     * Getter method for the attribute: useStoryNumber
     *
     * @return String representation of the user story number.
     */
    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    /**
     * This method checks if two instances of UsNumber are equal by comparing the value of the attribute user story
     * number.
     *
     * @param other UsNumber instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(UsNumber other) {
        return other != null && this.userStoryNumber.equals(other.userStoryNumber);
    }

    /**
     * This method checks if an instance of UsNumber is equal to another object.
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

        UsNumber other = (UsNumber) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return userStoryNumber.hashCode();
    }
}
