package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorCreationDtoTest {
    /**
     * Ensure Business Sector Creation Dto works.
     */
    @Test
    public void ensureBusinessSectorCreationDto() {
        // Arrange
        String name = "AAA";

        // Act
        BusinessSectorCreationDto dto = new BusinessSectorCreationDto(name);

        // Assert
        assertEquals(name, dto.name);
    }
}