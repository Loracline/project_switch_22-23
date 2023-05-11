package org.switch2022.project.ddd.domain.model.project_resource;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectResourceFactoryTest {

    /**
     * Method createProjectResource(ProjectResourceId projectResourceId, Code code, Email email, Role roleInProject,
     * Period allocationPeriod, CostPerHour costPerHour, PercentageOfAllocation percentageOfAllocation):
     * <p>
     * Creates a Project Resource if all the arguments passed in the method are not null.
     * The new instance of ProjectResource should assert to not null.
     */
    @Test
    void ensureThatProjectResourceIsCreatedSuccessfully() {
        // Arrange
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ProjectResourceId resourceId = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act
        ProjectResource projectResource = projectResourceFactory.createProjectResource(resourceId, codeDouble,
                emailDouble, roleDouble, periodDouble, costDouble,
                percentageOfAllocationDouble);

        //Assert
        assertNotNull(projectResource);
    }

}
