package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintFactoryTest {
    /**
     * Method createSprint()
     * Scenario 1: Verify if a Sprint object is created through the ISprintFactory instance
     * and equal to a Sprint object from a Sprint instance.
     * <p>
     * Expected result: Both Sprint objects are equal.
     */
    @Test
    public void testCreateSprint() {
        // Arrange
        Code projectCode = mock(Code.class);
        SprintId sprintId = mock(SprintId.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        Period period = mock(Period.class);
        ISprintFactory sprintFactoryI = new SprintFactory();

        // Act
        Sprint sprint = sprintFactoryI.createSprint(projectCode, sprintId, sprintNumber, period);
        Sprint sprintToCompare = new Sprint(projectCode,sprintId,sprintNumber,period);

        assertEquals(sprint, sprintToCompare);
    }
}