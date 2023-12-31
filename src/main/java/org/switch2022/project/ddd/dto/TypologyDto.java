package org.switch2022.project.ddd.dto;

import java.util.Objects;

public class TypologyDto {
    /**
     * Attributes
     */
    public final String typologyId;
    public final String typologyName;

    /**
     * Constructor
     */
    public TypologyDto(String typologyId, String typologyName) {
        this.typologyId = typologyId;
        this.typologyName = typologyName.toLowerCase().trim();
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param o typologyDto to compare to.
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
        TypologyDto that = (TypologyDto) o;
        return Objects.equals(typologyName, that.typologyName) && Objects.equals(typologyId, that.typologyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyName);
    }
}
