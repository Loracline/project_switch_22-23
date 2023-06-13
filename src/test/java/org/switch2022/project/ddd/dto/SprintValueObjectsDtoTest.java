package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintValueObjectsDtoTest {

    @DisplayName("Sprint ID is the expected")
    @Test
    void ensureSprintIdIsRetrievedSuccessfully() {
        // Arrange
        SprintId idDouble = mock(SprintId.class);
        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(idDouble, numberDouble, statusDouble, startDate, endDate);
        String expected = "p001_s001";

        when(idDouble.getSprintId()).thenReturn(expected);

        // Act
        String result = dto.getId();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Sprint number is the expected")
    @Test
    void ensureSprintNumberIsRetrievedSuccessfully() {
        // Arrange
        SprintId idDouble = mock(SprintId.class);
        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(idDouble, numberDouble, statusDouble, startDate, endDate);
        String expected = "s001";

        when(numberDouble.getSprintNumber()).thenReturn(expected);

        // Act
        String result = dto.getNumber();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Sprint status is the expected")
    @Test
    void ensureSprintStatusIsRetrievedSuccessfully() {
        // Arrange
        SprintId idDouble = mock(SprintId.class);
        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(idDouble, numberDouble, statusDouble, startDate, endDate);
        String expected = "CLOSED";

        when(statusDouble.getStatus()).thenReturn(expected);

        // Act
        String result = dto.getStatus();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Start date is the expected")
    @Test
    void ensureStartDateIsRetrievedSuccessfully() {
        // Arrange
        SprintId idDouble = mock(SprintId.class);
        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(idDouble, numberDouble, statusDouble, startDate, endDate);

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
        SprintId idDouble = mock(SprintId.class);
        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5,1);
        LocalDate endDate = LocalDate.of(2023,5,31);

        SprintValueObjectsDto dto = new SprintValueObjectsDto(idDouble, numberDouble, statusDouble, startDate, endDate);

        String expected = "2023-05-31";

        // Act
        String result = dto.getEndDate();

        // Assert
        assertEquals(expected, result);
    }
}