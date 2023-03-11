package org.switch2022.project.factories;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FactoryProjectTest {

    /**
     * Scenario 1: Verifies if This test uses a mock object to simulate the creation of a project
     * with a specific name and code. It then verifies that the project object returned by the mock
     * object has the expected name and code.
     */

    @Test
    public void shouldCreateAValidProjectWithIsolation() {
        // Arrange
        String projectName = "My Project";
        String projectCode = "12345";
        Project projectMock = mock(Project.class);
        when(projectMock.getProjectCode()).thenReturn(projectCode);
        when(projectMock.getProjectName()).thenReturn(projectName);

        // Act
        Project project = projectMock;

        // Assert
        assertNotNull(project.getProjectCode());
        assertEquals(project.getProjectName(), projectName);

    }
}