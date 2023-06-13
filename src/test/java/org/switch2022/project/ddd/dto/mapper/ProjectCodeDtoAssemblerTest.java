package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.dto.ProjectCodeStringDto;
import org.switch2022.project.ddd.dto.ProjectCodeValueObjectDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectCodeDtoAssemblerTest {

    @DisplayName("String DTO -> Value Object DTO")
    @Test
    void ensureConversionIsMadeSuccessfully() {
        // Arrange
        String expected = "p001";

        ProjectCodeStringDto dtoDouble = mock(ProjectCodeStringDto.class);

        when(dtoDouble.getCode()).thenReturn("p001");

        // Act
        ProjectCodeValueObjectDto result = ProjectCodeDtoAssembler.convertToValueObject(dtoDouble);

        // Assert
        assertEquals(expected, result.getCode());
    }
}