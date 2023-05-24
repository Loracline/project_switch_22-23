package org.switch2022.project.ddd.domain.model.sprint;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Effort;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;


/**
 * The entity UserStoryInSprint represents the relation of each User Story included in a
 * Sprint and track its effort.
 */
public class UserStoryInSprint implements Entity<UserStoryInSprint> {

    /**
     * Attributes
     */

    private final UsId usId;
    private Effort effort;

    /**
     * Constructor
     *
     * @param usId   the id of a User Story from a given Project.
     * @param effort the Fibonacci sequence to be used for estimation the effort for User Stories
     *               in Sprint.
     */
    protected UserStoryInSprint(UsId usId, Effort effort) {
        Validate.notNull(usId, "UsId cannot be null");
        this.usId = usId;
        this.effort = effort;
    }

    /**
     * Constructor with only usId, sets effort as one.
     * @param usId of the user story.
     */
    protected UserStoryInSprint(UsId usId) {
        Validate.notNull(usId, "UsId cannot be null");
        this.usId = usId;
        this.effort = Effort.ONE;
    }

    /**
     * This method checks if two instances of UserStoryInSprint are equal by comparing
     * the usId.
     *
     * @param o userStoryInSprint instance to compare with.
     * @return TRUE if the two have the same attribute, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryInSprint userStoryInSprint = (UserStoryInSprint) o;
        return usId.equals(userStoryInSprint.usId);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(usId);
    }

    /**
     * This method checks if two instances of UserStoryInSprint are equal by comparing the value of
     * the attribute UsId.
     *
     * @param other UserStoryInSprint instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE otherwise.
     */
    @Override
    public boolean sameIdentityAs(UserStoryInSprint other) {
        return other != null && this.usId.equals(other.usId);
    }

    /**
     * Getter method for the attribute: usId.
     *
     * @return a String with the UserStoryInSprint id.
     */
    public UsId getUsId() {
        return this.usId;
    }

    /**
     * This method returns the effort of the userStory.
     *
     * @return the effort associated to the userStory.
     */
    public int getEffort() {
        return effort.getEffortValue();
    }

    /**
     * This method sets the effort of the userStory.
     *
     * @param effortEstimate of the User Story to be set.
     */
    public boolean changeEffort(int effortEstimate) {
        for (Effort effort : Effort.values()) {
            if (effort.getEffortValue() == effortEstimate) {
                this.effort = effort;
                return true;
            }
        }
        return false;
    }
}
