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
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
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
     * This getter method returns the User Story ID.
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
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * This getter method returns the User Story Text.
     */
    public UsText getUsText() {
        return usText;
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
     * This getter method returns the User Story Number.
     */
    public UsNumber getUsNumber() {
        return usNumber;
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
     * This protected method sets the acceptance criteria of the userStory.
     *
     * @param acceptanceCriteria of the User Story to be added.
     */
    public void setAcceptanceCriteria(AcceptanceCriteria acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    /**
     * This getter returns the Acceptance Criteria of a user story.
     */
    public AcceptanceCriteria getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    /**
     * This method sets the relevant attributes for a user story to be in a valid state.
     * @param usNumber of the user story.
     * @param usText of the user story.
     * @param actor of the user story.
     */
    protected void setValidUserStory(UsNumber usNumber, UsText usText, Actor actor) {
        setUsNumber(usNumber);
        setUsText(usText);
        setActor(actor);
    }

    /**
     * This method verifies if the User Story has the given User Story Number.
     *
     * @param usNumber of the User Story.
     * @return TRUE if the User Story has the given Us Number and FALSE otherwise.
     */
    public boolean has(UsNumber usNumber) {
        return usNumber.equals(this.usNumber);
    }
}
