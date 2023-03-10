package org.switch2022.project.model;

import org.switch2022.project.utils.Effort;
import org.switch2022.project.utils.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class UserStory was built to create and manager new userStories.
 * An UserStory is defined by: an userStoryNumber, which is it unique ID, a
 * projectCode,an actor, an userStoryText,
 * an effort, a status and a list of  acceptanceCriteria.
 */
public class UserStory {
    /**
     * Attributes
     */
    private final String userStoryNumber;
    private String actor;
    private String userStoryText;
    private List<String> acceptanceCriteria = new ArrayList<>();
    private Effort effort = Effort.ONE;
    private Status status = Status.PLANNED;

    /**
     * Constructor
     * It creates an userStory with the defining attribute: userStoryNumber
     */
    private UserStory(String userStoryNumber) {
        this.userStoryNumber = userStoryNumber.toLowerCase().trim();
    }

    /**
     * This method creates a new UserStory
     *
     * @param userStoryNumber,actor,userStoryText; When an userStory is instantiated,
     *                                             its default status is PLANNED, its
     *                                             default effort is 1 and hav an empty
     *                                             list of acceptanceCriteria.
     * @return userStory
     */
    public static UserStory createUserStory(String userStoryNumber, String actor,
                                            String userStoryText) {
        UserStory userStory = new UserStory(userStoryNumber);
        userStory.setActor(actor);
        userStory.setUserStoryText(userStoryText);
        return userStory;
    }

    /**
     * This method checks if two instances of UserStory are equal by comparing
     * the userStoryNumber.
     *
     * @param o userStory instance to compare with.
     * @return TRUE if the two have the same attribute, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        UserStory userStory = (UserStory) o;
        return Objects.equals(userStoryNumber,
                userStory.userStoryNumber.toLowerCase().trim());
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
     * Getter method for the attribute: userStoryNumber
     */
    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    /**
     * Getter method for the attribute: actor
     */
    public String getActor() {
        return actor.toLowerCase();
    }

    /**
     * Setter method for the attribute: actor
     */
    private void setActor(String actor) {
        this.actor = actor;
    }

    /**
     * Getter method for the attribute: userStoryText
     */
    public String getUserStoryText() {
        return userStoryText.toLowerCase().trim();
    }

    /**
     * Setter method for the attribute: userStoryText
     */
    private void setUserStoryText(String userStoryText) {
        this.userStoryText = userStoryText;
    }

    /**
     * Getter method for the attribute: status
     */
    public String getStatus() {
        return status.toString();
    }

    /**
     * Setter method for the attribute: status
     */
    public void setStatus(String status) {
        this.status.valueOf(status);
    }

    /**
     * Getter method for the attribute: effort
     */
    Effort getEffort() {
        return effort;
    }

    /**
     * Setter method for the attribute: effort
     */
    void setEffort(Effort effort) {
        this.effort = effort;
    }

    /**
     * This method verifies if an userStory is the one intended through it
     * userStoryNumber.
     *
     * @param userStoryNumber of the seeked userStory.
     * @return TRUE if userStory has given userStoryNumber, and FALSE otherwise.
     */
    public boolean hasUserStoryNumber(String userStoryNumber) {
        return this.userStoryNumber.equalsIgnoreCase(userStoryNumber);
    }
}
