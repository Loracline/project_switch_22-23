package org.switch2022.project.ddd.domain.model.project_resource;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectResourceFactoryTest {

    /**
     * Method createProjectResource(ProjectResourceId projectResourceId, Code code, Email email):
     * <p>
     * Creates a Project Resource with minimal required atributes.
     */
    @Test
    void ensureThatProjectResourceIsCreatedSucessfullyWhitThreeArguments() {
        // Arrange
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);

        // Act
        ProjectResource projectResource = projectResourceFactory.createProjectResource(projectResourceIdDouble,
                codeDouble, emailDouble);

        //Assert
        assertNotNull(projectResource);
    }

    /**
     * Method createProjectResource(ProjectResourceId projectResourceId, Code code, Email email, Role role, Period period,
     * CostPerHour cost, PercentageOfAllocation percentageOfAllocation):
     * <p>
     * Creates a Project Resource.
     */

    @Test
    void ensureThatProjectResourceIsCreatedSucessfullyWhitSevenArguments() {
        // Arrange
        ProjectResourceFactory factory = new ProjectResourceFactory();
        ProjectResourceId projectResourceId = mock(ProjectResourceId.class);
        Code code = mock(Code.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        Period period = mock(Period.class);
        CostPerHour cost = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectResource expected = factory.createProjectResource(projectResourceId, code, email, role, period,
                cost, percentageOfAllocation);

        //Act
        ProjectResource result = factory.createProjectResource(projectResourceId, code, email);
        result.setRole(role);
        result.setPeriod(period);
        result.setCost(cost);
        result.setPercentageOfAllocation(percentageOfAllocation);

        //Result:
        assertEquals(expected, result);


    }
}
