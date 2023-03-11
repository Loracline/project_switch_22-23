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
    private final String actor;
    private final String userStoryText;
    private List<String> acceptanceCriteria = new ArrayList<>();
    private Effort effort = Effort.ONE;
    private Status status = Status.PLANNED;

    /**
     * Constructor
     * It creates an userStory using the UserStoryBuilder;
     */
    private UserStory(UserStoryBuilder builder) {

        this.userStoryNumber = builder.userStoryNumber;
        this.actor = builder.actor;
        this.userStoryText = builder.userStoryText;
    }

    /**
     * This class applies the builderPattern to create a new UserStory
     */

    public static class UserStoryBuilder {

        private final String userStoryNumber;
        private String actor;
        private String userStoryText;

        /**
         * This method creates a userStory the userStoryNumber
         *
         * @param userStoryNumber
         */
        public UserStoryBuilder(String userStoryNumber) {
            this.userStoryNumber = userStoryNumber.toLowerCase().trim();
        }

        /**
         * This method set the actor of the userStory
         *
         * @param actor
         */
        public UserStoryBuilder setActor(String actor) {
            this.actor = actor;
            return this;
        }

        /**
         * This method set the userStoryText of the userStory
         *
         * @param userStoryText
         */
        public UserStoryBuilder setUserStoryText(String userStoryText) {
            this.userStoryText = userStoryText;
            return this;
        }

        /**
         * This method creates de userStory with the chosen attributes
         */
        public UserStory build() {
            return new UserStory(this);
        }


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
     * Getter method for the attribute: userStoryText
     */
    public String getUserStoryText() {
        return userStoryText;
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
     * This method returns the effort of the userStory.
     *
     * @return the effort associated to the userStory.
     */
    Effort getEffort() {
        return effort;
    }

    /**
     * This method sets the effort for the userStory.
     *
     * @param effort of the userStory.
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
