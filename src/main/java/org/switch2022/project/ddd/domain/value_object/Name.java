package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class Name implements ValueObject<Name> {
    private String name;

    /**
     * Constructor.
     *
     * @param projectName Name of the project.
     */
    public Name(final String projectName) {
        Validate.notNullOrEmptyOrBlank(projectName, "project name");

        this.name = projectName.toLowerCase();
    }

    /**
     * Getter method for the attribute: name
     *
     * @return String representation of the project name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method checks if two instances of Name are equal by comparing the value of the attribute name.
     *
     * @param other Name instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Name other) {
        return other != null && this.name.equals(other.name);
    }

    /**
     * This method checks if an instance of Name is equal to another object.
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

        Name other = (Name) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
