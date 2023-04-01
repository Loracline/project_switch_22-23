package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class UsId implements ValueObject<UsId> {
    private String userStoryId;

    /**
     * Constructor.
     *
     * @param projectCode project code.
     * @param usNumber    user story number.
     */
    public UsId(final String projectCode, final String usNumber) {
        Validate.notNullOrEmptyOrBlank(projectCode, "project code");
        Validate.notNullOrEmptyOrBlank(usNumber, "user story number");

        this.userStoryId = (projectCode + "_" + usNumber).toLowerCase();
    }

    /**
     * Getter method for the attribute: userStoryId.
     *
     * @return String representation of the user story ID.
     */
    public String getUserStoryId() {
        return userStoryId;
    }

    /**
     * This method checks if two instances of UsId are equal by comparing the value of the attribute user story id.
     *
     * @param other UsId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(UsId other) {
        return other != null && this.userStoryId.equals(other.userStoryId);
    }

    /**
     * This method checks if an instance of UsId is equal to another object.
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

        UsId other = (UsId) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return userStoryId.hashCode();
    }

}
