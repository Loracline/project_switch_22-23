package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

/**
 * The class UserStory was built to create and manage new userStories.
 * An UserStory is defined by: an userStoryNumber, which is it unique ID, a
 * projectCode,an actor, an userStoryText,
 * an effort and a status.
 */

public class UserStory implements Entity<UserStory> {
    /**
     * Attributes
     */
    private final UsId usId;
    private UsNumber usNumber;
    private Actor actor;
    private UsText usText;
    private Status status;
    private AcceptanceCriteria acceptanceCriteria;
    private Effort effort;

    /**
     * Constructor
     * It creates an userStory using its identifier: usId.
     */

    protected UserStory(final UsId usId) {
        Validate.notNull(usId, "User Story's ID can't be null.");
        this.usId = usId;
    }

    /**
     * This method checks if two instances of User Story are equal by comparing the
     * value of the attribute user story id.
     *
     * @param other UserStory instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE
     * otherwise.
     */

    @Override
    public boolean sameIdentityAs(UserStory other) {
        return this.usId.equals(other.usId);
    }

    /**
     * The method equals checks if the two objects are instances of the same objects
     * and if they  are equal by comparing the usId.
     *
     * @param o userStory instance to compare with.
     * @return TRUE if the two have the same attribute, and FALSE otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStory)) return false;
        UserStory userStory = (UserStory) o;
        return usId.equals(userStory.usId);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(usId);
    }

    /**
     * This getter method returns the User Story Id.
     */

    public UsId getUsId() {
        return usId;
    }

    /**
     * This getter method returns the User Story status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This protected method sets the status of the userStory.
     *
     * @param status of the User Story to be altered.
     */
    void setStatus(Status status) {
        this.status = status;
    }

    /**
     * This getter method returns the User Story effort.
     */
    public Effort getEffort() {
        return effort;
    }

    /**
     * This method sets the effort of the userStory.
     *
     * @param effortEstimate of the User Story to be set.
     */
    void setEffort(int effortEstimate) {
        for (Effort effort : Effort.values()) {
            if (effort.getEffortValue() == effortEstimate) {
                this.effort = effort;
                return;
            }
        }
        throw new RuntimeException("Effort estimate was not successful");
    }


    /**
     * This protected method sets the user story number of the userStory.
     *
     * @param usNumber of the User Story to create.
     */
    protected void setUsNumber(UsNumber usNumber) {
        this.usNumber = usNumber;
    }

    /**
     * This protected method sets the actor of the userStory.
     *
     * @param actor of the User Story to create.
     */
    protected void setActor(Actor actor) {
        this.actor = actor;
    }

    /**
     * This protected method sets the user story text of the userStory.
     *
     * @param usText of the User Story to create.
     */
    protected void setUsText(UsText usText) {
        this.usText = usText;
    }

    /**
     * This protected verifies if the instance os User Story has the given UsNumber.
     *
     * @param toCompare is the usNumber to compare with the usNumber of the instance.
     */
    public boolean has(String toCompare) {
        return this.usNumber.toString().equals(toCompare);
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "usId=" + usId +
                ", usNumber=" + usNumber +
                ", actor=" + actor +
                ", usText=" + usText +
                ", status=" + status +
                ", acceptanceCriteria=" + acceptanceCriteria +
                '}';
    }
}