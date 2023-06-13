package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Code;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCodeValueObjectDtoTest {

    @DisplayName("Project code is the expected")
    @Test
    void ensureCodeIsRetrievedSuccessfully() {
        // Arrange
        Code code = new Code(1);

        ProjectCodeValueObjectDto dto = new ProjectCodeValueObjectDto(code);

        String expected = "p001";

        // Act
        String result = dto.getCode();

        // Assert
        assertEquals(expected, result);
    }
}