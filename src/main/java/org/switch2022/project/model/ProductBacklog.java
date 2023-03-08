package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class ProductBacklog is built to access and manipulate the set of User Stories
 * of a given Project.
 */

public class ProductBacklog {
    /**
     * Attributes
     */
    private final List<UserStory> userStories = new ArrayList<>();

    /**
     * This method checks if two instances of ProductBacklog are equal by comparing
     * the userStories attribute (the list of user stories).
     *
     * @param o ProductBacklog instance to compare with.
     * @return TRUE if the two have the same attributes, and FALSE otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductBacklog that = (ProductBacklog) o;
        return userStories.equals(that.userStories);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(userStories);
    }
}
