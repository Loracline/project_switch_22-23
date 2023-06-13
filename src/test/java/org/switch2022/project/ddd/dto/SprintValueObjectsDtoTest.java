package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SprintValueObjectsDtoTest {

    @DisplayName("Sprint number is the expected")
    @Test
    void ensureSprintNumberIsRetrievedSuccessfully() {
        // Arrange
        SprintNumber number = new SprintNumber(1);
        SprintStatus status = SprintStatus.CLOSED;
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(number, status, startDate, endDate);

        String expected = "s001";

        // Act
        String result = dto.getNumber();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Sprint status is the expected")
    @Test
    void ensureSprintStatusIsRetrievedSuccessfully() {
        // Arrange
        SprintNumber number = new SprintNumber(1);
        SprintStatus status = SprintStatus.CLOSED;
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(number, status, startDate, endDate);

        String expected = "CLOSED";

        // Act
        String result = dto.getStatus();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Start date is the expected")
    @Test
    void ensureStartDateIsRetrievedSuccessfully() {
        // Arrange
        SprintNumber number = new SprintNumber(1);
        SprintStatus status = SprintStatus.CLOSED;
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(number, status, startDate, endDate);

        String expected = "2023-05-01";

        // Act
        String result = dto.getStartDate();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("End date is the expected")
    @Test
    void ensureEndDateIsRetrievedSuccessfully() {
        // Arrange
        SprintNumber number = new SprintNumber(1);
        SprintStatus status = SprintStatus.CLOSED;
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(number, status, startDate, endDate);

        String expected = "2023-05-31";

        // Act
        String result = dto.getEndDate();

        // Assert
        assertEquals(expected, result);
    }
}