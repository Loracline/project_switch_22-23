package org.switch2022.project.ddd.dto;

import java.util.Objects;

/**
 * Represents a sprint creation data transfer object.
 * This class contains information about the sprint such as the project code,
 * and start date.
 */
public class BusinessSectorCreationDto {

    /**
     * Attributes
     */
    public final String name;

    /**
     * Constructor to instatiate a new business sector dto.
     *
     * @param name the name of the
     */

    public BusinessSectorCreationDto(String name) {
        this.name = name;
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param o accountDto to compare to.
     * @return TRUE if equal, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        BusinessSectorCreationDto that = (BusinessSectorCreationDto) o;
        return Objects.equals(name, that.name);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}

