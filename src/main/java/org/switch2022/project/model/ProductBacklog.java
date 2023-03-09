package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    /**
     * This method adds a new User Story to the userStories list if the User Story
     * doesn't already exist.
     *
     * @param userStory the new User Story to be added
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise.
     */
    public boolean addUserStory(UserStory userStory) {
        boolean result = true;
        if (userStories.contains(userStory)) {
            result = false;
        } else {
            userStories.add(userStory);
        }
        return result;
    }

    /**
    * This method returns a User Story from the Product Backlog with a given User Story number.
     * @param userStoryNumber of the User Story one searches for.
     * @return an Optional with a User Story.
     */


    public Optional<UserStory> getUserStoryByNumber(String userStoryNumber) {
        int i = 0;
        UserStory userStory = null;
        while (i < userStories.size() && userStory == null) {
            if (userStories.get(i).hasUserStoryNumber(userStoryNumber)) {
                userStory = userStories.get(i);
            }
            i++;
        }
        return Optional.ofNullable(userStory);
    }
}
