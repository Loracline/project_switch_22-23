package org.switch2022.project.model;

import org.switch2022.project.utils.Effort;
import org.switch2022.project.utils.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class UserStory was built to create and manager new userStories.
 * An UserStory is defined by: an userStoryNumber, which is it unique ID, a projectCode,an actor, an userStoryText,
 * an effort, a status and a list of  acceptanceCriteria.
 */
public class UserStory {
    /**
     * Attributes
     */
    private final String userStoryNumber;
    private final String actor;
    private final String userStoryText;
    private List<String> acceptanceCriteria;
    private Effort effort;
    private Status status;

    /**
     * Constructor
     * When an userStory is instantiated, its default status is PLANNED, its
     * default effort is 1 and an empty list of acceptanceCriteria.
     */

    public UserStory(String userStoryNumber, String actor, String userStoryText) {
        this.userStoryNumber = userStoryNumber.toLowerCase().trim();
        this.actor = actor;
        this.userStoryText = userStoryText;
        this.acceptanceCriteria = new ArrayList<>();
        this.effort = Effort.ONE;
        this.status = Status.PLANNED;
    }

    /**
     * This method checks if two instances of UserStory are equal by comparing
     * the userStoryNumber.
     *
     * @param o Account instance to compare with.
     * @return TRUE if the two have the same attributes, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        UserStory userStory = (UserStory) o;
        return Objects.equals(userStoryNumber, userStory.userStoryNumber.toLowerCase().trim());
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userStoryNumber);
    }

    /**
     * This method verifies if an userStory is the one intended through it userStoryNumber.
     *
     * @param userStoryNumber of the seeked userStory.
     * @return TRUE if userStory has given userStoryNumber, and FALSE otherwise.
     */

    public boolean hasUserStoryNumber(String userStoryNumber) {
        return this.userStoryNumber.equalsIgnoreCase(userStoryNumber);
    }
}
