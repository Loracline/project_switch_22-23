package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Code;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectCodeValueObjectDtoTest {

    @DisplayName("Project code is the expected")
    @Test
    void ensureCodeIsRetrievedSuccessfully() {
        // Arrange
        Code codeDouble = mock(Code.class);

        ProjectCodeValueObjectDto dto = new ProjectCodeValueObjectDto(codeDouble);

        when(codeDouble.getCode()).thenReturn("p001");

        // Act
        Code result = dto.getCode();

        // Assert
        assertEquals(codeDouble, result);
    }
}