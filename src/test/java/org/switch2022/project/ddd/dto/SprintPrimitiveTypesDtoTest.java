package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintPrimitiveTypesDtoTest {

    @DisplayName("Sprint number is the expected")
    @Test
    void ensureSprintNumberIsTheRetrievedSuccessfully() {
        // Arrange
        String number = "S001";
        String status = "open";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        SprintPrimitiveTypesDto dto = new SprintPrimitiveTypesDto(number, status, startDate, endDate);

        // Act
        String result = dto.getNumber();

        // Assert
        assertEquals(number, result);
    }

    @DisplayName("Sprint status is the expected")
    @Test
    void ensureSprintStatusIsRetrievedSuccessfully() {
        // Arrange
        String number = "S001";
        String status = "open";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        SprintPrimitiveTypesDto dto = new SprintPrimitiveTypesDto(number, status, startDate, endDate);

        // Act
        String result = dto.getStatus();

        // Assert
        assertEquals(status, result);
    }

    @DisplayName("Start date is the expected")
    @Test
    void ensureStartDateIsRetrievedSuccessfully() {
        // Arrange
        String number = "S001";
        String status = "open";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        SprintPrimitiveTypesDto dto = new SprintPrimitiveTypesDto(number, status, startDate, endDate);

        // Act
        String result = dto.getStartDate();

        // Assert
        assertEquals(startDate, result);
    }

    @DisplayName("End date is the expected")
    @Test
    void ensureEndDateIsRetrievedSuccessfully() {
        // Arrange
        String number = "S001";
        String status = "open";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        SprintPrimitiveTypesDto dto = new SprintPrimitiveTypesDto(number, status, startDate, endDate);

        // Act
        String result = dto.getEndDate();

        // Assert
        assertEquals(endDate, result);
    }
}