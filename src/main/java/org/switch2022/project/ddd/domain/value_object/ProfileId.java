package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class ProfileId implements ValueObject<ProfileId> {

    private final String id;

    /**
     * Constructor.
     *
     * @param idNumber the id number of the profile.
     */

    public ProfileId(final Number idNumber) {
        Validate.notNull(idNumber, "The profile number must not be null");
        Validate.notNegative(idNumber, "profile number");

        this.id = String.format("PR%03d", idNumber).toLowerCase();
    }

    /**
     * *Getter method for the attribute: profileId.
     *
     * @return String representation of the profile ID.
     */

    public String getProfileId() {
        return id;
    }

    /**
     * This method checks if two instances of ProfileId are equal by comparing
     * the value of the attribute profile ID.
     *
     * @param other ProfileId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value,
     * and <code>false</code> otherwise.
     */

    @Override
    public boolean sameValueAs(ProfileId other) {
        return other != null && this.id.equals(other.id);
    }

    /**
     * This method checks if an instance of ProfileId is equal to another object.
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

        ProfileId other = (ProfileId) o;

        return sameValueAs(other);

    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
