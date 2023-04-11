package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class Actor implements ValueObject<Actor> {
    private final String designation;

    /**
     * Constructor.
     *
     * @param actor Actor.
     */
    public Actor(final String actor) {
        Validate.notNullOrEmptyOrBlank(actor, "actor");

        this.designation = actor.toLowerCase();
    }

    /**
     * Getter method for the attribute: actor
     *
     * @return String representation of the actor.
     */
    public String getActor() {
        return designation;
    }

    /**
     * This method checks if two instances of Actor are equal by comparing the value of the attribute actor.
     *
     * @param other Actor instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Actor other) {
        return other != null && this.designation.equals(other.designation);
    }

    /**
     * This method checks if an instance of Actor is equal to another object.
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

        Actor other = (Actor) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return designation.hashCode();
    }
}
