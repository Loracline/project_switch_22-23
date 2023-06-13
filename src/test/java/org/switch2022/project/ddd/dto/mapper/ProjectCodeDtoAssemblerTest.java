package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Code;
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
        String code = "p001";
        ProjectCodeStringDto dto = new ProjectCodeStringDto(code);

        Code codeDouble = mock(Code.class);
        ProjectCodeValueObjectDto expected = new ProjectCodeValueObjectDto(codeDouble);

        when(codeDouble.getCode()).thenReturn(code);

        // Act
        ProjectCodeValueObjectDto result = ProjectCodeDtoAssembler.convertToValueObject(dto);

        // Assert
        assertEquals(expected.getCode().getCode(), result.getCode().getCode());
    }
}