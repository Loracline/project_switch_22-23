package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SprintPrimitiveTypesDtoTest {

    @DisplayName("DTO is created successfully")
    @Test
    void ensureDtoIsCreatedSuccessfully() {
        // Arrange
        String id = "p001_s001";
        String number = "s001";
        String status = "CLOSED";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        // Act
        SprintPrimitiveTypesDto result = new SprintPrimitiveTypesDto(id, number, status, startDate, endDate);

        // Assert
        assertNotNull(result);
    }
}