package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class Description implements ValueObject<Description> {
    private String description;

    /**
     * Constructor.
     *
     * @param projectDescription Description of the project.
     */
    public Description(final String projectDescription) {
        Validate.notNullOrEmptyOrBlank(projectDescription, "project name");

        this.description = projectDescription.toLowerCase();
    }

    /**
     * Getter method for the attribute: description.
     *
     * @return String representation of the project description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method checks if two instances of Description are equal by comparing the value of the attribute description.
     *
     * @param other Description instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Description other) {
        return other != null && this.description.equals(other.description);
    }

    /**
     * This method checks if an instance of Description is equal to another object.
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

        Description other = (Description) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
