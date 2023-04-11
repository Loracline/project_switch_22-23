package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class SprintDuration implements ValueObject<SprintDuration> {
    private final Integer duration;
    private static final int MIN_SPRINT_DURATION = 0;
    private static final int MAX_SPRINT_DURATION = 4;

    /**
     * Constructor.
     *
     * @param duration of sprints in the project.
     */
    public SprintDuration(final Number duration) {
        Validate.notNull(duration, "sprint duration");
        Validate.withinInterval(MIN_SPRINT_DURATION, MAX_SPRINT_DURATION, duration, "sprint duration");
        this.duration = duration.intValue();
    }

    /**
     * Getter method for the attribute: sprintDuration.
     *
     * @return the duration of sprints in the project.
     */
    public int getSprintDuration() {
        return duration;
    }

    /**
     * This method checks if two instances of SprintDuration are equal by comparing the value of the attribute
     * sprintDuration.
     *
     * @param other SprintDuration instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(SprintDuration other) {
        return other != null && this.duration.equals(other.duration);
    }

    /**
     * This method checks if an instance of SprintDuration is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SprintDuration other = (SprintDuration) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return duration.hashCode();
    }
}
