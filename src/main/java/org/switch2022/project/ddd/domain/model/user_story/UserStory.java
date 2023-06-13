package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private final List<AcceptanceCriteria> acceptanceCriteria;
    private final Code projectCode;
    private UsNumber usNumber;
    private Actor actor;
    private UsText usText;
    private Status status;

    /**
     * Constructor
     * It creates an userStory using its identifier: usId  and your minimals attributs.
     * Attributs: usNumber, actor, usText, accptaneCriteria.
     */
    protected UserStory(Code projectCode, UsNumber usNumber, Actor actor, UsText usText,
                        List<AcceptanceCriteria> acceptanceCriteria) {
        Validate.notNull(projectCode, "User Story's project code can't be null.");
        Validate.notNull(usNumber, "User Story's User Story Number can't be null.");
        Validate.notNull(actor, "User Story's actor can't be null.");
        Validate.notNull(usText, "User Story's Text can't be null");
        Validate.notNull(acceptanceCriteria, "User Story's Acceptance Criteria can't be null.");
        this.projectCode = projectCode;
        this.usNumber = usNumber;
        this.actor = actor;
        this.usText = usText;
        this.status = Status.PLANNED;
        this.acceptanceCriteria = acceptanceCriteria;
        this.usId = new UsId(projectCode.getCode(), usNumber.getUserStoryNumber());
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
     * This getter method returns a String with User Story ID.
     */
    public String getUsId() {
        return usId.getUserStoryId();
    }


    /**
     * This getter method returns a String with User Story status.
     */
    public String getStatus() {return status.getStatus();}

    /**
     * This protected method sets the status of the userStory.
     *
     * @param status of the User Story to be altered.
     */
    public void changeStatus(Status status) {
        this.status = status;
    }

    /**
     * This getter method returns a String with the User Story Text.
     */
    public String getUsText() {
        return usText.getUserStoryText();
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
     * This getter method returns a String with the User Story Number.
     */
    public String getUsNumber() {
        return usNumber.getUserStoryNumber();
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
     * This getter returns the Acceptance Criteria of a user story.
     */
    public List<String> getAcceptanceCriteria() {
        List<String> listOfAcceptanceCriteria = new ArrayList<>();
        for (AcceptanceCriteria ac : this.acceptanceCriteria) {
            listOfAcceptanceCriteria.add(ac.getAcceptanceCriteria());
        }
        return Collections.unmodifiableList(listOfAcceptanceCriteria);
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

    /**
     * This method verifies if the UserStory has the given status
     *
     * @param status of the User Story.
     * @return TRUE if Status has the given Status, and FALSE otherwise.
     */
    public boolean hasStatus(Status status) {
        return status.equals(this.status);
    }

    /**
     * The method verify if the UserStory has the given usId.
     *
     * @param usId of the User Story.
     * @return TRUE if User Story has the given usId, and FALSE otherwise.
     */
    public boolean hasUsId(UsId usId) {
        return usId.equals(this.usId);
    }

    /**
     * This getter method returns a String with the actor.
     */
    public String getActor() {
        return actor.getActor();
    }

    /**
     * This getter method returns a String with the project code.
     */

    public String getProjectCode() {
        return projectCode.getCode();
    }

    /**
     * This method verifies if the userStory has the given projectCode
     */

    public boolean hasProjectCode (Code projectCode){
        return projectCode.equals(this.projectCode);
    }
}

