package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.SprintId;
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

        String projectCode = "p001";
        String id = "p001_s001";
        String number = "s001";
        String status = "CLOSED";
        String startDate = "2023-05-01";
        String endDate = "2023-05-31";

        when(sprintDouble.getProjectCode()).thenReturn(projectCode);
        when(sprintDouble.getFullSprintNumber()).thenReturn(number);
        when(sprintDouble.getSprintNumber()).thenReturn(1);
        when(sprintDouble.getStatus()).thenReturn(status);
        when(sprintDouble.getStartDate()).thenReturn(startDate);
        when(sprintDouble.getEndDate()).thenReturn(endDate);

        // Act
        SprintValueObjectsDto result = SprintDtoAssembler.sprintToValueObjectsDto(sprintDouble);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(number, result.getNumber());
        assertEquals(status, result.getStatus());
        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());
    }

    @DisplayName("Value Objects DTO -> Primitive Types DTO")
    @Test
    void ensureConversionToPrimitiveTypesDtoIsMadeSuccessfully() {
        // Arrange
        SprintId idDouble = mock(SprintId.class);
        SprintNumber numberDouble = mock(SprintNumber.class);
        SprintStatus statusDouble = mock(SprintStatus.class);
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);

        SprintValueObjectsDto dtoDouble = new SprintValueObjectsDto(
                idDouble, numberDouble, statusDouble, startDate, endDate);

        String id = "p001_s001";
        String number = "s001";
        String status = "OPEN";

        SprintPrimitiveTypesDto expected = new SprintPrimitiveTypesDto(
                id, number, status, startDate.toString(), endDate.toString());

        when(idDouble.getSprintId()).thenReturn(id);
        when(numberDouble.getSprintNumber()).thenReturn(number);
        when(statusDouble.getStatus()).thenReturn(status);

        // Act
        SprintPrimitiveTypesDto result = SprintDtoAssembler.convertToPrimitiveTypes(dtoDouble);

        // Assert
        assertEquals(expected.id, result.id);
        assertEquals(expected.number, result.number);
        assertEquals(expected.status, result.status);
        assertEquals(expected.startDate, result.startDate);
        assertEquals(expected.endDate, result.endDate);
    }
}