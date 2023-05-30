package org.switch2022.project.ddd.dto;

import java.util.Objects;

/**
 * Represents a business sector data transfer object.
 * This class contains information about the business sector such as its name.
 */
public class BusinessSectorDto {
    /**
     * Attributes
     */
    public final String name;
    public final String id;

    /**
     * Constructor to instantiate a new business sector dto.
     *
     * @param name the name of the business sector
     * @param id
     */
    public BusinessSectorDto(String name, String id) {
        this.name = name.toLowerCase().trim();
        this.id = id.toLowerCase().trim();
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
        BusinessSectorDto that = (BusinessSectorDto) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(name, id);
    }
}
