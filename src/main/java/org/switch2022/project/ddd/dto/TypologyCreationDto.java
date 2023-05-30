package org.switch2022.project.ddd.dto;

import java.util.Objects;

public class TypologyCreationDto {
    /**
     * Attributes
     */
    public final String typologyName;

    /**
     * Constructor to instatiate a new typology dto.
     *
     * @param typologyName the name of the
     */
    public TypologyCreationDto(String typologyName) {
        this.typologyName = typologyName;
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param o typologyCreationDto to compare to.
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
        TypologyCreationDto that = (TypologyCreationDto) o;
        return Objects.equals(typologyName, that.typologyName);
    }

    /**
     * Retrieves the name of the typology.
     *
     * @return The name of the typology as a String.
     */
    public String getTypologyName() {
        return typologyName;
    }
}
