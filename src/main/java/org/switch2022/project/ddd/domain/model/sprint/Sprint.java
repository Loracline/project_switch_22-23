package org.switch2022.project.ddd.domain.model.sprint;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Represents a short period of time defined by a Sprint Number, Period and Sprint
 * Backlog.
 */

public class Sprint implements Entity<Sprint> {

    private final SprintNumber sprintNumber;

    private final Period period;

    private final List<UserStoryInSprint> userStoriesInSprint;


    /**
     * Constructor
     *
     * @param sprintNumber the number of the sprint.
     * @param period the duration of the sprint.
     */

    protected Sprint(int sprintNumber, Period period) {
        Validate.notNull(period,"Period cannot be null");
        Validate.notNegative(sprintNumber,"Sprint Number");
        this.sprintNumber = new SprintNumber(sprintNumber);
        this.period = period;
        this.userStoriesInSprint = new ArrayList<>();
    }

    /**
     * This method checks if two instances of Sprint are equal by comparing
     * the sprintNumber.
     *
     * @param o userStory instance to compare with.
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
       Sprint sprint = (Sprint) o;
        return sprintNumber.equals(sprint.sprintNumber);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(sprintNumber);
    }

    /**
     * This method checks if two instances of Sprint are equal by comparing the value of the
     * attribute Sprint Number.
     *
     * @param other Sprint instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE otherwise.
     */
    @Override
    public boolean sameIdentityAs(Sprint other) {
        return this.sprintNumber.equals(other.sprintNumber);
    }
}
