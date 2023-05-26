package org.switch2022.project.ddd.dto;

public class TypologyCreationDto {
    /**
     * Attributes
     */
    public String typologyName;
    /**
     * Constructor to instatiate a new typology dto.
     * @param typologyName the name of the
     */
    public TypologyCreationDto(String typologyName) {
        this.typologyName = typologyName;
    }
}
