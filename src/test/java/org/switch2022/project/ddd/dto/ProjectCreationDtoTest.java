package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreationDtoTest {

    /**
     * Tests the constructor of ProjectCreationDto to ensure that attributes are properly set.
     */
    @Test
    public void ensureConstructor_ShouldSetAttributes() {
        // Arrange
        String expectedProjectName = "MyProject";
        String expectedProjectDescription = "This is a project description.";
        String expectedBusinessSectorId = "SECTOR-123";
        String expectedCustomerId = "CUSTOMER-456";
        String expectedTypologyId = "TYPOLOGY-789";

        // Act
        ProjectCreationDto project = new ProjectCreationDto(expectedProjectName, expectedProjectDescription,
                expectedBusinessSectorId, expectedCustomerId, expectedTypologyId);

        // Assert
        Assertions.assertEquals(expectedProjectName, project.projectName);
        Assertions.assertEquals(expectedProjectDescription, project.projectDescription);
        Assertions.assertEquals(expectedBusinessSectorId, project.businessSectorId);
        Assertions.assertEquals(expectedCustomerId, project.customerId);
        Assertions.assertEquals(expectedTypologyId, project.typologyId);
    }
}
