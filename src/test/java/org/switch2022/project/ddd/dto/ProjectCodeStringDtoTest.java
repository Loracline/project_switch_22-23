package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeStringDtoTest {

    @DisplayName("Project code is the expected")
    @Test
    void ensureProjectCodeIsRetrievedSuccessfully() {
        // Arrange
        String code = "p001";

        ProjectCodeStringDto dto = new ProjectCodeStringDto(code);

        // Act
        String result = dto.getCode();

        // Assert
        assertEquals(code, result);
    }
}