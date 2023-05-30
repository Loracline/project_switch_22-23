package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SprintCreationDtoTest {

    /**
     * Method : SprintCreationDto().
     *
     */
    @Test
    public void ensureConstructor_ShouldSetAttributes() {
        // Arrange
        String expectedProjectCode = "PROJ-123";
        String expectedStartDate = "2023-06-01";

        // Act
        SprintCreationDto sprint = new SprintCreationDto(expectedProjectCode, expectedStartDate);

        // Assert
        Assertions.assertEquals(expectedProjectCode, sprint.projectCode);
        Assertions.assertEquals(expectedStartDate, sprint.startDate);
    }
}
