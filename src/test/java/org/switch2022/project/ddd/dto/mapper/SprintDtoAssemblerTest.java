package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.dto.SprintPrimitiveTypesDto;
import org.switch2022.project.ddd.dto.SprintValueObjectsDto;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintDtoAssemblerTest {

    @DisplayName("Sprint -> Value Objects DTO")
    @Test
    void ensureConversionToValueObjectsDtoIsMadeSuccessfully() {
        // Arrange
        Sprint sprintDouble = mock(Sprint.class);

        String number = "s001";
        String status = "CLOSED";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        when(sprintDouble.getSprintNumber()).thenReturn(1);
        when(sprintDouble.getStatus()).thenReturn(status);
        when(sprintDouble.getStartDate()).thenReturn(startDate);
        when(sprintDouble.getEndDate()).thenReturn(endDate);

        // Act
        SprintValueObjectsDto result = SprintDtoAssembler.sprintToValueObjectsDto(sprintDouble);

        // Assert
        assertEquals(number, result.getNumber());
        assertEquals(status, result.getStatus());
        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());
    }

    @DisplayName("Value Objects DTO -> Primitive Types DTO")
    @Test
    void ensureConversionToPrimitiveTypesDtoIsMadeSuccessfully() {
        // Arrange
        SprintValueObjectsDto dtoDouble = mock(SprintValueObjectsDto.class);

        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);

        String number = "s001";
        String status = "OPEN";

        when(dtoDouble.getNumber()).thenReturn(number);
        when(numberDouble.getSprintNumber()).thenReturn(number);

        when(dtoDouble.getStatus()).thenReturn(status);
        when(statusDouble.getStatus()).thenReturn(status);

        when(dtoDouble.getStartDate()).thenReturn(startDate.toString());
        when(dtoDouble.getEndDate()).thenReturn(endDate.toString());

        // Act
        SprintPrimitiveTypesDto result = SprintDtoAssembler.convertToPrimitiveTypes(dtoDouble);

        // Assert
        assertEquals(number, result.getNumber());
        assertEquals(status, result.getStatus());
        assertEquals(startDate.toString(), result.getStartDate());
        assertEquals(endDate.toString(), result.getEndDate());
    }
}