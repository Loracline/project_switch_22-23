package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class UsText implements ValueObject<UsText> {
    private String userStoryText;

    /**
     * Constructor.
     *
     * @param usText user story text.
     */
    public UsText(final String usText) {
        Validate.notNullOrEmptyOrBlank(usText, "user story text");

        this.userStoryText = usText.toLowerCase();
    }

    /**
     * Getter method for the attribute: useStoryText
     *
     * @return String representation of the user story text.
     */
    public String getUserStoryText() {
        return userStoryText;
    }

    /**
     * This method checks if two instances of UsText are equal by comparing the value of the attribute user story
     * number.
     *
     * @param other UsText instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(UsText other) {
        return other != null && this.userStoryText.equals(other.userStoryText);
    }

    /**
     * This method checks if an instance of UsText is equal to another object.
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

        UsText other = (UsText) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return userStoryText.hashCode();
    }
}
